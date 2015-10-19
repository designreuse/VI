package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.BranchManager;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

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
	public static ArrayList<BranchManager> getBranchManagersByBranch(Branch branch){
		ArrayList<BranchManager> branchemanagers = new ArrayList<BranchManager>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchManager.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			branchemanagers.add((BranchManager) o);
		}
		return branchemanagers;
	}
	public static BranchManager getBranchManagerByEmail(String email) {
		BranchManager branchManager = null;
		BranchManager tempBranchManager = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchManager.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tempBranchManager = (BranchManager) o;
			if (tempBranchManager.getEmail().equals(email)) {
				branchManager = tempBranchManager;
				break;
			}
		}
		return branchManager;
	}
	
	public static BranchManager getBranchManagerByNric(String branchManagerNric) {
		BranchManager branchManager = null;
		BranchManager tempBranchManager = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchManager.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCHMANAGERNRIC, branchManagerNric));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempBranchManager = (BranchManager)o;
			if(tempBranchManager.getBranchManagerNric().equals(branchManagerNric)){
				branchManager = tempBranchManager;
				break;
			}
		}
		return branchManager;
	}
}
