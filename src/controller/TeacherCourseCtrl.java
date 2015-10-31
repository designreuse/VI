package controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.TeacherCourseDAO;
import dataManager.TeacherDAO;
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
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if(course != null){
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if(teacher != null){
					TeacherCourse teacherCourse = new TeacherCourse(course, teacher);
					TeacherCourseDAO.addTeacherCourse(teacherCourse);
					
					returnJson.put(Key.STATUS, Value.SUCCESS)  ;
					returnJson.put(Key.MESSAGE, teacherCourse.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
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
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					teacherCourse.setCourse(course);
				}//TODO maybe add in the else clause to state the absent of course.
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if(teacher != null){
					teacherCourse.setTeacher(teacher);
				}//TODO same as b=above for teacher.
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
				TeacherCourseDAO.deleteTeacherCourse(teacherCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSEDELETED);
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
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				JSONArray teacherCourseArr = new JSONArray();
				for (TeacherCourse tc : TeacherCourseDAO.getTeacherCoursesByTeacher(teacher)) {
					teacherCourseArr.add(tc.toJsonStrong());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourseArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get teachercourses by course
	public static JSONObject getTeacherCoursesByCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				JSONArray teacherCourseArr = new JSONArray();
				for (TeacherCourse tc : TeacherCourseDAO.getTeacherCoursesByCourse(course)) {
					teacherCourseArr.add(tc.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourseArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get teachercourses by teacher and course
	public static JSONObject getTeacherCoursesByTeacherAndCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if (course != null) {
					JSONArray teacherCourseArr = new JSONArray();
					for (TeacherCourse tc : TeacherCourseDAO.getTeacherCoursesByTeacherAndCourse(teacher, course)) {
						teacherCourseArr.add(tc.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherCourseArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
