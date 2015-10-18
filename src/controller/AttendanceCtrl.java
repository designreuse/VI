package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Admin;
import model.Attendance;
import model.Branch;
import model.Classroom;
import model.Course;
import model.Parent;
import model.Student;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.AttendanceDAO;
import dataManager.BranchDAO;
import dataManager.ClassroomDAO;
import dataManager.CourseDAO;
import dataManager.ParentDAO;
import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class AttendanceCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long) inputJson.get(Key.CLASSROOMID));
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));

			if (classroom != null) {
				if (student != null) {
					if (course != null) {
						String location = (String) inputJson.get(Key.LOCATION);
						Date planStartDate = Config.SDF.parse((String) inputJson.get(Key.PLANNEDSTARTDATE));

						Attendance attendance = new Attendance(planStartDate, location, course, student);
						AttendanceDAO.addAttendance(attendance);

						returnJson.put(Key.STATUS, Value.SUCCESS);
						returnJson.put(Key.MESSAGE, attendance.toJson());
					} else {
						returnJson.put(Key.STATUS, Value.FAIL);
						returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
					}
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get attendance by id
	public static JSONObject getAttendanceById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long attendanceId = (long) inputJson.get(Key.BRANCHID);
			Attendance attendance = AttendanceDAO.getAttendanceById(attendanceId);
			if (attendance != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
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

	// Get all attendance
	public static JSONObject getAllAttendances() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray attendanceJArr = new JSONArray();
			for (Attendance a : AttendanceDAO.getAllAttendances()) {
				attendanceJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, attendanceJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.ATTENDANCEID));

			if (attendance != null) {
				//Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
				Date actualStartDate = (Date) inputJson.get(Key.ACTUALSTARTDATE);
				// String name = (String) inputJson.get(Key.NAME);
				// String location = (String) inputJson.get(Key.LOCATION);
				// String postalCode = (String) inputJson.get(Key.POSTALCODE);
				// //String contactNo = (String) inputJson.get(Key.CONTACTNO);
				
				//attendance.setStudent(student);
				attendance.setActualStartDate(actualStartDate);
				// attendance.setName(name);
				// attendance.setLocation(location);
				// attendance.setPostalcode(postalCode);
				// attendance.setContactNo(contactNo);
				
				AttendanceDAO.modifyAttendance(attendance);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
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

	public static JSONObject deleteAttendance(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Attendance attendance = AttendanceDAO.getAttendanceById((long) inputJson.get(Key.BRANCHID));

			if (attendance != null) {
				attendance.setObjStatus(Value.DELETED);
				AttendanceDAO.modifyAttendance(attendance);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, attendance.toJson());
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
	// Get attendances by classroom
	public static JSONObject getAttendancesByClassroom(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = (Classroom) inputJson.get(Key.CLASSROOM);
			ArrayList<Attendance> attendances = AttendanceDAO.getAttendancesByClassroom(classroom);
			if (attendances != null) {
				for (Attendance attendance : attendances) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendance.toJson());
				}
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

	// Get attendances by course
	public static JSONObject getAttendancesByCourse(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long courseId = (long) inputJson.get(Key.COURSEID);
			Course course = CourseDAO.getCourseById(courseId);
			ArrayList<Attendance> attendances = AttendanceDAO.getAttendancesByCourse(course);
			if (attendances != null) {
				for (Attendance attendance : attendances) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendance.toJson());
				}
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

	// Get attendances by student
	public static JSONObject getAttendancesByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = (Student) inputJson.get(Key.STUDENT);
			ArrayList<Attendance> attendances = AttendanceDAO.getAttendancesByStudent(student);
			if (attendances != null) {
				for (Attendance attendance : attendances) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, attendance.toJson());
				}
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
}
