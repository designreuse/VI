package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Branch;
import model.Classroom;
import model.Course;
import model.Schedule;
import model.ScheduleEvent;
import model.Student;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class ScheduleEventDAO {
	// a. ScheduleEvent class method: C R U D
	public static ArrayList<ScheduleEvent> getAllScheduleEvents() {
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		DetachedCriteria dc = DetachedCriteria.forClass(ScheduleEvent.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			scheduleEvents.add((ScheduleEvent) o);
		}
		return scheduleEvents;
	}

	public static ScheduleEvent getScheduleEventById(long id) {
		return (ScheduleEvent) HibernateUtil.get(ScheduleEvent.class, id);
	}

	public static void addScheduleEvent(ScheduleEvent scheduleEvent) {
		HibernateUtil.save(scheduleEvent);
	}

	public static void modifyScheduleEvent(ScheduleEvent modifiedScheduleEvent) {
		HibernateUtil.update(modifiedScheduleEvent);
	}

	public static void deleteScheduleEvent(ScheduleEvent scheduleEvent) {
		HibernateUtil.delete(scheduleEvent);
	}
	
	//features
	public static ArrayList<ScheduleEvent> getScheduleEventsByClassroom(Classroom classroom){
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.CLASSROOM, classroom));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			scheduleEvents.add((ScheduleEvent) o);
		}
		return scheduleEvents;
	}
	
	public static ArrayList<ScheduleEvent> getScheduleEventsBySchedule(Schedule schedule){
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULE, schedule));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			scheduleEvents.add((ScheduleEvent) o);
		}
		return scheduleEvents;
	}
	
	public static ArrayList<ScheduleEvent> getScheduleEventsByClassroomAndSchedule(Classroom classroom, Schedule schedule){
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleEvent.class);
		detachedCriteria.add(Restrictions.eq(Key.CLASSROOM, classroom));
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULE, schedule));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			scheduleEvents.add((ScheduleEvent) o);
		}
		return scheduleEvents;
	}
	
	//TODO get s e by branch
	public static ArrayList<ScheduleEvent> getShceduleEventsByBranch(Branch branch){
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		
		return scheduleEvents;
	}

	public static ArrayList<ScheduleEvent> getScheduleEventsByStudent(Student student) {
		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select se from ScheduleEvent se join se.attendances a where a.student = :student")
				.setParameter("student", student).list();
//		Iterator iter = session.createQuery("select se from ScheduleEvent se join se.attendances a where a.student = :student")
//				.setParameter("student", student).iterate();
		
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			scheduleEvents.add((ScheduleEvent) o);
		}
		return scheduleEvents;
	}
	
	//TODO use the methos in course to get scheduleEvent
//	public static ArrayList<ScheduleEvent> getScheduleEventsByStudent(Student student){
//		ArrayList<ScheduleEvent> scheduleEvents = new ArrayList<ScheduleEvent>();
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleEvent.class);
//		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for(Object o : list){
//			scheduleEvents.add((ScheduleEvent) o);
//		}
//		return scheduleEvents;
//	}
}
