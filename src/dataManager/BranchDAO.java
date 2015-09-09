package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

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
}
