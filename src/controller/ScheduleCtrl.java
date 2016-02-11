package controller;

import java.util.Date;

import model.Branch;
import model.Classroom;
import model.Course;
import model.Schedule;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ClassroomDAO;
import dataManager.CourseDAO;
import dataManager.ScheduleDAO;
import dataManager.TeacherDAO;
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
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if(teacher != null){
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					String name = (String) inputJson.get(Key.NAME);
					String description = (String) inputJson.get(Key.DESCRIPTION);
					long dayOfWeek = (long) inputJson.get(Key.DAYOFWEEK);
					Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
					Date scheduleEndDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULEENDDATE));
//					long recFrequency = (long) inputJson.get(Key.RECFREQUENCY);
					long duration = (long) inputJson.get(Key.DURATION);
					
					Schedule schedule = new Schedule(name, description, scheduleStartDate, 
														scheduleEndDate, duration, course, teacher);
					ScheduleDAO.addSchedule(schedule);
					
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, schedule.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
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
				String name = (String) inputJson.get(Key.NAME);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				long dayOfWeek = (long) inputJson.get(Key.DAYOFWEEK);
				Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
				Date scheduleEndDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULEENDDATE));
				
				schedule.setName(name);
				schedule.setDescription(description);
				schedule.setDayOfWeek(dayOfWeek);
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
	//Get schedules by branch
	public static JSONObject getSchedulesByBranch (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Branch branch = BranchDAO.getBranchById((long)inputJson.get(Key.BRANCHID));
			if(branch != null){
				JSONArray salaryArr = new JSONArray();
				for (Teacher t : TeacherDAO.getTeachersByBranch(branch)){
					for (Schedule s : ScheduleDAO.getSchedulesByTeacher(t)) {
						salaryArr.add(s.toJsonSimple());
					}	
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
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
	
	//Get schedule by teacher id
	public static JSONObject getSchedulesByTeacher (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long)inputJson.get(Key.TEACHERID));
			if(teacher != null){
				JSONArray salaryArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByTeacher(teacher)) {
					salaryArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get schedule by course id
	public static JSONObject getSchedulesByCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseById((long)inputJson.get(Key.COURSEID));
			if(course != null){
				JSONArray salaryArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByCourse(course)) {
					salaryArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
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
	
	//Get schedule by course name
	public static JSONObject getSchedulesByCourseName(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try{
			Course course = CourseDAO.getCourseByName((String) inputJson.get(Key.COURSENAME));
			if (course != null) {
				JSONArray scheduleArr = new JSONArray();
				for (Schedule s : ScheduleDAO.getSchedulesByCourse(course)) {
					scheduleArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleArr);
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
	
	//Get schedule by teacher id and course id
	public static JSONObject getSchedulesByTeacherAndCourse (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long)inputJson.get(Key.TEACHERID));
			if(teacher != null){
				Course course = CourseDAO.getCourseById((long)inputJson.get(Key.COURSEID));
				if(course != null){
					JSONArray salaryArr = new JSONArray();
					for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourse(teacher, course)) {
						salaryArr.add(s.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, salaryArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Create schedule and schedule events together
	public static JSONObject createSchedulesAndScheduleEvents(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		JSONArray scheduleArr = new JSONArray();
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if(teacher != null){
				int count = 0;
				JSONArray inputCourses = (JSONArray) inputJson.get(Key.COURSES);
				for (Object c : inputCourses) {
					JSONObject messageJson = new JSONObject();
					Course course = CourseDAO.getCourseById(Long.parseLong((String)c));
					if(course != null){
						String name = (String) inputJson.get(Key.NAME);
						String description = (String) inputJson.get(Key.DESCRIPTION);
						Date scheduleStartDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULESTARTDATE));
						Date scheduleEndDate = Config.SDF.parse((String) inputJson.get(Key.SCHEDULEENDDATE));
						long duration = (long) inputJson.get(Key.DURATION);
						
						
						Schedule schedule = new Schedule(name, description, scheduleStartDate, 
															scheduleEndDate, duration, course, teacher);
						ScheduleDAO.addSchedule(schedule);
						messageJson.put(Key.SCHEDULE, schedule.toJson());
						
						Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
						if (classroom != null) {
							JSONObject scheduleEventsObj = ScheduleEventCtrl.createScheduleEventsBySchedule(schedule, classroom);
							count ++;
							System.out.println("This is the count :");
							System.out.println(count);
							messageJson.put(Key.SCHEDULEEVENTS, scheduleEventsObj);
						} else {
							messageJson.put(Key.STATUS, Value.FAIL);
							messageJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
						}
						scheduleArr.add(messageJson);
					} else {
						returnJson.put(Key.STATUS, Value.FAIL);
						returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, scheduleArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get courses schedule by teacher id
//	public static JSONObject getCoursesScheulesByTeacher (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
//			Teacher teacher = TeacherDAO.getTeacherById((long)inputJson.get(Key.TEACHERID));
//			if(teacher != null){
//				JSONArray salaryArr = new JSONArray();
//				for (Schedule s : ScheduleDAO.getCoursesScheduleByTeacherCourse(teacher) {
//					salaryArr.add(s.toJson());
//				}
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, salaryArr);
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL)  ;
//			returnJson.put(Key.MESSAGE, e);
//		}
//		
//		return returnJson;
//	}
	
	
	//Get schedule by student
//	public static JSONObject getSchedulesByStudent (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
//			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
//			if(student != null){
//				JSONArray salaryArr = new JSONArray();
//				for (Schedule s : ScheduleDAO.getSchedulesByStudent(student)) {
//					salaryArr.add(s.toJson());
//				}
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, salaryArr);
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL)  ;
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
//	public static JSONObject getSchedulesByStudentAndTeacherCourse (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
//			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
//			if(student != null){
//				TeacherCourse teacherCourse = TeacherCourseDAO.getTeacherCourseById((long)inputJson.get(Key.TEACHERCOURSEID));
//				if(teacherCourse != null){
//					JSONArray salaryArr = new JSONArray();
//					for (Schedule s : ScheduleDAO.getSchedulesByStudentAndTeacherCourse(student, teacherCourse)) {
//						salaryArr.add(s.toJson());
//					}
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, salaryArr);
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.TEACHERCOURSENOTEXIST);
//				}
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL)  ;
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
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
	
	//TODO get through classroom
//	public static JSONObject getSchedulesByBranch (JSONObject inputJson){
//		JSONObject returnJson = new JSONObject();
//		try{
//			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
//			if(branch != null){
//				ArrayList<Student> crList = StudentDAO.getStudentsByBranch(branch);
//				if(!crList.isEmpty()){
//					JSONArray scheduleArr = new JSONArray();
//					for (Student cr : crList){
//						for (Schedule s : ScheduleDAO.getSchedulesByStudent(cr)) {
//							scheduleArr.add(s.toJson());
//						}
//					}
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, scheduleArr);
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//				}
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL)  ;
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
}
