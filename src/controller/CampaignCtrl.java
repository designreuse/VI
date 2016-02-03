package controller;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.CampaignDAO;
import model.Branch;
import model.Campaign;
import system.Config;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class CampaignCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createCampaign(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				Date startDate = Config.SDF.parse((String) inputJson.get(Key.STARTDATE));
				Date endDate = Config.SDF.parse((String) inputJson.get(Key.ENDDATE));
				String campaignType = (String) inputJson.get(Key.CAMPAIGNTYPE);
				String address = (String) inputJson.get(Key.ADDRESS);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				double latitude = (double) inputJson.get(Key.LATITUDE);
				double longitude = (double) inputJson.get(Key.LONGITUDE);

				Campaign campaign = new Campaign(name, startDate, endDate, campaignType,
						address, postalCode, latitude, longitude, branch);
				CampaignDAO.addCampaign(campaign);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, campaign.toJsonSimple());
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

	// Get branch managers by id
	public static JSONObject getCampaignId(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Campaign c = CampaignDAO.getCampaignById((long) inputJson.get(Key.CAMPAIGNID));
			if (c != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, c.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CAMPAIGNNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all branch managers
	public static JSONObject getAllCampaigns() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray branchJArr = new JSONArray();
			for (Campaign a : CampaignDAO.getAllCampaigns()) {
				branchJArr.add(a.toJsonSimple());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, branchJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateCampaign(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Campaign campaign = CampaignDAO.getCampaignById((long) inputJson.get(Key.CAMPAIGNID));
			if (campaign != null) {
				String name = (String) inputJson.get(Key.NAME);
				Date startDate = Config.SDF.parse((String) inputJson.get(Key.STARTDATE));
				Date endDate = Config.SDF.parse((String) inputJson.get(Key.ENDDATE));
				String campaignType = (String) inputJson.get(Key.CAMPAIGNTYPE);
				String address = (String) inputJson.get(Key.ADDRESS);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				double latitude = Double.valueOf((String) inputJson.get(Key.LATITUDE));
				double longitude = Double.valueOf((String) inputJson.get(Key.LONGITUDE));

				campaign.setName(name);
				campaign.setStartDate(startDate);
				campaign.setEndDate(endDate);
				campaign.setCampaignType(campaignType);
				campaign.setAddress(address);
				campaign.setPostalCode(postalCode);
				campaign.setLatitude(latitude);
				campaign.setLongitude(longitude);

				CampaignDAO.modifyCampaign(campaign);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, campaign.toJsonSimple());
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

	public static JSONObject deleteBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Campaign campaign = CampaignDAO.getCampaignById((long) inputJson.get(Key.CAMPAIGNID));
			if (campaign != null) {
				campaign.setObjStatus(Value.DELETED);
				CampaignDAO.modifyCampaign(campaign);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, campaign.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CAMPAIGNNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Get campaigns by branch
	public static JSONObject getCampaignsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray campaignArr = new JSONArray();
				for (Campaign c : CampaignDAO.getCampaignsByBranch(branch)) {
					campaignArr.add(c.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, campaignArr);
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
}
