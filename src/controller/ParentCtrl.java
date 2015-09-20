package controller;

import model.Branch;
import model.Parent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ParentDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class ParentCtrl {

	/**
	 * CRUD
	 * */
	public static JSONObject createParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
			String name = (String) inputJson.get(Key.NAME);
			String email = (String) inputJson.get(Key.EMAIL);
			String contact = (String) inputJson.get(Key.CONTACT);
			String address = (String) inputJson.get(Key.ADDRESS);
			String password = (String) inputJson.get(Key.PASSWORD);

			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password,
					passwordSalt);

			Parent parent = new Parent(name, passwordSalt, passwordHash,
					contact, address, email, branch);
			ParentDAO.addParent(parent);

			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get parent by id
	public static JSONObject getParentById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long parentId = (long) inputJson.get(Key.PARENTID);
			Parent parent = ParentDAO.getParentById(parentId);
			if (parent != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all parent
	public static JSONObject getAllParents() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray parentJArr = new JSONArray();
			for (Parent a : ParentDAO.getAllParents()) {
				parentJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, parentJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Parent parent = ParentDAO.getParentById((long) inputJson
					.get(Key.PARENTID));

			if (parent != null) {
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);

				parent.setName(name);
				parent.setEmail(email);
				parent.setContact(contact);
				parent.setAddress(address);

				ParentDAO.modifyParent(parent);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	public static JSONObject deleteParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Parent parent = ParentDAO.getParentById((long) inputJson
					.get(Key.PARENTID));

			if (parent != null) {
				parent.setObjStatus(Value.DELETED);
				ParentDAO.modifyParent(parent);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	// Login parent
	public static JSONObject loginParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Parent parent = ParentDAO.getParentByEmail(email);
			if (parent != null) {
				String password = (String) inputJson.get(Key.PASSWORD);
				String passwordSalt = parent.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password,passwordSalt);
				String checkHash = parent.getPasswordHash();
				if (checkHash.equals(passwordHash)) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, parent.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGPARENTPASSWORD);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Register a new parent
	public static JSONObject registerParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		returnJson = getParentByEmail(inputJson);
		Long statusTest = (long) returnJson.get(Key.STATUS); 
		if (statusTest.intValue() == 0) {
			returnJson = createParent(inputJson);
		} else {
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, Message.EMAILALREADYEXIST);
		}
		return returnJson;
	}

	// Get parent by email
	public static JSONObject getParentByEmail(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Parent parent = ParentDAO.getParentByEmail(email);
			if (parent != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get parent by branch
	public static JSONObject getParentByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = (Branch) inputJson.get(Key.BRANCH);
			Parent parent = ParentDAO.getParentByBranch(branch);
			if (parent != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
