package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Branch;
import model.Classroom;
import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

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
	public static ArrayList<Student> getStudentsByBranch(Branch branch){
		ArrayList<Student> students = new ArrayList<Student>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			students.add((Student) o);
		}
		return students;
	}
	public static Student getStudentByEmail(String email){
		Student student = null;
		Student tempStudent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempStudent = (Student)o;
			if(tempStudent.getEmail().equals(email)){
				student = tempStudent;
				break;
			}
		}
		return student;
	}
}
