package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.GiftInventory;
import model.GiftTransaction;
import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class GiftTransactionDAO {
	// a. GiftTransaction class method: C R U D
	public static ArrayList<GiftTransaction> getAllGiftTransactions() {
		ArrayList<GiftTransaction> giftTransactions = new ArrayList<GiftTransaction>();
		DetachedCriteria dc = DetachedCriteria.forClass(GiftTransaction.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			giftTransactions.add((GiftTransaction) o);
		}
		return giftTransactions;
	}

	public static GiftTransaction getGiftTransactionById(long id) {
		return (GiftTransaction) HibernateUtil.get(GiftTransaction.class, id);
	}

	public static void addGiftTransaction(GiftTransaction giftTransaction) {
		HibernateUtil.save(giftTransaction);
	}

	public static void modifyGiftTransaction(GiftTransaction modifiedGiftTransaction) {
		HibernateUtil.update(modifiedGiftTransaction);
	}

	public static void deleteGiftTransaction(GiftTransaction giftTransaction) {
		HibernateUtil.delete(giftTransaction);
	}
	
	//features
	public static ArrayList<GiftTransaction> getGiftTransactionsByGiftInventory(GiftInventory giftInventory){
		ArrayList<GiftTransaction> giftTransactions = new ArrayList<GiftTransaction>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftTransaction.class);
		detachedCriteria.add(Restrictions.eq(Key.GIFTINVENTORY, giftInventory));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			giftTransactions.add((GiftTransaction) o);
		}
		return giftTransactions;
	}
	
	public static ArrayList<GiftTransaction> getGiftTransactionsByStudent(Student student){
		ArrayList<GiftTransaction> giftTransactions = new ArrayList<GiftTransaction>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftTransaction.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			giftTransactions.add((GiftTransaction) o);
		}
		return giftTransactions;
	}
	
	public static ArrayList<GiftTransaction> getGiftTransactionsByGiftInventoryAndStudent(GiftInventory giftInventory, Student student){
		ArrayList<GiftTransaction> giftTransactions = new ArrayList<GiftTransaction>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftTransaction.class);
		detachedCriteria.add(Restrictions.eq(Key.GIFTINVENTORY, giftInventory));
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			giftTransactions.add((GiftTransaction) o);
		}
		return giftTransactions;
	}
	
//	public static GiftTransaction getGiftTransactionByEmail(String email) {
//		GiftTransaction giftTransaction = null;
//		GiftTransaction tempGiftTransaction = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftTransaction.class);
//		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for (Object o : list) {
//			tempGiftTransaction = (GiftTransaction) o;
//			if (tempGiftTransaction.getEmail().equals(email)) {
//				giftTransaction = tempGiftTransaction;
//				break;
//			}
//		}
//		return giftTransaction;
//	}
}
