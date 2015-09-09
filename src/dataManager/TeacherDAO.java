package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Teacher;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

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
}
