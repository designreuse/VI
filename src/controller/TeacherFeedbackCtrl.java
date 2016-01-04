package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Teacher;
import model.TeacherFeedback;
import model.Student;
import model.TeacherStudentCourse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.TeacherDAO;
import dataManager.TeacherFeedbackDAO;
import dataManager.StudentDAO;
import dataManager.TeacherStudentCourseDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class TeacherFeedbackCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createTeacherFeedback(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById((long) inputJson.get(Key.TEACHERSTUDENTCOURSEID));
			if(teacherStudentCourse != null){
				String content = (String) inputJson.get(Key.CONTENT);

				TeacherFeedback teacherFeedback = new TeacherFeedback(content, teacherStudentCourse);
				TeacherFeedbackDAO.addTeacherFeedback(teacherFeedback);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedback.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get teacherFeedback by id
	public static JSONObject getTeacherFeedbackById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherFeedback teacherFeedback = TeacherFeedbackDAO.getTeacherFeedbackById((long) inputJson.get(Key.TEACHERFEEDBACKID));
			if (teacherFeedback != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedback.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERFEEDBACKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all teacherFeedback
	public static JSONObject getAllTeacherFeedbacks() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray teacherFeedbackJArr = new JSONArray();
			for (TeacherFeedback a : TeacherFeedbackDAO.getAllTeacherFeedbacks()) {
				teacherFeedbackJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, teacherFeedbackJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateTeacherFeedback(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherFeedback teacherFeedback = TeacherFeedbackDAO.getTeacherFeedbackById((long) inputJson.get(Key.TEACHERFEEDBACKID));
			if (teacherFeedback != null) {
				String content = (String) inputJson.get(Key.CONTENT);
				
				teacherFeedback.setContent(content);
				
				TeacherFeedbackDAO.modifyTeacherFeedback(teacherFeedback);
				
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedback.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERFEEDBACKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteTeacherFeedback(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherFeedback teacherFeedback = TeacherFeedbackDAO.getTeacherFeedbackById((long) inputJson.get(Key.TEACHERFEEDBACKID));
			if (teacherFeedback != null) {
				teacherFeedback.setObjStatus(Value.DELETED);
				TeacherFeedbackDAO.modifyTeacherFeedback(teacherFeedback);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedback.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERFEEDBACKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	// Get teacherFeedbacks by student
	public static JSONObject getTeacherFeedbacksByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray teacherFeedbackArr = new JSONArray();
				ArrayList<TeacherStudentCourse> tscs = TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(student);
				for(TeacherStudentCourse tsc : tscs){
					for (TeacherFeedback b : TeacherFeedbackDAO.getTeacherFeedbacksByTeacherStudentCourse(tsc)) {
						teacherFeedbackArr.add(b.toJson());
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedbackArr);
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
	
	// Get latest teacherFeedbacks by student
	public static JSONObject getLatestTeacherFeedbacksByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray teacherFeedbackArr = new JSONArray();
				ArrayList<TeacherStudentCourse> tscs = TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(student);
				for(TeacherStudentCourse tsc : tscs){
					teacherFeedbackArr.add(TeacherFeedbackDAO.getLatestTeacherFeedbackByTSC(tsc).toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherFeedbackArr);
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
	
	// Get teacherFeedbacks by teacher and student
	public static JSONObject getTeacherFeedbacksByTeacherAndStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if(teacher != null){
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if (student != null) {
					JSONArray teacherFeedbackArr = new JSONArray();
					ArrayList<TeacherStudentCourse> tscs = TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(student);
					for(TeacherStudentCourse tsc : tscs){
						for (TeacherFeedback b : TeacherFeedbackDAO.getTeacherFeedbacksByTeacherStudentCourse(tsc)) {
							teacherFeedbackArr.add(b.toJson());
						}
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherFeedbackArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
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
