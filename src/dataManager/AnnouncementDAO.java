package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Announcement;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class AnnouncementDAO {
	// a. Announcement class method: C R U D
	public static ArrayList<Announcement> getAllAnnouncements() {
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		DetachedCriteria dc = DetachedCriteria.forClass(Announcement.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			announcements.add((Announcement) o);
		}
		return announcements;
	}

	public static Announcement getAnnouncementById(long id) {
		return (Announcement) HibernateUtil.get(Announcement.class, id);
	}

	public static void addAnnouncement(Announcement announcement) {
		HibernateUtil.save(announcement);
	}

	public static void modifyAnnouncement(Announcement modifiedAnnouncement) {
		HibernateUtil.update(modifiedAnnouncement);
	}

	public static void deleteAnnouncement(Announcement announcement) {
		HibernateUtil.delete(announcement);
	}
	
	//features
	public static ArrayList<Announcement> getAnnouncementsByBranch(Branch branch){
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Announcement.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			announcements.add((Announcement) o);
		}
		return announcements;
	}
	
//	public static Announcement getAnnouncementByEmail(String email) {
//		Announcement announcement = null;
//		Announcement tempAnnouncement = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Announcement.class);
//		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for (Object o : list) {
//			tempAnnouncement = (Announcement) o;
//			if (tempAnnouncement.getEmail().equals(email)) {
//				announcement = tempAnnouncement;
//				break;
//			}
//		}
//		return announcement;
//	}
}
