package controller;

import java.util.Date;

import model.Branch;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.TeacherDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class TeacherCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createTeacher(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if(branch != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				Date dateOfBirth = Config.SDF.parse((String)inputJson.get(Key.DATEOFBIRTH));
				long age = (long) inputJson.get(Key.AGE);
				String qualification = (String) inputJson.get(Key.QUALIFICATION);
				
				Teacher teacher = new Teacher(name, email, contact, address, dateOfBirth, age, qualification, branch);
				TeacherDAO.addTeacher(teacher);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacher.toJson());
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
	
	//Get teacher by id
	public static JSONObject getTeacherById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long teacherId = (long)inputJson.get(Key.TEACHERID);
			Teacher teacher = TeacherDAO.getTeacherById(teacherId);
			if(teacher != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all teacher
	public static JSONObject getAllTeachers(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray teacherJArr = new JSONArray();
			for(Teacher a: TeacherDAO.getAllTeachers()){
				teacherJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, teacherJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateTeacher(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			
			if(teacher != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				Date dateOfBirth = Config.SDF.parse((String)inputJson.get(Key.DATEOFBIRTH));
				long age = (long) inputJson.get(Key.AGE);
				String qualification = (String) inputJson.get(Key.QUALIFICATION);
				
				teacher.setName(name);
				teacher.setEmail(email);
				teacher.setContact(contact);
				teacher.setAddress(address);
				teacher.setDateOfBirth(dateOfBirth);
				teacher.setAge(age);
				teacher.setQualification(qualification);
				
				TeacherDAO.modifyTeacher(teacher);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacher.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteTeacher(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			
			if(teacher != null){
				teacher.setObjStatus(Value.DELETED);
				TeacherDAO.modifyTeacher(teacher);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacher.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
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
