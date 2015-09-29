package controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.TeacherCourseDAO;
import model.Course;
import model.Teacher;
import model.TeacherCourse;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class TeacherCourseCtrl {
	/**
	 * CRUD teacher and course exist
	 * */
	public static JSONObject createTeacherCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long courseId = (long) inputJson.get(Key.COURSEID);
			long teacherId = (long) inputJson.get(Key.TEACHERID);
			
			TeacherCourse teacherCourse = new TeacherCourse(courseId, teacherId);
			TeacherCourseDAO.addTeacherCourse(teacherCourse);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, teacherCourse.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get teacherCourse by id
	public static JSONObject getTeacherCourseById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long teacherCourseId = (long)inputJson.get(Key.TEACHERCOURSEID);
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById(teacherCourseId);
			if(teacherCourse != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all teacherCourses
	public static JSONObject getAllTeacherCourses(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray teacherCourseJArr = new JSONArray();
			for(TeacherCourse tc: TeacherCourseDAO.getAllTeacherCourses()){
				teacherCourseJArr.add(tc.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, teacherCourseJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateTeacherCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
			
			if(teacherCourse != null){
				long courseId = (long) inputJson.get(Key.COURSEID);
				long teacherId = (long) inputJson.get(Key.TEACHERID);
				
				teacherCourse.setCourseId(courseId);
				teacherCourse.setTeacherId(teacherId);
				
				TeacherCourseDAO.modifyTeacherCourse(teacherCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteTeacherCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
			
			if(teacherCourse != null){
				teacherCourse.setObjStatus(Value.DELETED);
				TeacherCourseDAO.modifyTeacherCourse(teacherCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	// features
	// Get teachercourses by teacher
	public static JSONObject getTeacherCoursesByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = (Teacher) inputJson.get(Key.TEACHER);
			ArrayList<TeacherCourse> teacherCourses = TeacherCourseDAO.getTeacherCoursesByTeacher(teacher);
			if (teacherCourses != null) {
				for (TeacherCourse teacherCourse : teacherCourses) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherCourse.toJson());
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
