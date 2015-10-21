package controller;

import java.util.Date;

import model.Classroom;
import model.Schedule;
import model.TeacherCourse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ClassroomDAO;
import dataManager.ScheduleDAO;
import dataManager.TeacherCourseDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class ScheduleCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createSchedule(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			if(classroom != null){
				TeacherCourse teachercourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
				if(teachercourse != null){
					Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANNEDSTARTDATE));
					
					Schedule schedule = new Schedule(planStartDate, teachercourse, classroom);
					ScheduleDAO.addSchedule(schedule);
					
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, schedule.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get schedule by id
	public static JSONObject getScheduleById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long scheduleId = (long)inputJson.get(Key.SCHEDULEID);
			Schedule schedule = ScheduleDAO.getScheduleById(scheduleId);
			if(schedule != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				//TODO shall i return strong here? need to test out the strong result
//				returnJson.psut(Key.MESSAGE, schedule.toJsonStrong());
				returnJson.put(Key.MESSAGE, schedule.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all schedule
	//TODO later may need to add in get all schedule based on a particular date
	public static JSONObject getAllSchedules(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray scheduleJArr = new JSONArray();
			for(Schedule a: ScheduleDAO.getAllSchedules()){
				scheduleJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, scheduleJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateSchedule(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if(schedule != null){
				Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
				if(classroom != null){
					schedule.setClassroom(classroom);
				}
				TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
				if(teacherCourse != null){
					schedule.setTeacherCourse(teacherCourse);
				}
				Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANNEDSTARTDATE));
				schedule.setPlanStartDate(planStartDate);
				
				ScheduleDAO.modifySchedule(schedule);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, schedule.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject deleteSchedule(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Schedule schedule = ScheduleDAO.getScheduleById((long) inputJson.get(Key.SCHEDULEID));
			if(schedule != null){
				schedule.setObjStatus(Value.DELETED);
				ScheduleDAO.modifySchedule(schedule);
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, schedule.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SCHEDULENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//features
	
	//TODO take note that if we were to get the attendance of all the classroom or other attributes
	//that first need to get the schedule, use toStrong method of schedule in the attendance ctrl.
	
	//Get schedule by classroom
	public static JSONObject getSchedulesByClassroom (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Classroom classroom = ClassroomDAO.getClassroomById((long)inputJson.get(Key.CLASSROOMID));
			if(classroom != null){
				JSONArray salaryArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByClassroom(classroom)) {
					salaryArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject getSchedulesByTeacherCourse (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
			if(teacherCourse != null){
				JSONArray salaryArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourse(teacherCourse)) {
					salaryArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject getSchedulesByClassroomAndTeacherCourse (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Classroom classroom = ClassroomDAO.getClassroomById((long)inputJson.get(Key.CLASSROOMID));
			if(classroom != null){
				TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
				if(teacherCourse != null){
					JSONArray salaryArr = new JSONArray();
					for (Schedule s : ScheduleDAO.getSchedulesByClassroomAndTeacherCourse(classroom, teacherCourse)) {
						salaryArr.add(s.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, salaryArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
