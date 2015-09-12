package controller;

import java.util.Date;

import model.Branch;
import model.Classroom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ClassroomDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class ClassroomCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createClassroom(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if(branch != null){
				String name = (String) inputJson.get(Key.NAME);
				long roomCapacity = (long) inputJson.get(Key.ROOMCAPACITY);
				
				Classroom classroom = new Classroom(name, roomCapacity, branch);
				ClassroomDAO.addClassroom(classroom);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, classroom.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get classroom by id
	public static JSONObject getClassroomById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long classroomId = (long)inputJson.get(Key.CLASSROOMID);
			Classroom classroom = ClassroomDAO.getClassroomById(classroomId);
			if(classroom != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, classroom.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all classroom
	public static JSONObject getAllClassrooms(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray classroomJArr = new JSONArray();
			for(Classroom a: ClassroomDAO.getAllClassrooms()){
				classroomJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, classroomJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateClassroom(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			if(classroom != null){
				String name = (String) inputJson.get(Key.NAME);
				long roomCapacity = (long) inputJson.get(Key.ROOMCAPACITY);
				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				
				classroom.setName(name);
				classroom.setRoomCapacity(roomCapacity);
				classroom.setBranch(branch);
				
				ClassroomDAO.modifyClassroom(classroom);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, classroom.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteClassroom(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			
			if(classroom != null){
				classroom.setObjStatus(Value.DELETED);
				ClassroomDAO.modifyClassroom(classroom);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, classroom.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
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
