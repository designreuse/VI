package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.BranchCourse;
import model.Branch;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class BranchCourseDAO {
	// a. BranchCourse class method: C R U D
	public static ArrayList<BranchCourse> getAllBranchCourses() {
		ArrayList<BranchCourse> branchCourses = new ArrayList<BranchCourse>();
		DetachedCriteria dc = DetachedCriteria.forClass(BranchCourse.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			branchCourses.add((BranchCourse) o);
		}
		return branchCourses;
	}

	public static BranchCourse getBranchCourseById(long id) {
		return (BranchCourse) HibernateUtil.get(BranchCourse.class, id);
	}

	public static void addBranchCourse(BranchCourse branchCourse) {
		HibernateUtil.save(branchCourse);
	}

	public static void modifyBranchCourse(BranchCourse modifiedBranchCourse) {
		HibernateUtil.update(modifiedBranchCourse);
	}

	public static void deleteBranchCourse(BranchCourse branchCourse) {
		HibernateUtil.delete(branchCourse);
	}
	
	//TODO later implement get branchCourse by course / by branch and course if necessary
	//features
	public static ArrayList<BranchCourse> getBranchCoursesByBranch(Branch branch){
		ArrayList<BranchCourse> branchCourses = new ArrayList<BranchCourse>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchCourse.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			branchCourses.add((BranchCourse) o);
		}
		return branchCourses;
	}
}
