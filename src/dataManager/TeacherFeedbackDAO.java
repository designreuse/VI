package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;
import model.Teacher;
import model.TeacherFeedback;
import model.TeacherStudentCourse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
	
	public static TeacherFeedback getLatestTeacherFeedbackByTSC(TeacherStudentCourse tsc){
		TeacherFeedback tf = null;
		if(tsc != null){
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeacherFeedback.class);
			detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, tsc));
			detachedCriteria.addOrder(Order.desc(Key.CREATEDATE));
			detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
			List<Object> list = HibernateUtil.detachedCriteriaReturnLimitedList(detachedCriteria, 1);
			for(Object o : list){
				tf = (TeacherFeedback)o;
			}
		}
		return tf;
	}
	
	public static ArrayList<TeacherFeedback> getFeedbacksByStudent(Student student){
		ArrayList<TeacherFeedback> feedbacks = new ArrayList<TeacherFeedback>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select tf from TeacherFeedback tf join tf.teacherStudentCourses tsc join tsc.Student s where s.student = :student")
				.setParameter("student", student).list();
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			feedbacks.add((TeacherFeedback) o);
		}
		return feedbacks;
	}
	
	
}
