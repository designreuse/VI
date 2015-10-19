package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class AdminDAO {
	// a. Admin class method: C R U D
	public static ArrayList<Admin> getAllAdmins() {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			admins.add((Admin) o);
		}
		return admins;
	}

	public static Admin getAdminById(long id) {
		return (Admin) HibernateUtil.get(Admin.class, id);
	}

	public static void addAdmin(Admin admin) {
		HibernateUtil.save(admin);
	}

	public static void modifyAdmin(Admin modifiedAdmin) {
		HibernateUtil.update(modifiedAdmin);
	}

	public static void deleteAdmin(Admin admin) {
		HibernateUtil.delete(admin);
	}

	// features
	public static Admin getAdminByName(String name) {
		Admin admin = null;
		Admin tempAdmin = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Admin.class);
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tempAdmin = (Admin) o;
			if (tempAdmin.getName().equals(name)) {
				admin = tempAdmin;
				break;
			}
		}
		return admin;
	}

	public static Admin getAdminByEmail(String email) {
		Admin admin = null;
		Admin tempAdmin = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Admin.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for (Object o : list) {
			tempAdmin = (Admin) o;
			if (tempAdmin.getEmail().equals(email)) {
				admin = tempAdmin;
				break;
			}
		}
		return admin;
	}
}
