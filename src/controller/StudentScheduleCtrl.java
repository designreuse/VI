package controller;

import java.util.Date;

import model.Schedule;
import model.StudentSchedule;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ScheduleDAO;
import dataManager.StudentScheduleDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class StudentScheduleCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createStudentSchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if (schedule != null) {
					StudentSchedule studentSchedule = new StudentSchedule(student, schedule);
					StudentScheduleDAO.addStudentSchedule(studentSchedule);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, studentSchedule.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
				}
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

	// Get studentSchedule by id
	public static JSONObject getStudentScheduleById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			StudentSchedule studentSchedule = StudentScheduleDAO.getStudentScheduleById((long) inputJson.get(Key.STUDENTSCHEDULEID));
			if (studentSchedule != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentSchedule.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTSCHEDULENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all studentSchedule
	public static JSONObject getAllStudentSchedules() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray studentScheduleJArr = new JSONArray();
			for (StudentSchedule a : StudentScheduleDAO.getAllStudentSchedules()) {
				studentScheduleJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, studentScheduleJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	
	//TODO complete the update function if needed
//	public static JSONObject updateStudentSchedule(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			StudentSchedule studentSchedule = StudentScheduleDAO.getStudentScheduleById((long) inputJson.get(Key.BILLID));
//			if (studentSchedule != null) {
//				
//				studentSchedule.setStudent(student);
//				StudentScheduleDAO.modifyStudentSchedule(studentSchedule);
//				
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, studentSchedule.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.STUDENTSCHEDULENOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}

	public static JSONObject deleteStudentSchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			StudentSchedule studentSchedule = StudentScheduleDAO.getStudentScheduleById((long) inputJson.get(Key.BILLID));
			if (studentSchedule != null) {
				studentSchedule.setObjStatus(Value.DELETED);
				StudentScheduleDAO.modifyStudentSchedule(studentSchedule);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentSchedule.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BILLNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	// Get studentSchedules by student
	public static JSONObject getStudentSchedulesByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray studentScheduleArr = new JSONArray();
				for (StudentSchedule b : StudentScheduleDAO.getStudentSchedulesByStudent(student)) {
					studentScheduleArr.add(b.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentScheduleArr);
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
	
	// Get studentSchedules by schedule
		public static JSONObject getStudentSchedulesBySchedule(JSONObject inputJson) {
			JSONObject returnJson = new JSONObject();
			try {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if (schedule != null) {
					JSONArray studentScheduleArr = new JSONArray();
					for (StudentSchedule ss : StudentScheduleDAO.getStudentSchedulesBySchedule(schedule)) {
						studentScheduleArr.add(ss.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, studentScheduleArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, e);
			}
			return returnJson;
		}

}
