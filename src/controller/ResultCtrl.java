package controller;

import model.Course;
import model.Result;
import model.Student;
import model.TeacherCourse;
import model.TeacherStudentCourse;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
import dataManager.TeacherCourseDAO;
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
				String name = (String) inputJson.get(Key.NAME);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				long courseLevel =  (long) inputJson.get(Key.COURSELEVEL);
				long bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
				String bookletName = (String) inputJson.get(Key.BOOKLETNAME);
				String bookletDescription = (String) inputJson.get(Key.BOOKLETDESCRIPTION);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				Date resultDate = Config.SDF.parse((String) inputJson.get(Key.RESULTDATE));
				long pointAmount = (long) inputJson.get(Key.POINTAMOUNT);
				
				Result result = new Result(name, description, courseLevel, bookletLevel,bookletName, 
											bookletDescription, resultValue, resultDate, pointAmount, teacherStudentCourse);
				ResultDAO.addResult(result);
				
				returnJson.put(Key.STATUS, Value.SUCCESS) ;
				returnJson.put(Key.MESSAGE, result.toJson());
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
				String name = (String) inputJson.get(Key.NAME);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				long courseLevel =  (long) inputJson.get(Key.COURSELEVEL);
				long bookletLevel = (long) inputJson.get(Key.BOOKLETLEVEL);
				String bookletName = (String) inputJson.get(Key.BOOKLETNAME);
				String bookletDescription = (String) inputJson.get(Key.BOOKLETDESCRIPTION);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				Date resultDate = Config.SDF.parse((String) inputJson.get(Key.RESULTDATE));
				long pointAmount = (long) inputJson.get(Key.POINTAMOUNT);
				
				result.setName(name);
				result.setDescription(description);
				result.setCourseLevel(courseLevel);
				result.setBookletLevel(bookletLevel);
				result.setBookletName(bookletName);
				result.setBookletDescription(bookletDescription);
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
}
