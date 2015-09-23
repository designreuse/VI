package controller;

import model.Admin;
import model.Branch;
import model.BranchManager;
import model.Classroom;
import model.Parent;
import model.Student;
import model.Teacher;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.BranchDAO;
import dataManager.BranchManagerDAO;
import dataManager.ClassroomDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import system.Key;
import system.Message;
import system.Value;

public class BranchCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			if (admin != null) {
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String contactnumber = (String) inputJson.get(Key.CONTACTNUMBER);

				Branch branch = new Branch(name, location, postalCode, admin, contactnumber);
				BranchDAO.addBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get branch by id
	public static JSONObject getBranchById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long branchId = (long) inputJson.get(Key.BRANCHID);
			Branch branch = BranchDAO.getBranchById(branchId);
			if (branch != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all branch
	public static JSONObject getAllBranches() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray branchJArr = new JSONArray();
			for (Branch a : BranchDAO.getAllBranches()) {
				branchJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, branchJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));

			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String contactnumber = (String) inputJson.get(Key.CONTACTNUMBER);

				branch.setName(name);
				branch.setLocation(location);
				branch.setPostalcode(postalCode);
				branch.setContactnumber(contactnumber);

				BranchDAO.modifyBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	public static JSONObject deleteBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));

			if (branch != null) {
				branch.setObjStatus(Value.DELETED);
				BranchDAO.modifyBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	// Get branches by admin
	public static JSONObject getBranchesByAdmin(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Admin admin = (Admin) inputJson.get(Key.ADMIN);
			ArrayList<Branch> branches = BranchDAO.getBranchesByAdmin(admin);
			if (branches != null) {
				// iterate through the list of branches & add into jsonobject
				for (Branch branch : branches) {
					// add 1 time or many times
					returnJson.put(Key.STATUS, Value.SUCCESS);

					returnJson.put(Key.MESSAGE, branch.toJson());
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get branch by student
	public static JSONObject getBranchByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
			if (student != null) {
				Branch branch = student.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get branch by branch manager
	public static JSONObject getBranchByBranchManager(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			BranchManager branchManager = BranchManagerDAO.getBranchManagerById((long)inputJson.get(Key.BRANCHMANAGERID));
			if (branchManager != null) {
				Branch branch = branchManager.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHMANAGERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get branch by classroom
	public static JSONObject getBranchByClassroom(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long)inputJson.get(Key.CLASSROOMID));
			if (classroom != null) {
				Branch branch = classroom.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	// Get branch by teacher
	public static JSONObject getBranchByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long)inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Branch branch = teacher.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

}
