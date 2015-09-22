package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.BranchManager;
import model.Course;
import model.Salary;
import model.Student;
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
	public static Teacher getTeacherByEmail(String email){
		Teacher teacher = null;
		Teacher tempTeacher = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
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
	public static Teacher getTeacherByCourse(Course course){
		Teacher teacher = null;
		Teacher tempTeacher = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.COURSE, course));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempTeacher = (Teacher)o;
			//would this work? need to iterate through?
			if(tempTeacher.getCourses().equals(course)){
				teacher = tempTeacher;
				break;
			}
		}
		return teacher;
	}
	public static Teacher getTeacherBySalary(Salary salary){
		Teacher teacher = null;
		Teacher tempTeacher = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
		detachedCriteria.add(Restrictions.eq(Key.SALARY, salary));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempTeacher = (Teacher)o;
			//would this work? need to iterate through?
			if(tempTeacher.getSalaries().equals(salary)){
				teacher = tempTeacher;
				break;
			}
		}
		return teacher;
	}
}
