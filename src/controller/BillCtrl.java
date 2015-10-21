package controller;

import java.util.Date;

import model.Bill;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BillDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class BillCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createBill(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				double billAmount = Double.valueOf((String) inputJson.get(Key.BILLAMOUNT));
				Date dueDate = Config.SDF.parse((String) inputJson.get(Key.DUEDATE));

				Bill bill = new Bill(billAmount, dueDate, student);
				BillDAO.addBill(bill);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, bill.toJson());
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

	// Get bill by id
	public static JSONObject getBillById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Bill bill = BillDAO.getBillById((long) inputJson.get(Key.BILLID));
			if (bill != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, bill.toJson());
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

	// Get all bill
	public static JSONObject getAllBills() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray billJArr = new JSONArray();
			for (Bill a : BillDAO.getAllBills()) {
				billJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, billJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateBill(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Bill bill = BillDAO.getBillById((long) inputJson.get(Key.BILLID));
			if (bill != null) {
				double billAmount = Double.valueOf((String) inputJson.get(Key.BILLAMOUNT));
				Date dueDate = Config.SDF.parse((String) inputJson.get(Key.DUEDATE));
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				bill.setBillAmount(billAmount);
				bill.setDueDate(dueDate);
				bill.setStudent(student);
				BillDAO.modifyBill(bill);
				
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, bill.toJson());
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

	public static JSONObject deleteBill(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Bill bill = BillDAO.getBillById((long) inputJson.get(Key.BILLID));
			if (bill != null) {
				bill.setObjStatus(Value.DELETED);
				BillDAO.modifyBill(bill);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, bill.toJson());
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
	// Get bills by student
	public static JSONObject getBillsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray billArr = new JSONArray();
				for (Bill b : BillDAO.getBillsByStudent(student)) {
					billArr.add(b.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, billArr);
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
