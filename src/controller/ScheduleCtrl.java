package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Branch;
import model.Student;
import model.Course;
import model.Schedule;
import model.TeacherCourse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.StudentDAO;
import dataManager.CourseDAO;
import dataManager.ScheduleDAO;
import dataManager.TeacherCourseDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

/**
 * @author RaySong
 */

public class ScheduleCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createSchedule(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if(student != null){
				TeacherCourse teachercourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
				if(teachercourse != null){
					String name = (String) inputJson.get(Key.NAME);
					String description = (String) inputJson.get(Key.DESCRIPTION);
					Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
					Date scheduleEndDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULEENDDATE));
					
					Schedule schedule = new Schedule(name, description, scheduleStartDate, scheduleEndDate, teachercourse, student);
					ScheduleDAO.addSchedule(schedule);
					
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, schedule.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
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
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if(student != null){
					schedule.setStudent(student);
				}
				TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long) inputJson.get(Key.TEACHERCOURSEID));
				if(teacherCourse != null){
					schedule.setTeacherCourse(teacherCourse);
				}
				String name = (String) inputJson.get(Key.NAME);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
				Date scheduleEndDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULEENDDATE));
				
				schedule.setName(name);
				schedule.setDescription(description);
				schedule.setScheduleStartDate(scheduleStartDate);
				schedule.setScheduleEndDate(scheduleEndDate);
				
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
	
	//Get schedule by student
	public static JSONObject getSchedulesByStudent (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
			if(student != null){
				JSONArray salaryArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByStudent(student)) {
					salaryArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
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
	
	public static JSONObject getSchedulesByStudentAndTeacherCourse (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
			if(student != null){
				TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
				if(teacherCourse != null){
					JSONArray salaryArr = new JSONArray();
					for (Schedule s : ScheduleDAO.getSchedulesByStudentAndTeacherCourse(student, teacherCourse)) {
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
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
//	public static JSONObject getScheduleByTeacherCourseAndScheduleStartDate (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
//			TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
//			if(teacherCourse != null){
////				JSONArray scheduleArr = new JSONArray();
//				Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
//				if(scheduleStartDate != null){
//					Schedule schedule = ScheduleDAO.getScheduleByTeacherCourseAndScheduleStartDate(teacherCourse, scheduleStartDate);
//					//TODO can i use toJsonStrong method to retrieve the attendance at once also? so only one servlet call will do
////					scheduleArr.add(s.toJsonStrong());
////					salaryArr.add(s.toJson());
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, schedule.toJsonStrong());
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.SCHEDULESTARTDATEEMPTY);
//				}
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

	
	//TODO should return schedules list of attendances list
	public static JSONObject getSchedulesByCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			if (course != null) {
				ArrayList<TeacherCourse> tcList = TeacherCourseDAO.getTeacherCoursesByCourse(course);
				if(!tcList.isEmpty()){
					JSONArray scheduleArr = new JSONArray();
					for (TeacherCourse tc : tcList) {
						for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourse(tc)) {
							scheduleArr.add(s.toJson());
						}
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
			} 
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject getSchedulesByCourseName(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseByName((String) inputJson.get(Key.COURSENAME));
			if (course != null) {
				ArrayList<TeacherCourse> tcList = TeacherCourseDAO.getTeacherCoursesByCourse(course);
				if(!tcList.isEmpty()){
					JSONArray scheduleArr = new JSONArray();
					for (TeacherCourse tc : tcList) {
						for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourse(tc)) {
							scheduleArr.add(s.toJson());
						}
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
			} 
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject getSchedulesByBranch (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if(branch != null){
				ArrayList<Student> crList = StudentDAO.getStudentsByBranch(branch);
				if(!crList.isEmpty()){
					JSONArray scheduleArr = new JSONArray();
					for (Student cr : crList){
						for (Schedule s : ScheduleDAO.getSchedulesByStudent(cr)) {
							scheduleArr.add(s.toJson());
						}
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, scheduleArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
