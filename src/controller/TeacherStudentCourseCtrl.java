package controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.CourseDAO;
import dataManager.StudentDAO;
import dataManager.TeacherStudentCourseDAO;
import dataManager.TeacherDAO;
import model.Branch;
import model.Course;
import model.Student;
import model.Teacher;
import model.TeacherStudentCourse;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class TeacherStudentCourseCtrl {
	/**
	 * CRUD teacher and course exist
	 * */
	public static JSONObject createTeacherStudentCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if(course != null){
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if(teacher != null){
					Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
					if(student != null){
						long courseLevel = (long) inputJson.get(Key.COURSELEVEL);
						long bookletLevel = 0; 
						if(inputJson.get(Key.BOOKLETLEVEL) != null){
							bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
						}
						TeacherStudentCourse teacherStudentCourse = new TeacherStudentCourse(course, teacher, student, courseLevel, bookletLevel);
						TeacherStudentCourseDAO.addTeacherStudentCourse(teacherStudentCourse);
						
						returnJson.put(Key.STATUS, Value.SUCCESS)  ;
						returnJson.put(Key.MESSAGE, teacherStudentCourse.toJsonSimpleTSC());
					} else {
						returnJson.put(Key.STATUS, Value.FAIL);
						returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
					}
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
	
	//Get teacherStudentCourse by id
	public static JSONObject getTeacherStudentCourseById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long teacherStudentCourseId = (long)inputJson.get(Key.TEACHERSTUDENTCOURSEID);
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById(teacherStudentCourseId);
			if(teacherStudentCourse != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherStudentCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all teacherStudentCourses
	public static JSONObject getAllTeacherStudentCourses(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray teacherStudentCourseJArr = new JSONArray();
			for(TeacherStudentCourse tc: TeacherStudentCourseDAO.getAllTeacherStudentCourses()){
				teacherStudentCourseJArr.add(tc.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, teacherStudentCourseJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateTeacherStudentCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById((long) inputJson.get(Key.TEACHERSTUDENTCOURSEID));
			if(teacherStudentCourse != null){
				
				long courseLevel = (long) inputJson.get(Key.COURSELEVEL);
				long bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
				
//				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
//				if(course != null){
//					teacherStudentCourse.setCourse(course);
//				}//TODO maybe add in the else clause to state the absent of course.
//				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
//				if(teacher != null){
//					teacherStudentCourse.setTeacher(teacher);
//				}//TODO same as b=above for teacher.
				
				teacherStudentCourse.setCourseLevel(courseLevel);
				teacherStudentCourse.setBookletLevel(bookletLevel);
				
				TeacherStudentCourseDAO.modifyTeacherStudentCourse(teacherStudentCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, teacherStudentCourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteTeacherStudentCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById((long) inputJson.get(Key.TEACHERSTUDENTCOURSEID));
			if(teacherStudentCourse != null){
				TeacherStudentCourseDAO.deleteTeacherStudentCourse(teacherStudentCourse);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSEDELETED);
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	// features
	// Get teacher student courses by teacher
	public static JSONObject getTeacherStudentCoursesByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				JSONArray teacherStudentCourseArr = new JSONArray();
				for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByTeacher(teacher)) {
					teacherStudentCourseArr.add(tsc.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherStudentCourseArr);
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
	
	// Get teacher student courses by course
	public static JSONObject getTeacherStudentCoursesByCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				JSONArray teacherStudentCourseArr = new JSONArray();
				for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByCourse(course)) {
					teacherStudentCourseArr.add(tsc.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherStudentCourseArr);
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
	
	// Get teacher student courses by course
	public static JSONObject getTeacherStudentCoursesByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray teacherStudentCourseArr = new JSONArray();
				for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(student)) {
					teacherStudentCourseArr.add(tsc.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherStudentCourseArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get teacherCourses by teacher and course
	public static JSONObject getTeacherStudentCoursesByTeacherAndCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if (course != null) {
					JSONArray teacherStudentCourseArr = new JSONArray();
					for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByTeacherAndCourse(teacher, course)) {
						teacherStudentCourseArr.add(tsc.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherStudentCourseArr);
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
	
	// Get studnets by teacher and course
	public static JSONObject getStudentsByTeacherAndCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if (course != null) {
					JSONArray teacherStudentCourseArr = new JSONArray();
					for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByTeacherAndCourse(teacher, course)) {
						teacherStudentCourseArr.add(tsc.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherStudentCourseArr);
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
