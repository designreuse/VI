package controller;

import java.util.Date;

import model.Attendance;
import model.GiftInventory;
import model.GiftTransaction;
import model.Schedule;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.GiftInventoryDAO;
import dataManager.GiftTransactionDAO;
import dataManager.ScheduleDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class GiftTransactionCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createGiftTransaction(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftInventory giftInventory = GiftInventoryDAO.getGiftInventoryById((long) inputJson.get(Key.GIFTINVENTORYID));
			if (giftInventory != null) {
				Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				if (student != null) {
					long giftQuantity = (long) inputJson.get(Key.GIFTQUANTITY);
					double studentPoints = Double.valueOf((String) inputJson.get(Key.STUDENTPOINTS));
					
					GiftTransaction giftTransaction = new GiftTransaction(giftQuantity, studentPoints, giftInventory, student);
					GiftTransactionDAO.addGiftTransaction(giftTransaction);
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, giftTransaction.toJsonSimple());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.GIFTINVENTORYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get giftTransaction by id
	public static JSONObject getGiftTransactionById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftTransaction c = GiftTransactionDAO.getGiftTransactionById((long) inputJson.get(Key.GIFTTRANSACTIONID));
			if (c != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, c.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.GIFTTRANSACTIONNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all giftTransaction
	public static JSONObject getAllGiftTransactions() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray giftTransactionJArr = new JSONArray();
			for (GiftTransaction a : GiftTransactionDAO.getAllGiftTransactions()) {
				giftTransactionJArr.add(a.toJsonSimple());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, giftTransactionJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateGiftTransaction(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftTransaction giftTransaction = GiftTransactionDAO.getGiftTransactionById((long) inputJson.get(Key.GIFTTRANSACTIONID));
			if (giftTransaction != null) {
				long giftQuantity = (long) inputJson.get(Key.GIFTQUANTITY);
				double studentPoints = Double.valueOf((String) inputJson.get(Key.STUDENTPOINTS));
				
				giftTransaction.setGiftQuantity(giftQuantity);
				giftTransaction.setStudentPoints(studentPoints);
				
				GiftTransactionDAO.modifyGiftTransaction(giftTransaction);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftTransaction.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.GIFTTRANSACTIONNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteGiftTransaction(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftTransaction giftTransaction = GiftTransactionDAO.getGiftTransactionById((long) inputJson.get(Key.GIFTTRANSACTIONID));
			if (giftTransaction != null) {
				giftTransaction.setObjStatus(Value.DELETED);
				GiftTransactionDAO.modifyGiftTransaction(giftTransaction);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftTransaction.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.GIFTTRANSACTIONNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Get giftTransactions by giftInventory
	public static JSONObject getGiftTransactionsByGiftInventory(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftInventory giftInventory = GiftInventoryDAO.getGiftInventoryById((long) inputJson.get(Key.GIFTINVENTORYID));
			if (giftInventory != null) {
				JSONArray giftTransactionArr = new JSONArray();
				for (GiftTransaction giftTransaction : GiftTransactionDAO.getGiftTransactionsByGiftInventory(giftInventory)) {
					giftTransactionArr.add(giftTransaction.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftTransactionArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.GIFTTRANSACTIONNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get giftTransactions by student
	public static JSONObject getGiftTransactionsByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				JSONArray giftTransactionArr = new JSONArray();
				for (GiftTransaction giftTransaction : GiftTransactionDAO.getGiftTransactionsByStudent(student)) {
					giftTransactionArr.add(giftTransaction.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftTransactionArr);
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
