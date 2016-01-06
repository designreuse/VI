package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;
import model.Teacher;
import model.TeacherStudentCourse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class TeacherStudentCourseDAO {
	// a. TeacherStudentCourse class method: C R U D
	public static ArrayList<TeacherStudentCourse> getAllTeacherStudentCourses() {
		ArrayList<TeacherStudentCourse> teacherStudentCourses = new ArrayList<TeacherStudentCourse>();
		DetachedCriteria dc = DetachedCriteria.forClass(TeacherStudentCourse.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			teacherStudentCourses.add((TeacherStudentCourse) o);
		}
		return teacherStudentCourses;
	}

	public static TeacherStudentCourse getTeacherStudentCourseById(long id) {
		return (TeacherStudentCourse) HibernateUtil.get(TeacherStudentCourse.class, id);
	}

	public static void addTeacherStudentCourse(TeacherStudentCourse teacherStudentCourse) {
		HibernateUtil.save(teacherStudentCourse);
	}

	public static void modifyTeacherStudentCourse(TeacherStudentCourse modifiedTeacherStudentCourse) {
		HibernateUtil.update(modifiedTeacherStudentCourse);
	}

	public static void deleteTeacherStudentCourse(TeacherStudentCourse teacherStudentCourse) {
		HibernateUtil.delete(teacherStudentCourse);
	}

	// features
	//get by teacher
	public static ArrayList<TeacherStudentCourse> getTeacherStudentCoursesByTeacher(Teacher teacher) {
		ArrayList<TeacherStudentCourse> teacherStudentCourses = new ArrayList<TeacherStudentCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherStudentCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			teacherStudentCourses.add((TeacherStudentCourse) o);
		}
		return teacherStudentCourses;
	}
	//get by course
	public static ArrayList<TeacherStudentCourse> getTeacherStudentCoursesByCourse(Course course) {
		ArrayList<TeacherStudentCourse> teacherStudentCourses = new ArrayList<TeacherStudentCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherStudentCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			teacherStudentCourses.add((TeacherStudentCourse) o);
		}
		return teacherStudentCourses;
	}
	//get by student
	public static ArrayList<TeacherStudentCourse> getTeacherStudentCoursesByStudent(Student student) {
		ArrayList<TeacherStudentCourse> teacherStudentCourses = new ArrayList<TeacherStudentCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherStudentCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			teacherStudentCourses.add((TeacherStudentCourse) o);
		}
		return teacherStudentCourses;
	}
	//get by teacher and course
	public static ArrayList<TeacherStudentCourse> getTeacherStudentCoursesByTeacherAndCourse(Teacher teacher, Course course) {
		ArrayList<TeacherStudentCourse> teacherStudentCourses = new ArrayList<TeacherStudentCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherStudentCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			teacherStudentCourses.add((TeacherStudentCourse) o);
		}
		return teacherStudentCourses;
	}
	
	//get by teacher, student and course
	public static TeacherStudentCourse getTeacherStudentCoursesByTeacherStudentAndCourse(Teacher teacher, Student student, Course course) {
		TeacherStudentCourse tsc = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherStudentCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tsc = (TeacherStudentCourse) o;
			
		}
		return tsc;
	}
}
