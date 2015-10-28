package controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.BranchCourseDAO;
import dataManager.BranchDAO;
import model.Course;
import model.Branch;
import model.BranchCourse;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class BranchCourseCtrl {
	/**
	 * CRUD branch and course exist
	 * */
	public static JSONObject createBranchCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if(course != null){
				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				if(branch != null){
					BranchCourse branchCourse = new BranchCourse(branch, course);
					BranchCourseDAO.addBranchCourse(branchCourse);
					
					returnJson.put(Key.STATUS, Value.SUCCESS)  ;
					returnJson.put(Key.MESSAGE, branchCourse.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get branchCourse by id
	public static JSONObject getBranchCourseById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long branchCourseId = (long)inputJson.get(Key.BRANCHCOURSEID);
			BranchCourse branchCourse = BranchCourseDAO.getBranchCourseById(branchCourseId);
			if(branchCourse != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all branchCourses
	public static JSONObject getAllBranchCourses(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray branchCourseJArr = new JSONArray();
			for(BranchCourse tc: BranchCourseDAO.getAllBranchCourses()){
				branchCourseJArr.add(tc.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, branchCourseJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateBranchCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			BranchCourse branchCourse = BranchCourseDAO.getBranchCourseById((long) inputJson.get(Key.BRANCHCOURSEID));
			if(branchCourse != null){
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					branchCourse.setCourse(course);
				}//TODO maybe add in the else clause to state the absent of course.
				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				if(branch != null){
					branchCourse.setBranch(branch);
				}//TODO same as b=above for branch.
				BranchCourseDAO.modifyBranchCourse(branchCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, branchCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteBranchCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			BranchCourse branchCourse = BranchCourseDAO.getBranchCourseById((long) inputJson.get(Key.BRANCHCOURSEID));
			if(branchCourse != null){
				BranchCourseDAO.deleteBranchCourse(branchCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHCOURSEDELETED);
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.BRANCHCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	// features
	// Get branchcourses by branch
	public static JSONObject getBranchCoursesByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray branchCourseArr = new JSONArray();
				for (BranchCourse tc : BranchCourseDAO.getBranchCoursesByBranch(branch)) {
					branchCourseArr.add(tc.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchCourseArr);
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
	
	// Get branchcourses by course
//	public static JSONObject getBranchCoursesByCourse(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
//			if (course != null) {
//				JSONArray branchCourseArr = new JSONArray();
//				for (BranchCourse tc : BranchCourseDAO.getBranchCoursesByCourse(course)) {
//					branchCourseArr.add(tc.toJson());
//				}
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, branchCourseArr);
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
	// Get branchcourses by branch and course
//	public static JSONObject getBranchCoursesByBranchAndCourse(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
//			if (branch != null) {
//				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
//				if (course != null) {
//					JSONArray branchCourseArr = new JSONArray();
//					for (BranchCourse tc : BranchCourseDAO.getBranchCoursesByBranchAndCourse(branch, course)) {
//						branchCourseArr.add(tc.toJson());
//					}
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, branchCourseArr);
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
//				}
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
}
