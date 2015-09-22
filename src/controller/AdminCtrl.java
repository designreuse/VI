package controller;

import model.Admin;
import model.Branch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class AdminCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String name = (String) inputJson.get(Key.NAME);
			String email = (String) inputJson.get(Key.EMAIL);
			String password = (String) inputJson.get(Key.PASSWORD);
			
			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
			
			Admin admin = new Admin(name, email, passwordSalt, passwordHash);
			AdminDAO.addAdmin(admin);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, admin.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get admin by id
	public static JSONObject getAdminById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long adminId = (long)inputJson.get(Key.ADMINID);
			Admin admin = AdminDAO.getAdminById(adminId);
			if(admin != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, admin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all admin
	public static JSONObject getAllAdmins(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray adminJArr = new JSONArray();
			for(Admin a: AdminDAO.getAllAdmins()){
				adminJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, adminJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			
			if(admin != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				
				admin.setName(name);
				admin.setEmail(email);
				
				AdminDAO.modifyAdmin(admin);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, admin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			
			if(admin != null){
				admin.setObjStatus(Value.DELETED);
				AdminDAO.modifyAdmin(admin);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, admin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	//Login admin
	public static JSONObject loginAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String email = (String)inputJson.get(Key.EMAIL);
			Admin admin = AdminDAO.getAdminByEmail(email);
			if(admin != null){
				String password = (String)inputJson.get(Key.PASSWORD);
				String passwordSalt = admin.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String checkHash = admin.getPasswordHash();
				if(checkHash.equals(passwordHash)){
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, admin.toJson());
				}else{
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGADMINPASSWORD);
				}
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Register a new admin
	public static JSONObject registerAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		returnJson = getAdminByEmail(inputJson);
		if((int)returnJson.get(Key.STATUS) == 0){
			createAdmin(inputJson);
		}else{
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, Message.EMAILALREADYEXIST);
		}
		return returnJson;	
	}
	
	//Get admin by email
	public static JSONObject getAdminByEmail (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String email = (String)inputJson.get(Key.EMAIL);
			Admin admin = AdminDAO.getAdminByEmail(email);
			if(admin != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, admin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	//Get admin by branch
		public static JSONObject getAdminByBranch (JSONObject inputJson){
			JSONObject returnJson = new JSONObject();
			try{
				Branch branch = (Branch)inputJson.get(Key.BRANCH);
				Admin admin = AdminDAO.getAdminByBranch(branch);
				if(admin != null){
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, admin.toJson());
				}else{
					returnJson.put(Key.STATUS, Value.FAIL)  ;
					returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
				}
			}catch(Exception e){
				e.printStackTrace();
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, e);
			}
			return returnJson;
		}
}
