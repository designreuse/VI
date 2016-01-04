package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Diagnostic;
import model.Student;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class DiagnosticDAO {
	// a. Diagnostic class method: C R U D
	public static ArrayList<Diagnostic> getAllDiagnostics() {
		ArrayList<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		DetachedCriteria dc = DetachedCriteria.forClass(Diagnostic.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			diagnostics.add((Diagnostic) o);
		}
		return diagnostics;
	}

	public static Diagnostic getDiagnosticById(long id) {
		return (Diagnostic) HibernateUtil.get(Diagnostic.class, id);
	}

	public static void addDiagnostic(Diagnostic diagnostic) {
		HibernateUtil.save(diagnostic);
	}

	public static void modifyDiagnostic(Diagnostic modifiedDiagnostic) {
		HibernateUtil.update(modifiedDiagnostic);
	}

	public static void deleteDiagnostic(Diagnostic diagnostic) {
		HibernateUtil.delete(diagnostic);
	}
	
	//features
	public static ArrayList<Diagnostic> getDiagnosticsByStudent(Student student){
		ArrayList<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diagnostic.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			diagnostics.add((Diagnostic) o);
		}
		return diagnostics;
	}
}
