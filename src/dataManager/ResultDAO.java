package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Result;
import model.TeacherStudentCourse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class ResultDAO {
	// a. Result class method: C R U D
	public static ArrayList<Result> getAllResults() {
		ArrayList<Result> results = new ArrayList<Result>();
		DetachedCriteria dc = DetachedCriteria.forClass(Result.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			results.add((Result) o);
		}
		return results;
	}

	public static Result getResultById(long id) {
		return (Result) HibernateUtil.get(Result.class, id);
	}

	public static void addResult(Result result) {
		HibernateUtil.save(result);
	}

	public static void modifyResult(Result modifiedResult) {
		HibernateUtil.update(modifiedResult);
	}

	public static void deleteResult(Result result) {
		HibernateUtil.delete(result);
	}
	
	//features
	public static ArrayList<Result> getResultsByTeacherStudentCourse(TeacherStudentCourse teacherStudentCourse) {
		ArrayList<Result> results = new ArrayList<Result>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Result.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, teacherStudentCourse));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			results.add((Result) o);
		}
		return results;
	}
	
	public static Result getLatestResultByTSC(TeacherStudentCourse tsc){
		Result result = null;
		if(tsc != null){
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Result.class);
			detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, tsc));
			detachedCriteria.addOrder(Order.desc(Key.CREATEDATE));
			detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
			List<Object> list = HibernateUtil.detachedCriteriaReturnLimitedList(detachedCriteria, 1);
			for(Object o : list){
				result = (Result)o;
			}
		}
		return result;
	}
	
	public static ArrayList<Result> getLatesThreeResultsByTSC(TeacherStudentCourse tsc){
		ArrayList<Result> results = new ArrayList<Result>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Result.class);
		detachedCriteria.add(Restrictions.eq(Key.TEACHERSTUDENTCOURSE, tsc));
		detachedCriteria.addOrder(Order.desc(Key.CREATEDATE));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnLimitedList(detachedCriteria, 3);
		for(Object o : list){
			results.add((Result) o);
		}
		return results;
	}


}
