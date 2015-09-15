package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Salary;
import model.Teacher;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class SalaryDAO {
	// a. Salary class method: C R U D
	public static ArrayList<Salary> getAllSalarys() {
		ArrayList<Salary> salarys = new ArrayList<Salary>();
		DetachedCriteria dc = DetachedCriteria.forClass(Salary.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			salarys.add((Salary) o);
		}
		return salarys;
	}

	public static Salary getSalaryById(long id) {
		return (Salary) HibernateUtil.get(Salary.class, id);
	}

	public static void addSalary(Salary salary) {
		HibernateUtil.save(salary);
	}

	public static void modifySalary(Salary modifiedSalary) {
		HibernateUtil.update(modifiedSalary);
	}

	public static void deleteSalary(Salary salary) {
		HibernateUtil.delete(salary);
	}
	
	//features
	public static ArrayList<Salary> getSalaryByTeacher(Teacher teacher){
		ArrayList<Salary> salaries = new ArrayList<Salary>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Salary.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			salaries.add((Salary) o);
		}
		return salaries;
	}
}
