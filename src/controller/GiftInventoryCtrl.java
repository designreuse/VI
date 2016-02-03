package controller;

import java.util.Date;

import model.Attendance;
import model.Branch;
import model.GiftInventory;
import model.Schedule;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.BranchDAO;
import dataManager.GiftInventoryDAO;
import dataManager.ScheduleDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class GiftInventoryCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createGiftInventory(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String giftName = (String) inputJson.get(Key.GIFTNAME);
				String giftImage = (String) inputJson.get(Key.GIFTIMAGE);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				long stockQuantity = (long) inputJson.get(Key.STOCKQUANTITY);
				String giftTier = (String) inputJson.get(Key.GIFTTIER);
				double giftPoints = Double.valueOf((String) inputJson.get(Key.GIFTPOINTS));
				
				GiftInventory giftInventory = new GiftInventory(giftName, giftImage, description, stockQuantity, giftTier, giftPoints, branch);
				GiftInventoryDAO.addGiftInventory(giftInventory);
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftInventory.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get giftInventory by id
	public static JSONObject getGiftInventoryById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftInventory c = GiftInventoryDAO.getGiftInventoryById((long) inputJson.get(Key.GIFTINVENTORYID));
			if (c != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, c.toJsonSimple());
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

	// Get all giftInventory
	public static JSONObject getAllGiftInventorys() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray giftInventoryJArr = new JSONArray();
			for (GiftInventory a : GiftInventoryDAO.getAllGiftInventorys()) {
				giftInventoryJArr.add(a.toJsonSimple());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, giftInventoryJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateGiftInventory(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftInventory giftInventory = GiftInventoryDAO.getGiftInventoryById((long) inputJson.get(Key.GIFTINVENTORYID));
			if (giftInventory != null) {
				String giftName = (String) inputJson.get(Key.GIFTNAME);
				String giftImage = (String) inputJson.get(Key.GIFTIMAGE);
				String description = (String) inputJson.get(Key.DESCRIPTION);
				long stockQuantity = (long) inputJson.get(Key.STOCKQUANTITY);
				String giftTier = (String) inputJson.get(Key.GIFTTIER);
				double giftPoints = Double.valueOf((String) inputJson.get(Key.GIFTPOINTS));
				
				giftInventory.setGiftName(giftName);
				giftInventory.setGiftImage(giftImage);
				giftInventory.setDescription(description);
				giftInventory.setStockQuantity(stockQuantity);
				giftInventory.setGiftTier(giftTier);
				giftInventory.setGiftPoints(giftPoints);
				
				GiftInventoryDAO.modifyGiftInventory(giftInventory);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftInventory.toJsonSimple());
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

	public static JSONObject deleteGiftInventory(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			GiftInventory giftInventory = GiftInventoryDAO.getGiftInventoryById((long) inputJson.get(Key.GIFTINVENTORYID));
			if (giftInventory != null) {
				giftInventory.setObjStatus(Value.DELETED);
				GiftInventoryDAO.modifyGiftInventory(giftInventory);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftInventory.toJsonSimple());
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

	// features
	// Get giftInventorys by branch
	public static JSONObject getGiftInventorysByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray giftInventoryArr = new JSONArray();
				for (GiftInventory giftInventory : GiftInventoryDAO.getGiftInventorysByBranch(branch)) {
					giftInventoryArr.add(giftInventory.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, giftInventoryArr);
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

}
