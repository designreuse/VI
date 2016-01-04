package controller;

import java.util.Date;

import model.Diagnostic;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.DiagnosticDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class DiagnosticCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createDiagnostic(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				String subjectName = (String) inputJson.get(Key.SUBJECTNAME);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				String feedback = (String) inputJson.get(Key.FEEDBACK);

				Diagnostic diagnostic = new Diagnostic(subjectName, resultValue, feedback, student);
				DiagnosticDAO.addDiagnostic(diagnostic);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnostic.toJson());
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

	// Get diagnostic by id
	public static JSONObject getDiagnosticById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Diagnostic diagnostic = DiagnosticDAO.getDiagnosticById((long) inputJson.get(Key.DIAGNOSTICID));
			if (diagnostic != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnostic.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.DIAGNOSTICNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all diagnostic
	public static JSONObject getAllDiagnostics() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray diagnosticJArr = new JSONArray();
			for (Diagnostic a : DiagnosticDAO.getAllDiagnostics()) {
				diagnosticJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, diagnosticJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateDiagnostic(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Diagnostic diagnostic = DiagnosticDAO.getDiagnosticById((long) inputJson.get(Key.DIAGNOSTICID));
			if (diagnostic != null) {
				String subjectName = (String) inputJson.get(Key.SUBJECTNAME);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				String feedback = (String) inputJson.get(Key.FEEDBACK);
				
				diagnostic.setSubjectName(subjectName);
				diagnostic.setResultValue(resultValue);
				diagnostic.setFeedback(feedback);
				DiagnosticDAO.modifyDiagnostic(diagnostic);
				
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnostic.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.DIAGNOSTICNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteDiagnostic(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Diagnostic diagnostic = DiagnosticDAO.getDiagnosticById((long) inputJson.get(Key.DIAGNOSTICID));
			if (diagnostic != null) {
				diagnostic.setObjStatus(Value.DELETED);
				DiagnosticDAO.modifyDiagnostic(diagnostic);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnostic.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.DIAGNOSTICNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	//Create multiple diagnostics for student
	public static JSONObject createDiagnosticsForStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray diagnosticArr = new JSONArray();
				diagnosticArr = (JSONArray) inputJson.get(Key.DIAGNOSTICS);
				for (Object o : diagnosticArr) {
					//TODO finish the implementation after XY confirm the input array
				}
					
				
				String subjectName = (String) inputJson.get(Key.SUBJECTNAME);
				String resultValue = (String) inputJson.get(Key.RESULTVALUE);
				String feedback = (String) inputJson.get(Key.FEEDBACK);

				Diagnostic diagnostic = new Diagnostic(subjectName, resultValue, feedback, student);
				DiagnosticDAO.addDiagnostic(diagnostic);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnostic.toJson());
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
	
	// Get diagnostics by student
	public static JSONObject getDiagnosticsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray diagnosticArr = new JSONArray();
				for (Diagnostic b : DiagnosticDAO.getDiagnosticsByStudent(student)) {
					diagnosticArr.add(b.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, diagnosticArr);
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
