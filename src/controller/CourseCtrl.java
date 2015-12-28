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
			String name = (String) inputJson.get(Key.NAME);
			String description = (String) inputJson.get(Key.DESCRIPTION);
			String courseCost = (String) inputJson.get(Key.COURSECOST);
			long courseCapacity = (long) inputJson.get(Key.COURSECAPACITY);

			Course course = new Course(name, description, courseCost, courseCapacity);
			CourseDAO.addCourse(course);

			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, course.toJson());
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
				String courseCost = (String) inputJson.get(Key.COURSECOST);
				long courseCapacity = (long) inputJson.get(Key.COURSECAPACITY);

				course.setName(name);
				course.setDescription(description);
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
	
}
