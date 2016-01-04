package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Course;
import model.Schedule;
import model.Teacher;

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
	//get by teacher
	public static ArrayList<Schedule> getSchedulesByTeacher(Teacher teacher) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by course
	public static ArrayList<Schedule> getSchedulesByCourse(Course course) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by teacher and course
	public static ArrayList<Schedule> getSchedulesByTeacherCourse(Teacher teacher, Course course) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//get by teacher
	
	//get by planStartDate
	//TODO make sure the date pass in is correctly parse 
	public static ArrayList<Schedule> getSchedulesByStudentAndPlanStartDate(Date planStartDate) {
		ArrayList<Schedule> results = new ArrayList<Schedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		
		//TODO implement this method

//		detachedCriteria.add(Restrictions.between("createdAt", startDate, endDate));   
		
//		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
//		detachedCriteria.add(Restrictions.eq(Key.TEACHERCOURSE, teacherCourse));
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
		detachedCriteria.add(Restrictions.eq(Key.PLANSTARTDATE, planStartDate));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			results.add((Schedule) o);
		}
		return results;
	}
	
	//TODO create the method to retrieve base on plan start date, teacherCourse
//	public static Schedule getScheduleByTeacherCourseAndPlanStartDate(TeacherCourse teacherCourse, Date planStartDate) {
//		Schedule schedule = null;
//		Schedule tempSchedule = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
//		detachedCriteria.add(Restrictions.eq(Key.TEACHERCOURSE, teacherCourse));
//		detachedCriteria.add(Restrictions.eq(Key.PLANSTARTDATE, planStartDate));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for (Object o : list) {
//			tempSchedule = (Schedule)o;
//			if(tempSchedule != null){
//				schedule = tempSchedule;
//				break;
//			}
//		}
//		return schedule;
//	}

}
