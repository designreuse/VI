package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Attendance;
import model.Bill;
import model.Branch;
import model.Classroom;
import model.Parent;
import model.Result;
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
	public static ArrayList<Student> getStudentsByParent(Parent parent){
		ArrayList<Student> students = new ArrayList<Student>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.PARENT, parent));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			students.add((Student) o);
		}
		return students;
	}
	public static Student getStudentByBill(Bill bill){
		Student student = null;
		Student tempStudent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.BILL, bill));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempStudent = (Student)o;
			//would this work? need to iterate through?
			if(tempStudent.getBills().equals(bill)){
				student = tempStudent;
				break;
			}
		}
		return student;
	}
	public static Student getStudentByResult(Result result){
		Student student = null;
		Student tempStudent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.RESULT, result));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempStudent = (Student)o;
			//would this work? need to iterate through?
			if(tempStudent.getResults().equals(result)){
				student = tempStudent;
				break;
			}
		}
		return student;
	}
	
	public static Student getStudentByAttendance(Attendance attendance){
		Student student = null;
		Student tempStudent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.ATTENDANCE, attendance));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempStudent = (Student)o;
			//would this work? need to iterate through?
			if(tempStudent.getAttendances().equals(attendance)){
				student = tempStudent;
				break;
			}
		}
		return student;
	}
}
