package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class CourseDAO {
	// a. Course class method: C R U D
	public static ArrayList<Course> getAllCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			courses.add((Course) o);
		}
		return courses;
	}

	public static Course getCourseById(long id) {
		return (Course) HibernateUtil.get(Course.class, id);
	}

	public static void addCourse(Course course) {
		HibernateUtil.save(course);
	}

	public static void modifyCourse(Course modifiedCourse) {
		HibernateUtil.update(modifiedCourse);
	}

	public static void deleteCourse(Course course) {
		HibernateUtil.delete(course);
	}
	
	//features
//	public static ArrayList<Course> getCoursesByTeacher(Teacher teacher){
//		ArrayList<Course> courses = new ArrayList<Course>();
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class);
//		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for(Object o : list){
//			courses.add((Course) o);
//		}
//		return courses;
//	}
	
	public static Course getCourseByName(String name) {
		Course course = null;
		Course tempCourse = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class);
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tempCourse = (Course) o;
			if (tempCourse.getName().equals(name)) {
				course = tempCourse;
				break;
			}
		}
		return course;
	}
	
//	public static Course getCourseByAttendance(Attendance attendance){
//		Course course = null;
//		Course tempCourse = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class);
//		detachedCriteria.add(Restrictions.eq(Key.ATTENDANCE, attendance));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for(Object o : list){
//			tempCourse = (Course)o;
//			//would this work? need to iterate through?
//			if(tempCourse.getAttendances().equals(attendance)){
//				course = tempCourse;
//				break;
//			}
//		}
//		return course;
//	}
//	
//	public static Course getCourseByResult(Result result){
//		Course course = null;
//		Course tempCourse = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class);
//		detachedCriteria.add(Restrictions.eq(Key.RESULT, result));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for(Object o : list){
//			tempCourse = (Course)o;
//			//would this work? need to iterate through?
//			if(tempCourse.getResults().equals(result)){
//				course = tempCourse;
//				break;
//			}
//		}
//		return course;
//	}
	
}
