package controller;

import java.util.Date;

import model.Attendance;
import model.Branch;
import model.Announcement;
import model.Schedule;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.BranchDAO;
import dataManager.AnnouncementDAO;
import dataManager.ScheduleDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class AnnouncementCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createAnnouncement(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				String content = (String) inputJson.get(Key.CONTENT);
				String image = (String) inputJson.get(Key.IMAGE);
				Date startDate = Config.SDF.parse((String) inputJson.get(Key.STARTDATE));
				Date endDate = Config.SDF.parse((String) inputJson.get(Key.ENDDATE));
				long announcementType = (long) inputJson.get(Key.ANNOUNCEMENTTYPE);
				
				Announcement announcement = new Announcement(name, content, image, startDate, endDate, branch);
				AnnouncementDAO.addAnnouncement(announcement);
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, announcement.toJsonSimple());
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

	// Get announcement by id
	public static JSONObject getAnnouncementById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Announcement c = AnnouncementDAO.getAnnouncementById((long) inputJson.get(Key.ANNOUNCEMENTID));
			if (c != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, c.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ANNOUNCEMENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all announcement
	public static JSONObject getAllAnnouncements() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray announcementJArr = new JSONArray();
			for (Announcement a : AnnouncementDAO.getAllAnnouncements()) {
				announcementJArr.add(a.toJsonSimple());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, announcementJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateAnnouncement(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Announcement announcement = AnnouncementDAO.getAnnouncementById((long) inputJson.get(Key.ANNOUNCEMENTID));
			if (announcement != null) {
				String name = (String) inputJson.get(Key.NAME);
				String content = (String) inputJson.get(Key.CONTENT);
				String image = (String) inputJson.get(Key.IMAGE);
				Date startDate = Config.SDF.parse((String) inputJson.get(Key.STARTDATE));
				Date endDate = Config.SDF.parse((String) inputJson.get(Key.ENDDATE));
				long announcementType = (long) inputJson.get(Key.ANNOUNCEMENTTYPE);
				
				announcement.setName(name);
				announcement.setContent(content);
				announcement.setImage(image);
				announcement.setStartDate(startDate);
				announcement.setEndDate(endDate);
				announcement.setAnnouncementType(announcementType);
				
				AnnouncementDAO.modifyAnnouncement(announcement);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, announcement.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ANNOUNCEMENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteAnnouncement(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Announcement announcement = AnnouncementDAO.getAnnouncementById((long) inputJson.get(Key.ANNOUNCEMENTID));
			if (announcement != null) {
				announcement.setObjStatus(Value.DELETED);
				AnnouncementDAO.modifyAnnouncement(announcement);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, announcement.toJsonSimple());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ANNOUNCEMENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Get announcements by branch
	public static JSONObject getAnnouncementsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray announcementArr = new JSONArray();
				for (Announcement announcement : AnnouncementDAO.getAnnouncementsByBranch(branch)) {
					announcementArr.add(announcement.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, announcementArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ANNOUNCEMENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

}
