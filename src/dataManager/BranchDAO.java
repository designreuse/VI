package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Branch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class BranchDAO {
	// a. Branch class method: C R U D
	public static ArrayList<Branch> getAllBranches() {
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
	
	public static Branch getBranchByPostalCode(String postalCode) {
		Branch branch = null;
		Branch tempBranch = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Branch.class);
		detachedCriteria.add(Restrictions.eq(Key.POSTALCODE, postalCode));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object b : list) {
			tempBranch = (Branch) b;
			if (tempBranch.getPostalCode().equals(postalCode)) {
				branch = tempBranch;
				break;
			}
		}
		return branch;
	}
}
