package controller;

import model.Course;
import model.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import system.Key;
import system.Message;
import system.Value;

public class CourseCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
//			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
//			if (teacher != null) {
			String name = (String) inputJson.get(Key.NAME);
			String description = (String) inputJson.get(Key.DESCRIPTION);
			String courseLevel = (String) inputJson.get(Key.COURSELEVEL);
			String courseCost = (String) inputJson.get(Key.COURSECOST);
			long courseCapacity = (long) inputJson.get(Key.COURSECAPACITY);

			Course course = new Course(name, description, courseLevel, courseCost, courseCapacity);
			CourseDAO.addCourse(course);

			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, course.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get course by id
	public static JSONObject getCourseById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, course.toJson());
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

	// Get all course
	public static JSONObject getAllCourses() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray courseJArr = new JSONArray();
			for (Course a : CourseDAO.getAllCourses()) {
				courseJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, courseJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				String name = (String) inputJson.get(Key.NAME);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				String courseLevel = (String) inputJson.get(Key.COURSELEVEL);
				String courseCost = (String) inputJson.get(Key.COURSECOST);
				long courseCapacity = (long) inputJson.get(Key.COURSECAPACITY);

				course.setName(name);
				course.setDescription(description);
				course.setCourseLevel(courseLevel);
				course.setCourseCost(courseCost);
				course.setCourseCapacity(courseCapacity);

				CourseDAO.modifyCourse(course);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, course.toJson());
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

	public static JSONObject deleteCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				course.setObjStatus(Value.DELETED);
				CourseDAO.modifyCourse(course);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, course.toJson());
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

	// features
	
	//TODO wait for more request to implement detailed method to retrieve course.
	//Get course by name.
	public static JSONObject getCourseByName (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Course acourse = CourseDAO.getCourseByName((String)inputJson.get(Key.NAME));
			if(acourse != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, acourse.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get course by result
	public static JSONObject getCourseByResult(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Result result = ResultDAO.getResultById((long)inputJson.get(Key.RESULTID));
			if (result != null) {
				Course course = result.getCourse();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, course.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.RESULTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
		
		
	// Get courses by teacher
//	public static JSONObject getCoursesByTeacher(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Teacher teacher = (Teacher) inputJson.get(Key.TEACHER);
//			ArrayList<Course> courses = CourseDAO.getCoursesByTeacher(teacher);
//			if (courses != null) {
//				for (Course course : courses) {
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, course.toJson());
//				}
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
//	Get course by attendance
//	public static JSONObject getCourseByAttendance(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Attendance attendance = AttendanceDAO.getAttendanceById((long)inputJson.get(Key.ATTENDANCEID));
//			if (attendance != null) {
//				Course course = attendance.getCourse();
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, course.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.ATTENDANCENOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
}
