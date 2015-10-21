package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.PointEventDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import model.PointEvent;
import model.Student;
import model.Teacher;
import system.Key;
import system.Message;
import system.Value;

public class PointEventCtrl {
	/**
	 * CRUD student and teacher exist
	 */
	public static JSONObject createPointEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				if (teacher != null) {
					long pointAmount = (long) inputJson.get(Key.POINTAMOUNT);
					String description = (String) inputJson.get(Key.DESCRIPTION);

					PointEvent pointEvent = new PointEvent(pointAmount, description, student, teacher);
					PointEventDAO.addPointEvent(pointEvent);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, pointEvent.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
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

	// Get pointevent by id
	public static JSONObject getPointEventById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long pointEventId = (long) inputJson.get(Key.POINTEVENTID);
			PointEvent pointEvent = PointEventDAO.getPointEventById(pointEventId);
			if (pointEvent != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, pointEvent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.POINTEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all pointevents
	public static JSONObject getAllPointEvents() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray pointEventJArr = new JSONArray();
			for (PointEvent p : PointEventDAO.getAllPointEvents()) {
				pointEventJArr.add(p.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, pointEventJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updatePointEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			PointEvent pointEvent = PointEventDAO.getPointEventById((long) inputJson.get(Key.POINTEVENTID));
			if (pointEvent != null) {
				long pointAmount = (long) inputJson.get(Key.POINTAMOUNT);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				pointEvent.setPointAmount(pointAmount);
				pointEvent.setDescription(description);
				PointEventDAO.modifyPointEvent(pointEvent);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, pointEvent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.POINTEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//totally delete away the point event
	public static JSONObject deletePointEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			PointEvent pointEvent = PointEventDAO.getPointEventById((long) inputJson.get(Key.POINTEVENTID));
			if (pointEvent != null) {
				PointEventDAO.deletePointEvent(pointEvent);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, pointEvent.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.POINTEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// features
	// Get point events by student
	public static JSONObject getPointEventsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray pointEventArr = new JSONArray();
				for (PointEvent pe : PointEventDAO.getPointEventsByStudent(student)) {
					pointEventArr.add(pe.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, pointEventArr);
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
	
	// Get point events by teacher
	public static JSONObject getPointEventsByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				JSONArray pointEventArr = new JSONArray();
				for (PointEvent pe : PointEventDAO.getPointEventsByTeacher(teacher)) {
					pointEventArr.add(pe.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, pointEventArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get point events by teacher and student
	public static JSONObject getPointEventsByTeacherAndStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if(student != null){
					JSONArray pointEventArr = new JSONArray();
					for (PointEvent pe : PointEventDAO.getPointEventsByTeacherAndStudent(teacher, student)) {
						pointEventArr.add(pe.toJson());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, pointEventArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
