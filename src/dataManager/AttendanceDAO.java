package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Attendance;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class AttendanceDAO {
	// a. Attendance class method: C R U D
	public static ArrayList<Attendance> getAllAttendances() {
		ArrayList<Attendance> attendances = new ArrayList<Attendance>();
		DetachedCriteria dc = DetachedCriteria.forClass(Attendance.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			attendances.add((Attendance) o);
		}
		return attendances;
	}

	public static Attendance getAttendanceById(long id) {
		return (Attendance) HibernateUtil.get(Attendance.class, id);
	}

	public static void addAttendance(Attendance attendance) {
		HibernateUtil.save(attendance);
	}

	public static void modifyAttendance(Attendance modifiedAttendance) {
		HibernateUtil.update(modifiedAttendance);
	}

	public static void deleteAttendance(Attendance attendance) {
		HibernateUtil.delete(attendance);
	}
	
	//features
}
