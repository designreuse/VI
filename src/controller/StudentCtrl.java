package controller;

import java.util.Date;

import model.Branch;
import model.Parent;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.StudentDAO;
import system.Key;
import system.Message;
import system.Value;

public class StudentCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createStudent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
			if(branch != null){
				if(parent != null){
					String name = (String) inputJson.get(Key.NAME);
					String email = (String) inputJson.get(Key.EMAIL);
					String contact = (String) inputJson.get(Key.CONTACT);
					String address = (String) inputJson.get(Key.ADDRESS);
					String studentLevel = (String) inputJson.get(Key.STUDENTLEVEL);
					
					Student student = new Student(name, email, contact, address, studentLevel, parent, branch);
					StudentDAO.addStudent(student);
					
					returnJson.put(Key.STATUS, Value.SUCCESS)  ;
					returnJson.put(Key.MESSAGE, student.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
				}
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
	
	//Get student by id
	public static JSONObject getStudentById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long studentId = (long)inputJson.get(Key.STUDENTID);
			Student student = StudentDAO.getStudentById(studentId);
			if(student != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all student
	public static JSONObject getAllStudents(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray studentJArr = new JSONArray();
			for(Student a: StudentDAO.getAllStudents()){
				studentJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, studentJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateStudent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			
			if(student != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				String studentLevel = (String) inputJson.get(Key.STUDENTLEVEL);
				
				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
				
				student.setName(name); 
				student.setEmail(email);
				student.setContact(contact);
				student.setAddress(address);
				student.setStudentLevel(studentLevel);
				student.setBranch(branch);
				student.setParent(parent);
				
				StudentDAO.modifyStudent(student);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, student.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteStudent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			
			if(student != null){
				student.setObjStatus(Value.DELETED);
				StudentDAO.modifyStudent(student);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, student.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
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
