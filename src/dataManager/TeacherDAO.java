package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Teacher;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class TeacherDAO {
	// a. Teacher class method: C R U D
	public static ArrayList<Teacher> getAllTeachers() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			teachers.add((Teacher) o);
		}
		return teachers;
	}

	public static Teacher getTeacherById(long id) {
		return (Teacher) HibernateUtil.get(Teacher.class, id);
	}

	public static void addTeacher(Teacher teacher) {
		HibernateUtil.save(teacher);
	}

	public static void modifyTeacher(Teacher modifiedTeacher) {
		HibernateUtil.update(modifiedTeacher);
	}

	public static void deleteTeacher(Teacher teacher) {
		HibernateUtil.delete(teacher);
	}
	
	//features
	//get by branch
	public static ArrayList<Teacher> getTeachersByBranch(Branch branch){
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			teachers.add((Teacher) o);
		}
		return teachers;
	}
	
	//get by email
	public static Teacher getTeacherByEmail(String email){
		Teacher teacher = null;
		Teacher tempTeacher = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempTeacher = (Teacher)o;
			if(tempTeacher.getEmail().equals(email)){
				teacher = tempTeacher;
				break;
			}
		}
		return teacher;
	}
	
	//get by nric
	public static Teacher getTeacherByNric(String teacherNric){
		Teacher teacher = null;
		Teacher tempTeacher = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHERNRIC, teacherNric));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempTeacher = (Teacher)o;
			if(tempTeacher.getTeacherNric().equals(teacherNric)){
				teacher = tempTeacher;
				break;
			}
		}
		return teacher;
	}
	
}
