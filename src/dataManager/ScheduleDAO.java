package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Schedule;
import model.Classroom;
import model.TeacherCourse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class ScheduleDAO {
	// a. Schedule class method: C R U D
	public static ArrayList<Schedule> getAllSchedules() {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		DetachedCriteria dc = DetachedCriteria.forClass(Schedule.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			schedules.add((Schedule) o);
		}
		return schedules;
	}

	public static Schedule getScheduleById(long id) {
		return (Schedule) HibernateUtil.get(Schedule.class, id);
	}

	public static void addSchedule(Schedule schedule) {
		HibernateUtil.save(schedule);
	}

	public static void modifySchedule(Schedule modifiedSchedule) {
		HibernateUtil.update(modifiedSchedule);
	}

	public static void deleteSchedule(Schedule schedule) {
		HibernateUtil.delete(schedule);
	}

	// features
	//get by classroom
	public static ArrayList<Schedule> getSchedulesByClassroom(Classroom classroom) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.CLASSROOM, classroom));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by teacherCourse
	public static ArrayList<Schedule> getSchedulesByTeacherCourse(TeacherCourse teacherCourse) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHERCOURSE, teacherCourse));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by classroom and reacherCourse
	public static ArrayList<Schedule> getSchedulesByClassroomAndTeacherCourse(Classroom classroom, TeacherCourse teacherCourse) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.CLASSROOM, classroom));
		detachedCriteria.add(Restrictions.eq(Key.TEACHERCOURSE, teacherCourse));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by planStartDate
	//TODO make sure the date pass in is correctly parse 
	public static ArrayList<Schedule> getSchedulesByPlanStartDate(Date planStartDate) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.PLANNEDSTARTDATE, planStartDate));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//TODO create the method to retrieve base on plan start date, teacherCourse
	
//	public static Schedule getScheduleByEmail(String email) {
//		Schedule schedule = null;
//		Schedule tempSchedule = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
//		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for (Object o : list) {
//			tempSchedule = (Schedule) o;
//			if (tempSchedule.getEmail().equals(email)) {
//				schedule = tempSchedule;
//				break;
//			}
//		}
//		return schedule;
//	}
//	
//	public static Salary getLatestSalaryByTeacher(Teacher teacher){
//		Salary salary = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Salary.class);
//		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
//		detachedCriteria.addOrder(Order.desc(Key.CREATEDATE));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnLimitedList(detachedCriteria, 1);
//		if(!list.isEmpty()){
//			for(Object o : list){
//				return salary = (Salary) o;
//			}
//		} 
//		return salary;
//	}
}
