package controller;

import java.util.Date;

import model.Salary;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.SalaryDAO;
import dataManager.TeacherDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class SalaryCtrl {

	/**
	 * CRUD
	 * */
	public static JSONObject createSalary(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				double salaryAmount = Double.valueOf((String) inputJson.get(Key.SALARYAMOUNT));
				Date dueDate = Config.SDF.parse((String) inputJson.get(Key.DUEDATE));
				Salary salary = new Salary(salaryAmount, dueDate, teacher);
				SalaryDAO.addSalary(salary);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salary.toJson());
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

	// Get salary by id
	public static JSONObject getSalaryById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Salary salary = SalaryDAO.getSalaryById((long) inputJson.get(Key.SALARYID));
			if (salary != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salary.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SALARYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all salary
	public static JSONObject getAllSalarys() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray salaryJArr = new JSONArray();
			for (Salary a : SalaryDAO.getAllSalarys()) {
				salaryJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, salaryJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Decided whether to put in teacher obejct or not
	public static JSONObject updateSalary(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Salary salary = SalaryDAO.getSalaryById((long) inputJson.get(Key.SALARYID));
			if (salary != null) {
				double salaryAmount = Double.valueOf((String) inputJson.get(Key.SALARYAMOUNT));
				Date dueDate = Config.SDF.parse((String) inputJson.get(Key.DUEDATE));
				// Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
				// salary.setTeacher(teacher);
				salary.setSalaryAmount(salaryAmount);
				salary.setDueDate(dueDate);
				SalaryDAO.modifySalary(salary);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salary.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SALARYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	public static JSONObject deleteSalary(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Salary salary = SalaryDAO.getSalaryById((long) inputJson.get(Key.SALARYID));
			if (salary != null) {
				salary.setObjStatus(Value.DELETED);
				SalaryDAO.modifySalary(salary);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salary.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SALARYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Get salaries by teacher
	public static JSONObject getSalariesByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				JSONArray salaryArr = new JSONArray();
				for (Salary salary : SalaryDAO.getSalariesByTeacher(teacher)) {
					salaryArr.add(salary.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, salaryArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SALARYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
