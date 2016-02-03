package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.GiftInventory;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class GiftInventoryDAO {
	// a. GiftInventory class method: C R U D
	public static ArrayList<GiftInventory> getAllGiftInventorys() {
		ArrayList<GiftInventory> giftInventorys = new ArrayList<GiftInventory>();
		DetachedCriteria dc = DetachedCriteria.forClass(GiftInventory.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			giftInventorys.add((GiftInventory) o);
		}
		return giftInventorys;
	}

	public static GiftInventory getGiftInventoryById(long id) {
		return (GiftInventory) HibernateUtil.get(GiftInventory.class, id);
	}

	public static void addGiftInventory(GiftInventory giftInventory) {
		HibernateUtil.save(giftInventory);
	}

	public static void modifyGiftInventory(GiftInventory modifiedGiftInventory) {
		HibernateUtil.update(modifiedGiftInventory);
	}

	public static void deleteGiftInventory(GiftInventory giftInventory) {
		HibernateUtil.delete(giftInventory);
	}
	
	//features
	public static ArrayList<GiftInventory> getGiftInventorysByBranch(Branch branch){
		ArrayList<GiftInventory> giftInventorys = new ArrayList<GiftInventory>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftInventory.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			giftInventorys.add((GiftInventory) o);
		}
		return giftInventorys;
	}
	
//	public static GiftInventory getGiftInventoryByEmail(String email) {
//		GiftInventory giftInventory = null;
//		GiftInventory tempGiftInventory = null;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GiftInventory.class);
//		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
//		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
//		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
//		for (Object o : list) {
//			tempGiftInventory = (GiftInventory) o;
//			if (tempGiftInventory.getEmail().equals(email)) {
//				giftInventory = tempGiftInventory;
//				break;
//			}
//		}
//		return giftInventory;
//	}
}
