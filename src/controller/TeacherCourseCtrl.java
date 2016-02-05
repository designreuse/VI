package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.DiagnosticDAO;
import dataManager.StudentDAO;
import dataManager.TeacherCourseDAO;
import dataManager.CourseDAO;
import dataManager.TeacherDAO;
import model.Diagnostic;
import model.Student;
import model.TeacherCourse;
import model.Course;
import model.Teacher;
import system.Key;
import system.Message;
import system.Value;

public class TeacherCourseCtrl {
	/**
	 * CRUD course and teacher exist
	 */
	public static JSONObject createTeacherCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if (teacher != null) {
					TeacherCourse teacherCourse = new TeacherCourse(teacher, course);
					TeacherCourseDAO.addTeacherCourse(teacherCourse);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacherCourse.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
				}
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

	// Get pointevent by id
	public static JSONObject getTeacherCourseById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long teacherCourseId = (long) inputJson.get(Key.TEACHERCOURSEID);
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById(teacherCourseId);
			if (teacherCourse != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
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

	// Get all pointevents
	public static JSONObject getAllTeacherCourses() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray teacherCourseJArr = new JSONArray();
			for (TeacherCourse p : TeacherCourseDAO.getAllTeacherCourses()) {
				teacherCourseJArr.add(p.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, teacherCourseJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateTeacherCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
			if (teacherCourse != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if (course != null) {
					teacherCourse.setCourse(course);
				}
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if (teacher != null) {
					teacherCourse.setTeacher(teacher);
				}
				
				TeacherCourseDAO.modifyTeacherCourse(teacherCourse);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
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
	
	//totally delete away the point event
	public static JSONObject deleteTeacherCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
			if (teacherCourse != null) {
				TeacherCourseDAO.deleteTeacherCourse(teacherCourse);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacherCourse.toJson());
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
	
	// features
	//create multiple teacherCourses
	public static JSONObject createTeacherCourses(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArr = new JSONArray();
		try {
			JSONArray coursesArr = (JSONArray) inputJson.get(Key.COURSES);
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null){
				for (Object cId : coursesArr) {
					JSONObject messageJson = new JSONObject();
					Course course = CourseDAO.getCourseById((long) cId);
					if (course != null) {
						TeacherCourse teacherCourse = new TeacherCourse(teacher, course);
						TeacherCourseDAO.addTeacherCourse(teacherCourse);

						messageJson.put(Key.STATUS, Value.SUCCESS);
						messageJson.put(Key.TEACHERCOURSE, teacherCourse.toJson());
						returnArr.add(messageJson);
					} else {
						messageJson.put(Key.STATUS, Value.FAIL);
						messageJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
						returnArr.add(messageJson);
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, returnArr);
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
	
	
	// Get point events by course
	public static JSONObject getTeacherCoursesByCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				JSONArray teacherCourseArr = new JSONArray();
				for (TeacherCourse pe : TeacherCourseDAO.getTeacherCoursesByCourse(course)) {
					teacherCourseArr.add(pe.toJson());
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
	
	// Get point events by teacher
	public static JSONObject getTeacherCoursesByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				JSONArray teacherCourseArr = new JSONArray();
				for (TeacherCourse pe : TeacherCourseDAO.getTeacherCoursesByTeacher(teacher)) {
					teacherCourseArr.add(pe.toJson());
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
	
	// Get point events by teacher and course
	public static JSONObject getTeacherCoursesByTeacherAndCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					JSONArray teacherCourseArr = new JSONArray();
					for (TeacherCourse pe : TeacherCourseDAO.getTeacherCoursesByTeacherAndCourse(teacher, course)) {
						teacherCourseArr.add(pe.toJson());
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
