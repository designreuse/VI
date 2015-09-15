package controller;

import java.util.Date;

import model.Admin;
import model.Branch;
import model.Parent;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.StudentDAO;
import system.Encrypt;
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
	//Register a new student
	public static JSONObject registerStudent(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		returnJson = getStudentByEmail(inputJson);
		if((int)returnJson.get(Key.STATUS) == 0){
			createStudent(inputJson);
		}else{
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, Message.EMAILALREADYEXIST);
		}
		return returnJson;	
	}
	
	//Get student by email
		public static JSONObject getStudentByEmail (JSONObject inputJson){
			JSONObject returnJson = new JSONObject();
			try{
				String email = (String)inputJson.get(Key.EMAIL);
				Student student = StudentDAO.getStudentByEmail(email);
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
		//login student
		public static JSONObject loginStudent(JSONObject inputJson){
			JSONObject returnJson = new JSONObject();
			try{
				String email = (String)inputJson.get(Key.EMAIL);
				Student student = StudentDAO.getStudentByEmail(email);
				if(student != null){
					String password = (String)inputJson.get(Key.PASSWORD);
					String passwordSalt = student.getPasswordSalt();
					String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
					String checkHash = student.getPasswordHash();
					if(checkHash.equals(passwordHash)){
						returnJson.put(Key.STATUS, Value.SUCCESS);
						returnJson.put(Key.MESSAGE, student.toJson());
					}else{
						returnJson.put(Key.STATUS, Value.FAIL);
						returnJson.put(Key.MESSAGE, Message.WRONGADMINPASSWORD);
					}
				}else{
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			}catch(Exception e){
				e.printStackTrace();
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, e);
			}
			return returnJson;
		}
	
}
