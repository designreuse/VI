package controller;

import model.Course;
import model.Parent;
import model.Result;
import model.Student;
import model.Teacher;
import model.TeacherFeedback;
import model.TeacherStudentCourse;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ParentDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import dataManager.TeacherFeedbackDAO;
import dataManager.TeacherStudentCourseDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class ResultCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createResult(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById((long) inputJson.get(Key.TEACHERSTUDENTCOURSEID));
			if(teacherStudentCourse != null){
				long courseLevel =  (long) inputJson.get(Key.COURSELEVEL);
				long bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				Date resultDate = Config.SDF.parse((String) inputJson.get(Key.RESULTDATE));
				double pointAmount = Double.valueOf((String) inputJson.get(Key.POINTAMOUNT));
				
				Result result = new Result(courseLevel, bookletLevel,resultValue, resultDate, 
											pointAmount, teacherStudentCourse);
				ResultDAO.addResult(result);
				
				returnJson.put(Key.STATUS, Value.SUCCESS) ;
				returnJson.put(Key.MESSAGE, result.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}		
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get result by id
	public static JSONObject getResultById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Result result = ResultDAO.getResultById((long)inputJson.get(Key.RESULTID));
			if(result != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, result.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.RESULTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all result
	public static JSONObject getAllResults(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray resultJArr = new JSONArray();
			for(Result a: ResultDAO.getAllResults()){
				resultJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, resultJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateResult(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Result result = ResultDAO.getResultById((long) inputJson.get(Key.RESULTID));
			if(result != null){
				long courseLevel =  (long) inputJson.get(Key.COURSELEVEL);
				long bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				Date resultDate = Config.SDF.parse((String) inputJson.get(Key.RESULTDATE));
				long pointAmount = (long) inputJson.get(Key.POINTAMOUNT);
				
				result.setCourseLevel(courseLevel);
				result.setBookletLevel(bookletLevel);
				result.setResultValue(resultValue);
				result.setResultDate(resultDate);
				result.setPointAmount(pointAmount);
				
				ResultDAO.modifyResult(result);
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, result.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.RESULTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject deleteResult(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Result result = ResultDAO.getResultById((long) inputJson.get(Key.RESULTID));
			if(result != null){
				result.setObjStatus(Value.DELETED);
				ResultDAO.modifyResult(result);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, result.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.RESULTNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//features
	
	// Get results by course and student
	public static JSONObject getResultsByTeacherStudentCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCourseById((long) inputJson.get(Key.TEACHERSTUDENTCOURSEID));
			if (teacherStudentCourse != null) {
				JSONArray resultArr = new JSONArray();
				for (Result r : ResultDAO.getResultsByTeacherStudentCourse(teacherStudentCourse)) {
					resultArr.add(r.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, resultArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get results by student
	public static JSONObject getResultsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray resultArr = new JSONArray();
				ArrayList<TeacherStudentCourse> tscs = TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(student);
				for(TeacherStudentCourse tsc : tscs){
					for (Result r : ResultDAO.getLatesFiveResultsByTSC(tsc)) {
						resultArr.add(r.toJsonShowStudentAndTeacher());
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, resultArr);
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
	
	//Get results by parent
	public static JSONObject getResultsByParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
			if (parent != null) {
				JSONArray studentArr = new JSONArray();
				ArrayList<Student> students = StudentDAO.getStudentsByParent(parent);
				for(Student s : students){
					JSONObject studentObj = new JSONObject();
					if(s != null){
						studentObj.put(Key.STATUS, Value.SUCCESS);
						studentObj.put(Key.STUDENT, s.toJsonCourses());
						studentArr.add(studentObj);
					} else {
						studentObj.put(Key.STATUS, Value.FAIL);
						studentObj.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
						studentArr.add(studentObj);
					}
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Generate result and feedback
	public static JSONObject generateResultAndFeedback(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		JSONArray messageArray = new JSONArray();
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
			JSONArray feedbackArray = (JSONArray) inputJson.get(Key.FEEDBACKS);
			for(int i = 0; i < feedbackArray.size(); i++){
				JSONObject messageJson = new JSONObject();
				JSONObject feedback = (JSONObject) feedbackArray.get(i);
				Student student = StudentDAO.getStudentById((long) feedback.get(Key.STUDENTID));
				TeacherStudentCourse teacherStudentCourse = TeacherStudentCourseDAO.getTeacherStudentCoursesByTeacherStudentAndCourse(teacher, student, course);
				if(teacherStudentCourse != null){
					long courseLevel =  (long) feedback.get(Key.COURSELEVEL);
					long bookletLevel = (long) feedback.get(Key.BOOKLETLEVEL);
					String resultValue = (String) feedback.get(Key.RESULTVALUE);
					double pointAmount = Double.valueOf((String) feedback.get(Key.POINTAMOUNT));
					
					Result result = new Result(courseLevel, bookletLevel,resultValue, 
												pointAmount, teacherStudentCourse);
					ResultDAO.addResult(result);
					messageJson.put(Key.RESULT, result.toJsonSimple());
					
					JSONArray feedbackArr = (JSONArray) feedback.get(Key.CONTENTS);
					JSONArray contentReturn = new JSONArray();
					for (Object o : feedbackArr) {
						String content = (String) o;
						TeacherFeedback fb = new TeacherFeedback(content, result);
						TeacherFeedbackDAO.addTeacherFeedback(fb);
						contentReturn.add(fb.toJsonSimple());
					}
					messageJson.put(Key.TEACHERFEEDBACKS, contentReturn);
					
					//update student point
					Student s = teacherStudentCourse.getStudent();
					double newPoint = s.getPoints() + pointAmount;
					s.setPoints(newPoint);
					StudentDAO.modifyStudent(s);
					messageJson.put(Key.STUDENT, s.toJsonSimple());
					
					messageJson.put(Key.STATUS, Value.SUCCESS);
				} else {
					messageJson.put(Key.STATUS, Value.FAIL);
					messageJson.put(Key.MESSAGE, Message.TEACHERSTUDENTCOURSENOTEXIST + " for teacherId: " + inputJson.get(Key.TEACHERID) + ", "
							+ "studentId: " + feedback.get(Key.STUDENTID) + " and courseId: " + inputJson.get(Key.COURSEID));
				}
				messageArray.add(messageJson);
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, messageArray);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	
	
}
