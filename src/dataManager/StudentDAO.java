package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Course;
import model.Parent;
import model.Student;
import model.Teacher;

import org.hibernate.Session;
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
		//System.out.println("SAVED");
	}

	public static void modifyStudent(Student modifiedStudent) {
		HibernateUtil.update(modifiedStudent);
	}

	public static void deleteStudent(Student student) {
		HibernateUtil.delete(student);
	}
	
	//features
	
	public static Student getStudentByNric(String studentNric){
		Student student = null;
		Student tempStudent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENTNRIC, studentNric));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempStudent = (Student)o;
			if(tempStudent.getStudentNric().equals(studentNric)){
				student = tempStudent;
				break;
			}
		}
		return student;
	}
	
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
	
	public static ArrayList<Student> getStudentsByTeacherAndCourse(Teacher teacher, Course course){
		ArrayList<Student> students = new ArrayList<Student>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select s from Student s join s.teacherStudentCourses tsc where tsc.teacher = :teacher and tsc.course = :course")
				.setParameter("teacher", teacher)
				.setParameter("course", course)
				.list();
		session.getTransaction().commit();
		session.close();
		for(Object o : list){
			students.add((Student) o);
		}
		return students;
	}
	
}
