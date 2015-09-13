package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Bill;
import model.Bill;
import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

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
	public static ArrayList<Bill> getBillsByStudent(Student student){
		ArrayList<Bill> bills = new ArrayList<Bill>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bill.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			bills.add((Bill) o);
		}
		return bills;
	}
}
