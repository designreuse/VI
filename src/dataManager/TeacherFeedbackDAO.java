package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.TeacherFeedback;
import model.TeacherStudentCourse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class TeacherFeedbackDAO {
	// a. TeacherFeedback class method: C R U D
	public static ArrayList<TeacherFeedback> getAllTeacherFeedbacks() {
		ArrayList<TeacherFeedback> teacherFeedbacks = new ArrayList<TeacherFeedback>();
		DetachedCriteria dc = DetachedCriteria.forClass(TeacherFeedback.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			teacherFeedbacks.add((TeacherFeedback) o);
		}
		return teacherFeedbacks;
	}

	public static TeacherFeedback getTeacherFeedbackById(long id) {
		return (TeacherFeedback) HibernateUtil.get(TeacherFeedback.class, id);
	}

	public static void addTeacherFeedback(TeacherFeedback teacherFeedback) {
		HibernateUtil.save(teacherFeedback);
	}

	public static void modifyTeacherFeedback(TeacherFeedback modifiedTeacherFeedback) {
		HibernateUtil.update(modifiedTeacherFeedback);
	}

	public static void deleteTeacherFeedback(TeacherFeedback teacherFeedback) {
		HibernateUtil.delete(teacherFeedback);
	}
	
	//features
	public static ArrayList<TeacherFeedback> getTeacherFeedbacksByTeacherStudentCourse(TeacherStudentCourse teacherStudentCourse) {
		ArrayList<TeacherFeedback> teacherFeedbacks = new ArrayList<TeacherFeedback>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherFeedback.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, teacherStudentCourse));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			teacherFeedbacks.add((TeacherFeedback) o);
		}
		return teacherFeedbacks;
	}
	
}
