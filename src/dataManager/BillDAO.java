package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Bill;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class BillDAO {
	// a. Bill class method: C R U D
	public static ArrayList<Bill> getAllBills() {
		ArrayList<Bill> bills = new ArrayList<Bill>();
		DetachedCriteria dc = DetachedCriteria.forClass(Bill.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			bills.add((Bill) o);
		}
		return bills;
	}

	public static Bill getBillById(long id) {
		return (Bill) HibernateUtil.get(Bill.class, id);
	}

	public static void addBill(Bill bill) {
		HibernateUtil.save(bill);
	}

	public static void modifyBill(Bill modifiedBill) {
		HibernateUtil.update(modifiedBill);
	}

	public static void deleteBill(Bill bill) {
		HibernateUtil.delete(bill);
	}
	
	//features
}
