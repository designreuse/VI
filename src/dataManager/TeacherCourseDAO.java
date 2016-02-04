package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.TeacherCourse;
import model.Teacher;
import model.TeacherCourse;
import model.Teacher;
import model.TeacherCourse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class TeacherCourseDAO {
	// a. TeacherCourse class method: C R U D
	public static ArrayList<TeacherCourse> getAllTeacherCourses() {
		ArrayList<TeacherCourse> teacherCourses = new ArrayList<TeacherCourse>();
		DetachedCriteria dc = DetachedCriteria.forClass(TeacherCourse.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			teacherCourses.add((TeacherCourse) o);
		}
		return teacherCourses;
	}

	public static TeacherCourse getTeacherCourseById(long id) {
		return (TeacherCourse) HibernateUtil.get(TeacherCourse.class, id);
	}

	public static void addTeacherCourse(TeacherCourse teacherCourse) {
		HibernateUtil.save(teacherCourse);
	}

	public static void modifyTeacherCourse(TeacherCourse modifiedTeacherCourse) {
		HibernateUtil.update(modifiedTeacherCourse);
	}

	public static void deleteTeacherCourse(TeacherCourse teacherCourse) {
		HibernateUtil.delete(teacherCourse);
	}

	// features
	public static ArrayList<TeacherCourse> getTeacherCoursesByTeacher(Teacher teacher){
		ArrayList<TeacherCourse> teacherCourses = new ArrayList<TeacherCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			teacherCourses.add((TeacherCourse) o);
		}
		return teacherCourses;
	}
	
	public static ArrayList<TeacherCourse> getTeacherCoursesByCourse(Course course){
		ArrayList<TeacherCourse> teacherCourses = new ArrayList<TeacherCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			teacherCourses.add((TeacherCourse) o);
		}
		return teacherCourses;
	}
	
	public static ArrayList<TeacherCourse> getTeacherCoursesByTeacherAndCourse(Teacher teacher, Course course){
		ArrayList<TeacherCourse> teacherCourses = new ArrayList<TeacherCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			teacherCourses.add((TeacherCourse) o);
		}
		return teacherCourses;
	}
}
