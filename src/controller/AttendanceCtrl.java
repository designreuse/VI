package controller;

import java.util.Date;

import model.Attendance;
import model.Schedule;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.ScheduleDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

/**
 * @author RaySong
 */
public class AttendanceCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if (schedule != null) {
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if (student != null) {
//					Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
//					long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);
					
					Attendance attendance = new Attendance(schedule, student);
					AttendanceDAO.addAttendance(attendance);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					//use toJson here because it is not marked yet
					returnJson.put(Key.MESSAGE, attendance.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
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

	// Get attendance by id
	public static JSONObject getAttendanceById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
				//TODO, verified if the next line works
				if(attendance.getActualStartDate() != null){
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendance.toJsonMark());
				} else {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendance.toJson());
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ATTENDANCENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all attendance (not very useful, try to get attendance at least with some constraints.)
	public static JSONObject getAllAttendances() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray attendanceJArr = new JSONArray();
			for (Attendance a : AttendanceDAO.getAllAttendances()) {
				attendanceJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, attendanceJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
				Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
				long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if(student != null){
					attendance.setStudent(student);
				}
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if(schedule != null){
					attendance.setSchedule(schedule);
				}
				attendance.setActualStartDate(actualStartDate);
				attendance.setAttendanceStatus(attendanceStatus);
				
				AttendanceDAO.modifyAttendance(attendance);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ATTENDANCENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
				attendance.setObjStatus(Value.DELETED);
				AttendanceDAO.modifyAttendance(attendance);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ATTENDANCENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Get attendances by schedule
	public static JSONObject getAttendancesBySchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if (schedule != null) {
				JSONArray attendanceArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesBySchedule(schedule)) {
					//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
//					if(a.getActualStartDate() != null){
//						attendanceArr.add(a.toJsonMark());
//					} else {
//						attendanceArr.add(a.toJson());
//					}
					attendanceArr.add(a.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendanceArr);
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

	// Get attendances by student
	public static JSONObject getAttendancesByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray attendanceArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesByStudent(student)) {
					//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
//					if(a.getActualStartDate() != null){
//						attendanceArr.add(a.toJsonMark());
//					} else {
//						attendanceArr.add(a.toJson());
//					}
					attendanceArr.add(a.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendanceArr);
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
	
	// Get attendances by student and schedule
	public static JSONObject getAttendancesByStudentAndSchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if(schedule != null){
					JSONArray attendanceArr = new JSONArray();
					for (Attendance a : AttendanceDAO.getAttendancesByStudentAndSchedule(student, schedule)) {
						//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
//							if(a.getActualStartDate() != null){
//								attendanceArr.add(a.toJsonMark());
//							} else {
//								attendanceArr.add(a.toJson());
//							}
						
						//right now, everything fetched do not return actual start date even if there is one.
						attendanceArr.add(a.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendanceArr);
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
}
