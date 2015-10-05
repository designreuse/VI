package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Parent;
import model.Student;
import model.Parent;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class ParentDAO {
	// a. Parent class method: C R U D
	public static ArrayList<Parent> getAllParents() {
		ArrayList<Parent> parents = new ArrayList<Parent>();
		DetachedCriteria dc = DetachedCriteria.forClass(Parent.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			parents.add((Parent) o);
		}
		return parents;
	}

	public static Parent getParentById(long id) {
		return (Parent) HibernateUtil.get(Parent.class, id);
	}

	public static void addParent(Parent parent) {
		HibernateUtil.save(parent);
	}

	public static void modifyParent(Parent modifiedParent) {
		HibernateUtil.update(modifiedParent);
	}

	public static void deleteParent(Parent parent) {
		HibernateUtil.delete(parent);
	}
	
	//features
	public static Parent getParentByNric(String parentNric) {
		Parent parent = null;
		Parent tempParent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Parent.class);
		detachedCriteria.add(Restrictions.eq(Key.PARENTNRIC, parentNric));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempParent = (Parent)o;
			if(tempParent.getParentNric().equals(parentNric)){
				parent = tempParent;
				break;
			}
		}
		return parent;
	}
	
	public static Parent getParentByEmail(String email){
		Parent parent = null;
		Parent tempParent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Parent.class);
		detachedCriteria.add(Restrictions.eq(Key.EMAIL, email));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempParent = (Parent)o;
			if(tempParent.getEmail().equals(email)){
				parent = tempParent;
				break;
			}
		}
		return parent;
	}
	
	public static Parent getParentByBranch(Branch branch){
		Parent parent = null;
		Parent tempParent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Parent.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempParent = (Parent)o;
			if(tempParent.getBranch().equals(branch)){
				parent = tempParent;
				break;
			}
		}
		return parent;
	}
	
	public static ArrayList<Parent> getParentsByBranch(Branch branch){
		ArrayList<Parent> parents = new ArrayList<Parent>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Parent.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			parents.add((Parent) o);
		}
		return parents;
	}
	public static Parent getParentByStudent(Student student){
		Parent parent = null;
		Parent tempParent = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Parent.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempParent = (Parent)o;
			//would this work? need to iterate through?
			if(tempParent.getStudents().equals(student)){
				parent = tempParent;
				break;
			}
		}
		return parent;
	}
}
