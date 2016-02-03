package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Campaign;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class CampaignDAO {
	// a. Campaign class method: C R U D
	public static ArrayList<Campaign> getAllCampaigns() {
		ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
		DetachedCriteria dc = DetachedCriteria.forClass(Campaign.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			campaigns.add((Campaign) o);
		}
		return campaigns;
	}

	public static Campaign getCampaignById(long id) {
		return (Campaign) HibernateUtil.get(Campaign.class, id);
	}

	public static void addCampaign(Campaign campaign) {
		HibernateUtil.save(campaign);
	}

	public static void modifyCampaign(Campaign modifiedCampaign) {
		HibernateUtil.update(modifiedCampaign);
	}

	public static void deleteCampaign(Campaign campaign) {
		HibernateUtil.delete(campaign);
	}

	// features
	public static Campaign getCampaignByName(String name) {
		Campaign campaign = null;
		Campaign tempCampaign = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Campaign.class);
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tempCampaign = (Campaign) o;
			if (tempCampaign.getName().equals(name)) {
				campaign = tempCampaign;
				break;
			}
		}
		return campaign;
	}
	
	public static ArrayList<Campaign> getCampaignsByBranch(Branch branch){
		ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Campaign.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			campaigns.add((Campaign) o);
		}
		return campaigns;
	}

}
