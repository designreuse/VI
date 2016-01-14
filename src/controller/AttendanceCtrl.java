package controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.CLASSROOMID));
				if(student != null){
					Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
					Date planEndDate = Config.SDF.parse((String) inputJson.get(Key.PLANENDDATE));
					
					Attendance attendance = new Attendance(scheduleEvent, student);
					AttendanceDAO.addAttendance(attendance);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					//use toJson here because it is not marked yet
					returnJson.put(Key.MESSAGE, attendance.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
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
				Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
				Date planEndDate = Config.SDF.parse((String) inputJson.get(Key.PLANENDDATE));
				Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
				Date actualEndDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALENDDATE));
				long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);
//				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//				if(student != null){
//					attendance.setStudent(student);
//				}
//				ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
//				if(scheduleEvent != null){
//					attendance.setScheduleEvent(scheduleEvent);
//				}
				attendance.setActualStartDate(actualStartDate);
				attendance.setActualEndDate(actualEndDate);
				attendance.setPlanStartDate(planStartDate);
				attendance.setPlanEndDate(planEndDate);
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
	public static JSONObject createAttendancesBySchedule(ScheduleEvent scheduleEvent, Student student){
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = new JSONArray();
		try{
			Schedule schedule = scheduleEvent.getSchedule();
			int duration = (int) schedule.getDuration();
			Date startDate = schedule.getScheduleStartDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			Date compareDate = calendar.getTime();
			Date scheduleEndDate = schedule.getScheduleEndDate();
			//continue looping until the end date is been surpass
			while(compareDate.compareTo(scheduleEndDate)<=0){
				calendar.add(Calendar.HOUR_OF_DAY, duration);
				Date date = calendar.getTime();
				
				Attendance attendance = new Attendance(compareDate, date, scheduleEvent, student);
				AttendanceDAO.addAttendance(attendance);
				returnArray.add(attendance.toJsonSimple());
				compareDate = Config.addDaysToDate(compareDate, Config.ONEWEEK);
			}
			returnJson.put(Key.START, Value.SUCCESS);
			returnJson.put(Key.ATTENDANCES, returnArray);
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
//					attendanceArr.add(a.toJson());
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

	
	// Get attendances by student and scheduleEvent
//	public static JSONObject getAttendancesByStudentAndScheduleEvent(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
//			if (student != null) {
//				ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
//				if(scheduleEvent != null){
//					JSONArray attendanceArr = new JSONArray();
//					for (Attendance a : AttendanceDAO.getAttendancesByStudentAndScheduleEvent(student, scheduleEvent)) {//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
//						if(a.getActualStartDate() != null){
//							attendanceArr.add(a.toJsonMark());
//						} else {
//							attendanceArr.add(a.toJson());
//						}
//						//the below line fetched everything do not return actual start date even if there is one.
////						attendanceArr.add(a.toJson());
//					}
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, attendanceArr);
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
//				}
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
	//batch update attendances
	public static JSONObject updateAttendances(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray attendanceArr = (JSONArray) inputJson.get(Key.ATTENDANCES);
			if (attendanceArr != null) {
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
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.CLASSROOMID));
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
						attendanceObj = attendance.toJsonMark();
					} else {
						attendanceObj = attendance.toJson();
					}
					
					Parent parent = student.getParent();
//					System.out.println(parent.getName());
					attendanceObj.put(Key.PARENT, parent.toJson());
					
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendanceObj);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
//	public static JSONObject getAttendancesByScheduleEvents (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
////			JSONObject resultObj = ScheduleEventCtrl.getScheduleEventsByTeacherCourseAndPlanStartDate(inputJson);
//			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
//			if(teacherCourse != null){
//				//TODO do i need to add in checker to verified the date?
//				JSONArray scheduleEventArr = new JSONArray();
//				Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
//				for (ScheduleEvent s : ScheduleEventDAO.getScheduleEventsByTeacherCourseAndPlanStartDate(teacherCourse, planStartDate)) {
//					//TODO can i use toJsonStrong method to retrieve the attendance at once also? so only one servlet call will do
//					scheduleEventArr.add(s.toJsonStrong());
////					salaryArr.add(s.toJson());
//				}
//				//TODO add in a checker for the scheduleEventArr
//				
//				JSONArray attendanceArr = new JSONArray();
//				//loop through the scheduleEventArr to find all the attendance
//				scheduleEventArr
//				
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, attendanceArr);
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL)  ;
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
}
