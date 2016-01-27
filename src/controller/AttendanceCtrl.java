package controller;

import java.util.Calendar;
import java.util.Date;

import model.Attendance;
import model.Student;
import model.Parent;
import model.Schedule;
import model.ScheduleEvent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.ScheduleDAO;
import dataManager.StudentDAO;
import dataManager.ScheduleEventDAO;
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
	//standard way of creating one attendance
	public static JSONObject createAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
			if (scheduleEvent != null) {
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if(student != null){
					Attendance attendance = new Attendance(scheduleEvent, student);
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
				returnJson.put(Key.MESSAGE, Message.SCHEDULEEVENTNOTEXIST);
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
//				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//				if(student != null){
//					attendance.setStudent(student);
//				}
//				ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
//				if(scheduleEvent != null){
//					attendance.setScheduleEvent(scheduleEvent);
//				}else{
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.SCHEDULEEVENTNOTEXIST);
//				}
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
	//Create attendance based on the start and end date of a schedule
	public static JSONObject createAttendancesForStudentBySchedule(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if (schedule != null) {
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if(student != null){
					JSONArray attendancesArr = new JSONArray();
					for (ScheduleEvent se : ScheduleEventDAO.getScheduleEventsBySchedule(schedule)) {
						//loop through to add student to each scheduleEvent
						Attendance attendance = new Attendance(se, student);
						AttendanceDAO.addAttendance(attendance);
						attendancesArr.add(attendance.toJson());
						se.setStudentAmount(se.getStudentAmount()+1);
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendancesArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
			}
		} catch(Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get attendances by scheduleEvent
	public static JSONObject getAttendancesByScheduleEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
			if (scheduleEvent != null) {
				JSONArray attendanceArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesByScheduleEvent(scheduleEvent)) {
					if(a.getActualStartDate() != null){
						attendanceArr.add(a.toJsonMark());
					} else {
						attendanceArr.add(a.toJson());
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendanceArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SCHEDULEEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get schedule events by student
	public static JSONObject getAttendancesByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray scheduleEventArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesByStudent(student)) {
	//						scheduleEventArr.add(a.getScheduleEvent().toJsonStrong());
					scheduleEventArr.add(a.toJsonScheduleEvent());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEventArr);
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
	
	//batch update attendances
	public static JSONObject updateAttendances(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray attendanceArr = (JSONArray) inputJson.get(Key.ATTENDANCES);
			if (attendanceArr != null && attendanceArr.size()!=0) {
				JSONArray returnArr = new JSONArray();
				for(int i = 0; i < attendanceArr.size(); i++){
					JSONObject attendanceObj = (JSONObject) attendanceArr.get(i);
					//TODO may need to add in a checker to verify the inputs are not empty or null
					Attendance attendance = AttendanceDAO.getAttendanceById((long) attendanceObj.get(Key.ATTENDANCEID));
					Date actualStartDate = Config.SDF.parse((String) attendanceObj.get(Key.ACTUALSTARTDATE));
					long attendanceStatus = (long) attendanceObj.get(Key.ATTENDANCESTATUS);
					
					attendance.setActualStartDate(actualStartDate);
					attendance.setAttendanceStatus(attendanceStatus);
					
					AttendanceDAO.modifyAttendance(attendance);
					
					returnArr.add(attendance.toJsonMark());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, returnArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ATTENDANCESISEMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateAttendanceWithQR(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		JSONObject attendanceObj = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
				if(scheduleEvent != null){
					
					Attendance attendance = AttendanceDAO.getAttendanceByStudentAndScheduleEvent(student, scheduleEvent);
					Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
					long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);

					attendance.setActualStartDate(actualStartDate);
					attendance.setAttendanceStatus(attendanceStatus);
					
					AttendanceDAO.modifyAttendance(attendance);
					
					if(attendance.getActualStartDate() != null){
						attendanceObj = attendance.toJson();
					} else {
						attendanceObj = attendance.toJson();
					}
					
					Parent parent = student.getParent();
//					System.out.println(parent.getName());
					attendanceObj.put(Key.PARENT, parent.toJsonSimple());
					
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendanceObj);
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
	
	public static JSONObject changeScheduleEventInAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
//				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//				if(student != null){
//					attendance.setStudent(student);
//				}
				ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
				if(scheduleEvent != null){
					attendance.setScheduleEvent(scheduleEvent);
				}else{
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.SCHEDULEEVENTNOTEXIST);
				}
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
	
	// Get attendances by student and scheduleEvent
//		public static JSONObject getAttendancesByStudentAndScheduleEvent(JSONObject inputJson) {
//			JSONObject returnJson = new JSONObject();
//			try {
//				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//				if (student != null) {
//					ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
//					if(scheduleEvent != null){
//						JSONArray attendanceArr = new JSONArray();
//						for (Attendance a : AttendanceDAO.getAttendancesByStudentAndScheduleEvent(student, scheduleEvent)) {//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
//							if(a.getActualStartDate() != null){
//								attendanceArr.add(a.toJsonMark());
//							} else {
//								attendanceArr.add(a.toJson());
//							}
//							//the below line fetched everything do not return actual start date even if there is one.
////							attendanceArr.add(a.toJson());
//						}
//						returnJson.put(Key.STATUS, Value.SUCCESS);
//						returnJson.put(Key.MESSAGE, attendanceArr);
//					} else {
//						returnJson.put(Key.STATUS, Value.FAIL);
//						returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
//					}
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, e);
//			}
//			return returnJson;
//		}
}
