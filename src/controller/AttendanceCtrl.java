package controller;

import java.util.Date;

import model.Admin;
import model.Attendance;
import model.Branch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.AttendanceDAO;
import dataManager.BranchDAO;
import system.Config;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class AttendanceCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createAttendance(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
//			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
//			
//			if (branch != null && student != null && course != null) {
//				String location = (String) inputJson.get(Key.LOCATION);
//				Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
//				Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
//				
//				Attendance attendance = new Attendance(planStartDate, location, actualStartDate, admin);
//				AttendanceDAO.addAttendance(attendance);
//				
//				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
//				returnJson.put(Key.MESSAGE, attendance.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
//			}			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get attendance by id
	public static JSONObject getAttendanceById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long attendanceId = (long)inputJson.get(Key.BRANCHID);
			Attendance attendance = AttendanceDAO.getAttendanceById(attendanceId);
			if(attendance != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
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
	
	//Get all attendance
	public static JSONObject getAllAttendances(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray attendanceJArr = new JSONArray();
			for(Attendance a: AttendanceDAO.getAllAttendances()){
				attendanceJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, attendanceJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateAttendance(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.BRANCHID));
			
			if(attendance != null){
//				String name = (String) inputJson.get(Key.NAME);
//				String location = (String) inputJson.get(Key.LOCATION);
//				String postalCode = (String) inputJson.get(Key.POSTALCODE);
//				//String contactNo = (String) inputJson.get(Key.CONTACTNO);
//				
//				attendance.setName(name);
//				attendance.setLocation(location);
//				attendance.setPostalcode(postalCode);
//				//attendance.setContactNo(contactNo);
//				
				AttendanceDAO.modifyAttendance(attendance);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, attendance.toJson());
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
	
	public static JSONObject deleteAttendance(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.BRANCHID));
			
			if(attendance != null){
				attendance.setObjStatus(Value.DELETED);
				AttendanceDAO.modifyAttendance(attendance);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, attendance.toJson());
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
