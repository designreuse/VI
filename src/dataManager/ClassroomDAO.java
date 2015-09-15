package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Classroom;
import model.Teacher;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class ClassroomDAO {
	// a. Classroom class method: C R U D
	public static ArrayList<Classroom> getAllClassrooms() {
		ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
		DetachedCriteria dc = DetachedCriteria.forClass(Classroom.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			classrooms.add((Classroom) o);
		}
		return classrooms;
	}

	public static Classroom getClassroomById(long id) {
		return (Classroom) HibernateUtil.get(Classroom.class, id);
	}

	public static void addClassroom(Classroom classroom) {
		HibernateUtil.save(classroom);
	}

	public static void modifyClassroom(Classroom modifiedClassroom) {
		HibernateUtil.update(modifiedClassroom);
	}

	public static void deleteClassroom(Classroom classroom) {
		HibernateUtil.delete(classroom);
	}
	
	//features
	public static ArrayList<Classroom> getClassroomsByBranch(Branch branch){
		ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Classroom.class);
		detachedCriteria.add(Restrictions.eq(Key.BRANCH, branch));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			classrooms.add((Classroom) o);
		}
		return classrooms;
	}
}
