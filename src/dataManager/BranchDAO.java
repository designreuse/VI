package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Admin;
import model.Branch;
import model.BranchManager;
import model.Classroom;
import model.Result;
import model.Student;
import model.Teacher;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class BranchDAO {
	// a. Branch class method: C R U D
	public static ArrayList<Branch> getAllBranchs() {
		ArrayList<Branch> branchs = new ArrayList<Branch>();
		DetachedCriteria dc = DetachedCriteria.forClass(Branch.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			branchs.add((Branch) o);
		}
		return branchs;
	}

	public static Branch getBranchById(long id) {
		return (Branch) HibernateUtil.get(Branch.class, id);
	}

	public static void addBranch(Branch branch) {
		HibernateUtil.save(branch);
	}

	public static void modifyBranch(Branch modifiedBranch) {
		HibernateUtil.update(modifiedBranch);
	}

	public static void deleteBranch(Branch branch) {
		HibernateUtil.delete(branch);
	}
	
	//features
	public static ArrayList<Branch> getBranchesByAdmin(Admin admin){
		ArrayList<Branch> branches = new ArrayList<Branch>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.ADMIN, admin));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			branches.add((Branch) o);
		}
		return branches;
	}
	
	public static Branch getBranchByStudent(Student student){
		Branch branch = null;
		Branch tempBranch = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempBranch = (Branch)o;
			//would this work? need to iterate through?
			if(tempBranch.getStudents().equals(student)){
				branch = tempBranch;
				break;
			}
		}
		return branch;
	}
	public static Branch getBranchByBranchManager(BranchManager branchManager){
		Branch branch = null;
		Branch tempBranch = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCHMANAGER, branchManager));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempBranch = (Branch)o;
			//would this work? need to iterate through?
			if(tempBranch.getBranchManagers().equals(branchManager)){
				branch = tempBranch;
				break;
			}
		}
		return branch;
	}
	public static Branch getBranchByClassroom(Classroom classroom){
		Branch branch = null;
		Branch tempBranch = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.CLASSROOM, classroom));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempBranch = (Branch)o;
			//would this work? need to iterate through?
			if(tempBranch.getClassrooms().equals(classroom)){
				branch = tempBranch;
				break;
			}
		}
		return branch;
	}
	
	public static Branch getBranchByTeacher(Teacher teacher){
		Branch branch = null;
		Branch tempBranch = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHER, teacher));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempBranch = (Branch)o;
			//would this work? need to iterate through?
			if(tempBranch.getTeachers().equals(teacher)){
				branch = tempBranch;
				break;
			}
		}
		return branch;
	}
}
