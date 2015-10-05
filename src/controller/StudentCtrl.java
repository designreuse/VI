package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Attendance;
import model.Bill;
import model.Branch;
import model.Parent;
import model.PointEvent;
import model.Result;
import model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.BillDAO;
import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.PointEventDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class StudentCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			long branchId = Long.parseLong((String)inputJson.get(Key.BRANCHID));
			Branch branch = BranchDAO.getBranchById(branchId);
			Parent parent = ParentDAO.getParentByNric((String) inputJson.get(Key.PARENTNRIC));
			if (branch != null) {
				if (parent != null) {
					String name = (String) inputJson.get(Key.NAME);
					String email = (String) inputJson.get(Key.EMAIL);
					String contact = (String) inputJson.get(Key.CONTACT);
					String address = (String) inputJson.get(Key.ADDRESS);
					String studentLevel = (String) inputJson.get(Key.STUDENTLEVEL);
					String parentNric = (String) inputJson.get(Key.PARENTNRIC);

					Student student = new Student(name, email, contact, address, studentLevel, parentNric, parent, branch);
					StudentDAO.addStudent(student);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, student.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
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

	// Get student by id
	public static JSONObject getStudentById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long studentId = (long) inputJson.get(Key.STUDENTID);
			Student student = StudentDAO.getStudentById(studentId);
			if (student != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
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

	// Get all student
	public static JSONObject getAllStudents() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray studentJArr = new JSONArray();
			for (Student a : StudentDAO.getAllStudents()) {
				studentJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, studentJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));

			if (student != null) {
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				String studentLevel = (String) inputJson.get(Key.STUDENTLEVEL);

				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
				Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));

				student.setName(name);
				student.setEmail(email);
				student.setContact(contact);
				student.setAddress(address);
				student.setStudentLevel(studentLevel);
				student.setBranch(branch);
				student.setParent(parent);

				StudentDAO.modifyStudent(student);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
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

	public static JSONObject deleteStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));

			if (student != null) {
				student.setObjStatus(Value.DELETED);
				StudentDAO.modifyStudent(student);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
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

	// features
	// Register a new student
	public static JSONObject registerStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		returnJson = getStudentByEmail(inputJson);
		if ((long) returnJson.get(Key.STATUS) == 0) {
			returnJson = createStudent(inputJson);
		} else {
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, Message.EMAILALREADYEXIST);
		}
		return returnJson;
	}

	// Get student by email
	public static JSONObject getStudentByEmail(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Student student = StudentDAO.getStudentByEmail(email);
			if (student != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
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

	// login student
	public static JSONObject loginStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Student student = StudentDAO.getStudentByEmail(email);
			if (student != null) {
				String password = (String) inputJson.get(Key.PASSWORD);
				String passwordSalt = student.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String checkHash = student.getPasswordHash();
				if (checkHash.equals(passwordHash)) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, student.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGSTUDENTPASSWORD);
				}
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

	// Get students by branch
	public static JSONObject getStudentsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			// ArrayList<Student> students =
			// StudentDAO.getStudentsByBranch(branch);
			if (branch != null) {
				// iterate through the list of students & add into jsonobject
				// for (Student student : students) {
				// // add 1 time or many times
				// returnJson.put(Key.STATUS, Value.SUCCESS);
				//
				// returnJson.put(Key.MESSAGE, student.toJson());
				// }
				JSONArray studentArr = new JSONArray();
				for (Student s : StudentDAO.getStudentsByBranch(branch)) {
					studentArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentArr);
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

	// Get students by parent
	public static JSONObject getStudentsByParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Parent parent = (Parent) inputJson.get(Key.PARENT);
			ArrayList<Student> students = StudentDAO.getStudentsByParent(parent);
			if (students != null) {
				// iterate through the list of students & add into jsonobject
				for (Student student : students) {
					// add 1 time or many times
					returnJson.put(Key.STATUS, Value.SUCCESS);

					returnJson.put(Key.MESSAGE, student.toJson());
				}
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

	// Get student by bill
	public static JSONObject getStudentByBill(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Bill bill = BillDAO.getBillById((long) inputJson.get(Key.BILLID));
			if (bill != null) {
				Student student = bill.getStudent();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BILLNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get student by result
	public static JSONObject getStudentByResult(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Result result = ResultDAO.getResultById((long) inputJson.get(Key.RESULTID));
			if (result != null) {
				Student student = result.getStudent();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.RESULTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get student by attendance
	public static JSONObject getStudentByAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));
			if (attendance != null) {
				Student student = attendance.getStudent();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ATTENDANCENOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get student by pointEvent
	public static JSONObject getStudentByPointEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			PointEvent pointEvent = PointEventDAO.getPointEventById((long) inputJson.get(Key.POINTEVENTID));
			if (pointEvent != null) {
				Student student = pointEvent.getStudent();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.POINTEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
