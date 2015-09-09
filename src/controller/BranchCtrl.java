package controller;

import model.Admin;
import model.Branch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.BranchDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class BranchCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createBranch(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			if (admin != null) {
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String contactNo = (String) inputJson.get(Key.CONTACTNO);
				
				Branch branch = new Branch(name, location, postalCode, admin);
				BranchDAO.addBranch(branch);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
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
	
	//Get branch by id
	public static JSONObject getBranchById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long branchId = (long)inputJson.get(Key.BRANCHID);
			Branch branch = BranchDAO.getBranchById(branchId);
			if(branch != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all branch
	public static JSONObject getAllBranchs(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray branchJArr = new JSONArray();
			for(Branch a: BranchDAO.getAllBranchs()){
				branchJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, branchJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateBranch(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			
			if(branch != null){
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String contactNo = (String) inputJson.get(Key.CONTACTNO);
				
				branch.setName(name);
				branch.setLocation(location);
				branch.setPostalcode(postalcode);
				branch.setContactNo(contactNo);
				
				BranchDAO.modifyBranch(branch);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, branch.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteBranch(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			
			if(branch != null){
				branch.setObjStatus(Value.DELETED);
				BranchDAO.modifyBranch(branch);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, branch.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
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
