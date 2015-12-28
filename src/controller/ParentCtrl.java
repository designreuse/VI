package controller;

import model.Branch;
import model.Parent;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.StudentDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class ParentCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				String contact = (String) inputJson.get(Key.CONTACT);
				String email = (String) inputJson.get(Key.EMAIL);
				String parentNric = (String) inputJson.get(Key.PARENTNRIC);
				String occupation = (String) inputJson.get(Key.OCCUPATION);
				String relatoinship = (String) inputJson.get(Key.RELATIONSHIP);
				String password = (String) inputJson.get(Key.PASSWORD);
				String passwordSalt = Encrypt.nextSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);

				Parent parent = new Parent(name, passwordSalt, passwordHash, contact, email, parentNric, occupation, relatoinship, branch);
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
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
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

	// Get all parent, not a very good way to retrieve parents without constraints.
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
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
			if (parent != null) {
				String name = (String) inputJson.get(Key.NAME);
				String contact = (String) inputJson.get(Key.CONTACT);
				String email = (String) inputJson.get(Key.EMAIL);
				String parentNric = (String) inputJson.get(Key.PARENTNRIC);
				String occupation = (String) inputJson.get(Key.OCCUPATION);
				String relationship = (String) inputJson.get(Key.RELATIONSHIP);
				//Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				
				parent.setName(name);
				parent.setContact(contact);
				parent.setEmail(email);
				parent.setParentNric(parentNric);
				parent.setOccupation(occupation);
				parent.setRelationship(relationship);
				//parent.setBranch(branch);

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
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
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
			Parent parent = ParentDAO.getParentByEmail((String) inputJson.get(Key.EMAIL));
			if (parent != null) {
				String password = (String) inputJson.get(Key.PASSWORD);
				String passwordSalt = parent.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
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
		if ((long) returnJson.get(Key.STATUS) == 0) {
			returnJson = getParentByNric(inputJson);
			if ((long) returnJson.get(Key.STATUS) == 0) {
				returnJson = createParent(inputJson);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNRICALREADYEXIST);
			}
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
	
	// Get parent by parent nric
	public static JSONObject getParentByNric(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String nric = (String) inputJson.get(Key.PARENTNRIC);
			Parent parent = ParentDAO.getParentByNric(nric);
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
	
	// Get parents by branch
	public static JSONObject getParentsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray parentArr = new JSONArray();
				for(Parent p : ParentDAO.getParentsByBranch(branch)){
					parentArr.add(p.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parentArr);
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

	// Get parent by student, the parent data already comes in the student object, so this method may not necessraily used
	public static JSONObject getParentByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
			if (student != null) {
				Parent parent = student.getParent();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
