package controller;

import java.util.Date;

import model.Attendance;
import model.Classroom;
import model.Parent;
import model.Schedule;
import model.Classroom;
import model.TeacherCourse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.ClassroomDAO;
import dataManager.ScheduleDAO;
import dataManager.ClassroomDAO;
import dataManager.TeacherCourseDAO;
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
				Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
				if (classroom != null) {
//					Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
//					long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);
					
					Attendance attendance = new Attendance(schedule, classroom);
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
		JSONObject attendanceObj = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
				Date actualStartDate = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTDATE));
				long attendanceStatus = (long) inputJson.get(Key.ATTENDANCESTATUS);
//				Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.STUDENTID));
//				if(classroom != null){
//					attendance.setClassroom(classroom);
//				}
//				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
//				if(schedule != null){
//					attendance.setSchedule(schedule);
//				}
				attendance.setActualStartDate(actualStartDate);
				attendance.setAttendanceStatus(attendanceStatus);
				
				AttendanceDAO.modifyAttendance(attendance);

				attendanceObj = attendance.toJson();
				Classroom classroom = attendance.getClassroom();
				
//				Parent parent = classroom.getParent();
//				System.out.println(parent.getName());
//				attendanceObj.put(Key.PARENT, parent.toJson());
				
				
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendanceObj);
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
				returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get attendances by classroom
	public static JSONObject getAttendancesByClassroom(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.STUDENTID));
			if (classroom != null) {
				JSONArray attendanceArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesByClassroom(classroom)) {
					attendanceArr.add(a.toScheduleJson());
//					if(a.getActualStartDate() != null){
//						attendanceArr.add(a.toJsonMark());
//					} else {
//						attendanceArr.add(a.toJson());
//					}
////					attendanceArr.add(a.toJson());
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
	
	// Get attendances by classroom and schedule
	public static JSONObject getAttendancesByClassroomAndSchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.STUDENTID));
			if (classroom != null) {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if(schedule != null){
					JSONArray attendanceArr = new JSONArray();
					for (Attendance a : AttendanceDAO.getAttendancesByClassroomAndSchedule(classroom, schedule)) {//TODO after validating the above get attendance by id method, change the code below to match the fetching logic
						if(a.getActualStartDate() != null){
							attendanceArr.add(a.toJsonMark());
						} else {
							attendanceArr.add(a.toJson());
						}
						//the below line fetched everything do not return actual start date even if there is one.
//						attendanceArr.add(a.toJson());
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
					
					returnArr.add(attendance.toScheduleJsonMark());
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
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.STUDENTID));
			if (classroom != null) {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if(schedule != null){
					Attendance attendance = AttendanceDAO.getAttendanceByClassroomAndSchedule(classroom, schedule);
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
					
					Parent parent = classroom.getParent();
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
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
//	public static JSONObject getAttendancesBySchedules (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
////			JSONObject resultObj = ScheduleCtrl.getSchedulesByTeacherCourseAndPlanStartDate(inputJson);
//			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
//			if(teacherCourse != null){
//				//TODO do i need to add in checker to verified the date?
//				JSONArray scheduleArr = new JSONArray();
//				Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
//				for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourseAndPlanStartDate(teacherCourse, planStartDate)) {
//					//TODO can i use toJsonStrong method to retrieve the attendance at once also? so only one servlet call will do
//					scheduleArr.add(s.toJsonStrong());
////					salaryArr.add(s.toJson());
//				}
//				//TODO add in a checker for the scheduleArr
//				
//				JSONArray attendanceArr = new JSONArray();
//				//loop through the scheduleArr to find all the attendance
//				scheduleArr
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
