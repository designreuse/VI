package controller;

import model.Parent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ParentDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class ParentCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createParent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Key.NAME);
			String email = (String) inputJson.get(Key.EMAIL);
			String contact = (String) inputJson.get(Key.CONTACT);
			String address = (String) inputJson.get(Key.ADDRESS);
			String password = (String) inputJson.get(Key.PASSWORD);
			
			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
			
			Parent parent = new Parent(name, passwordSalt, passwordHash, contact, address, email);
			ParentDAO.addParent(parent);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, parent.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get parent by id
	public static JSONObject getParentById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long parentId = (long)inputJson.get(Key.PARENTID);
			Parent parent = ParentDAO.getParentById(parentId);
			if(parent != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, parent.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all parent
	public static JSONObject getAllParents(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray parentJArr = new JSONArray();
			for(Parent a: ParentDAO.getAllParents()){
				parentJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, parentJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateParent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
			
			if(parent != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				
				parent.setName(name);
				parent.setEmail(email);
				parent.setContact(contact);
				parent.setAddress(address);
				
				ParentDAO.modifyParent(parent);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, parent.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteParent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
			
			if(parent != null){
				parent.setObjStatus(Value.DELETED);
				ParentDAO.modifyParent(parent);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, parent.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	
}
