package controller;

import model.Admin;
import model.Course;
import model.Result;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
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
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					double resultValue = Double.valueOf((String) inputJson.get(Key.RESULTVALUE));
					
					Result result = new Result(resultValue, course, student);
					ResultDAO.addResult(result);
					
					returnJson.put(Key.STATUS, Value.SUCCESS)  ;
					returnJson.put(Key.MESSAGE, result.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
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
	
	//Get result by id
	public static JSONObject getResultById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long resultId = (long)inputJson.get(Key.RESULTID);
			Result result = ResultDAO.getResultById(resultId);
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
				double resultValue = Double.valueOf((String) inputJson.get(Key.RESULTVALUE));
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				
				result.setResultValue(resultValue);
				result.setStudent(student);
				result.setCourse(course);
				
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
}
