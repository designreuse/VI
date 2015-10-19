package dataManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import hibernate.HibernateUtil;
import model.PointEvent;
import model.Student;
import model.Teacher;
import system.Key;
import system.Value;

public class PointEventDAO {
	// a. PointEvent class method: C R U D
	public static ArrayList<PointEvent> getAllPointEvents() {
		ArrayList<PointEvent> admins = new ArrayList<PointEvent>();
		DetachedCriteria dc = DetachedCriteria.forClass(PointEvent.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			admins.add((PointEvent) o);
		}
		return admins;
	}

	public static PointEvent getPointEventById(long id) {
		return (PointEvent) HibernateUtil.get(PointEvent.class, id);
	}

	public static void addPointEvent(PointEvent admin) {
		HibernateUtil.save(admin);
	}

	public static void modifyPointEvent(PointEvent modifiedPointEvent) {
		HibernateUtil.update(modifiedPointEvent);
	}

	public static void deletePointEvent(PointEvent admin) {
		HibernateUtil.delete(admin);
	}

	public static ArrayList<PointEvent> getPointEventsByStudent(Student student) {
		ArrayList<PointEvent> results = new ArrayList<PointEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PointEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil
				.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((PointEvent) o);
		}
		return results;
	}

	public static ArrayList<PointEvent> getPointEventsByTeacher(Teacher teacher) {
		ArrayList<PointEvent> results = new ArrayList<PointEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PointEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil
				.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((PointEvent) o);
		}
		return results;
	}
	
	public static ArrayList<PointEvent> getPointEventsByTeacherAndStudent(Teacher teacher, Student student) {
		ArrayList<PointEvent> results = new ArrayList<PointEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PointEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil
				.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((PointEvent) o);
		}
		return results;
	}
}
