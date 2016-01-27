package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Attendance;
import model.Branch;
import model.Classroom;
import model.Course;
import model.Schedule;
import model.ScheduleEvent;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.BranchDAO;
import dataManager.ClassroomDAO;
import dataManager.CourseDAO;
import dataManager.ScheduleDAO;
import dataManager.ScheduleEventDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class ScheduleEventCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createScheduleEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			if (classroom != null) {
				Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if (schedule != null) {
					Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTDATE));
					Date planEndDate = Config.SDF.parse((String) inputJson.get(Key.PLANENDDATE));
					
					ScheduleEvent scheduleEvent = new ScheduleEvent(planStartDate, planEndDate, schedule, classroom);
					ScheduleEventDAO.addScheduleEvent(scheduleEvent);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleEvent.toJson());
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

	// Get scheduleEvent by id
	public static JSONObject getScheduleEventById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.SCHEDULEEVENTID));
			if (scheduleEvent != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEvent.toJsonStrong());
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

	// Get all scheduleEvent
	public static JSONObject getAllScheduleEvents() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray scheduleEventJArr = new JSONArray();
			for (ScheduleEvent a : ScheduleEventDAO.getAllScheduleEvents()) {
				scheduleEventJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, scheduleEventJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	
	//TODO complete the update function if needed
//	public static JSONObject updateScheduleEvent(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.BILLID));
//			if (scheduleEvent != null) {
//				
//				scheduleEvent.setClassroom(classroom);
//				ScheduleEventDAO.modifyScheduleEvent(scheduleEvent);
//				
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, scheduleEvent.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.CLASSROOMSCHEDULENOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}

	public static JSONObject deleteScheduleEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			ScheduleEvent scheduleEvent = ScheduleEventDAO.getScheduleEventById((long) inputJson.get(Key.BILLID));
			if (scheduleEvent != null) {
				scheduleEvent.setObjStatus(Value.DELETED);
				ScheduleEventDAO.modifyScheduleEvent(scheduleEvent);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEvent.toJson());
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
	//create schedules and schedule events
	public static JSONObject createScheduleEventsBySchedule(Schedule schedule, Classroom classroom) {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = new JSONArray();
		try {
			if (schedule != null) {
				int duration = (int) schedule.getDuration();
				Date startDate = schedule.getScheduleStartDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				Date compareDate = calendar.getTime();
				Date scheduleEndDate = schedule.getScheduleEndDate();
				calendar.add(Calendar.HOUR_OF_DAY, duration);
				Date date = calendar.getTime();
				//continue looping until the end date is been surpass
				while(compareDate.compareTo(scheduleEndDate)<=0){
					ScheduleEvent scheduleEvent = new ScheduleEvent(compareDate, date, schedule, classroom);
					ScheduleEventDAO.addScheduleEvent(scheduleEvent);
					returnArray.add(scheduleEvent.toJsonSimple());
					compareDate = Config.addDaysToDate(compareDate, schedule.getRecFrequency());
					date = Config.addDaysToDate(date, schedule.getRecFrequency());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.SCHEDULEEVENTS, returnArray);
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
	
	// Get scheduleEvents by classroom
	public static JSONObject getScheduleEventsByClassroom(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			if (classroom != null) {
				JSONArray scheduleEventArr = new JSONArray();
				for (ScheduleEvent b : ScheduleEventDAO.getScheduleEventsByClassroom(classroom)) {
					scheduleEventArr.add(b.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEventArr);
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
	
	// Get scheduleEvents by schedule
	public static JSONObject getScheduleEventsBySchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if (schedule != null) {
				JSONArray scheduleEventArr = new JSONArray();
				for (ScheduleEvent ss : ScheduleEventDAO.getScheduleEventsBySchedule(schedule)) {
					scheduleEventArr.add(ss.toJsonAttendances());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEventArr);
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
	
	// Get scheduleEvents by classroom and schedule
	public static JSONObject getScheduleEventsByClassroomAndSchedule(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			if (classroom != null) {
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
				if (schedule != null) {
					JSONArray scheduleEventArr = new JSONArray();
					for (ScheduleEvent ss : ScheduleEventDAO.getScheduleEventsByClassroomAndSchedule(classroom, schedule)) {
						scheduleEventArr.add(ss.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleEventArr);
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
	
	// Get schedule events by branch
	public static JSONObject getScheduleEventsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray scheduleEventArr = new JSONArray();
				for (Classroom classroom : ClassroomDAO.getClassroomsByBranch(branch)) {
					for (ScheduleEvent se : ScheduleEventDAO.getScheduleEventsByClassroom(classroom)){
						scheduleEventArr.add(se.toJsonStrong());
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleEventArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get schedule events by branch
		public static JSONObject getScheduleEventsByCourse(JSONObject inputJson) {
			JSONObject returnJson = new JSONObject();
			try {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if (course != null) {
					JSONArray scheduleEventArr = new JSONArray();
					for (Schedule schedule : ScheduleDAO.getSchedulesByCourse(course)) {
						for (ScheduleEvent se : ScheduleEventDAO.getScheduleEventsBySchedule(schedule)){
							scheduleEventArr.add(se.toJsonStrong());
						}
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleEventArr);
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
	
	// Get schedule events by student
	public static JSONObject getScheduleEventsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray scheduleEventArr = new JSONArray();
				for (Attendance a : AttendanceDAO.getAttendancesByStudent(student)) {
					scheduleEventArr.add(a.getScheduleEvent().toJsonStrong());
//					scheduleEventArr.add(a.toJsonScheduleEvent());
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

}
