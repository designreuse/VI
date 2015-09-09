package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.BranchManager;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class BranchManagerDAO {
	// a. BranchManager class method: C R U D
	public static ArrayList<BranchManager> getAllBranchManagers() {
		ArrayList<BranchManager> branchManagers = new ArrayList<BranchManager>();
		DetachedCriteria dc = DetachedCriteria.forClass(BranchManager.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			branchManagers.add((BranchManager) o);
		}
		return branchManagers;
	}

	public static BranchManager getBranchManagerById(long id) {
		return (BranchManager) HibernateUtil.get(BranchManager.class, id);
	}

	public static void addBranchManager(BranchManager branchManager) {
		HibernateUtil.save(branchManager);
	}

	public static void modifyBranchManager(BranchManager modifiedBranchManager) {
		HibernateUtil.update(modifiedBranchManager);
	}

	public static void deleteBranchManager(BranchManager branchManager) {
		HibernateUtil.delete(branchManager);
	}
	
	//features
}
