package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Schedule;
import model.StudentSchedule;
import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class StudentScheduleDAO {
	// a. StudentSchedule class method: C R U D
	public static ArrayList<StudentSchedule> getAllStudentSchedules() {
		ArrayList<StudentSchedule> studentSchedules = new ArrayList<StudentSchedule>();
		DetachedCriteria dc = DetachedCriteria.forClass(StudentSchedule.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			studentSchedules.add((StudentSchedule) o);
		}
		return studentSchedules;
	}

	public static StudentSchedule getStudentScheduleById(long id) {
		return (StudentSchedule) HibernateUtil.get(StudentSchedule.class, id);
	}

	public static void addStudentSchedule(StudentSchedule studentSchedule) {
		HibernateUtil.save(studentSchedule);
	}

	public static void modifyStudentSchedule(StudentSchedule modifiedStudentSchedule) {
		HibernateUtil.update(modifiedStudentSchedule);
	}

	public static void deleteStudentSchedule(StudentSchedule studentSchedule) {
		HibernateUtil.delete(studentSchedule);
	}
	
	//features
	public static ArrayList<StudentSchedule> getStudentSchedulesByStudent(Student student){
		ArrayList<StudentSchedule> studentSchedules = new ArrayList<StudentSchedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StudentSchedule.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			studentSchedules.add((StudentSchedule) o);
		}
		return studentSchedules;
	}
	
	public static ArrayList<StudentSchedule> getStudentSchedulesBySchedule(Schedule schedule){
		ArrayList<StudentSchedule> studentSchedules = new ArrayList<StudentSchedule>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StudentSchedule.class);
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULE, schedule));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			studentSchedules.add((StudentSchedule) o);
		}
		return studentSchedules;
	}
	
}
