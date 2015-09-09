package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class StudentDAO {
	// a. Student class method: C R U D
	public static ArrayList<Student> getAllStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		DetachedCriteria dc = DetachedCriteria.forClass(Student.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			students.add((Student) o);
		}
		return students;
	}

	public static Student getStudentById(long id) {
		return (Student) HibernateUtil.get(Student.class, id);
	}

	public static void addStudent(Student student) {
		HibernateUtil.save(student);
	}

	public static void modifyStudent(Student modifiedStudent) {
		HibernateUtil.update(modifiedStudent);
	}

	public static void deleteStudent(Student student) {
		HibernateUtil.delete(student);
	}
	
	//features
}
