package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;
import model.Teacher;
import model.TeacherStudentCourse;

import org.hibernate.Criteria;
import org.hibernate.Session;
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
//	public static ArrayList<Course> getCoursesByTeacher(ArrayList<TeacherStudentCourse> tsc){
//		ArrayList<Course> courses = new ArrayList<Course>();
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, tsc));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for(Object o : list){
//			courses.add((Course) o);
//		}
//		return courses;
//	}
	
	public static ArrayList<Course> getCoursesByTeacher(Teacher teacher){
		ArrayList<Course> courses = new ArrayList<Course>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select c from Course c join c.teacherStudentCourses tsc where tsc.teacher = :teacher")
				.setParameter("teacher", teacher).list();
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			courses.add((Course) o);
		}
		return courses;
	}
	
	public static ArrayList<Course> getCoursesByStudent(Student student){
		ArrayList<Course> courses = new ArrayList<Course>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select c from Course c join c.teacherStudentCourses tsc where tsc.student = :student")
				.setParameter("student", student).list();
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			courses.add((Course) o);
		}
		return courses;
	}
	
	public static ArrayList<Course> getCoursesScheduleByTeacher(Teacher teacher){
		ArrayList<Course> courses = new ArrayList<Course>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select c from Course c join c.schedules s where s.teacher = :teacher")
				.setParameter("teacher", teacher).list();
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			courses.add((Course) o);
		}
		return courses;
	}
	
	public static Course getCourseByName(String name) {
		Course course = null;
		Course tempCourse = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Course.class);
		detachedCriteria.add(Restrictions.eq(Key.COURSENAME, name));
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
