package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import dataManager.BillDAO;
import dataManager.BranchManagerDAO;
import dataManager.ClassroomDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import system.Config;
import system.Key;
import system.Value;

public class Branch {
	private long branchId;
	private String name;
	private String location;
	private String postalcode;
	private String contactnumber;
	
	private Admin admin;
	private Set<Student> students;
	private Set<Teacher> teachers;
	private Set<BranchManager> branchManagers;
	private Set<Classroom> classrooms;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Branch(){}

	public Branch(String name, String location, String postalcode, Admin admin, String contactnumber) {
		super();
		this.name = name;
		this.location = location;
		this.postalcode = postalcode;
		this.admin = admin;
		this.setCreateDate(new Date());
		this.setObjStatus(Value.ACTIVED);
		this.contactnumber = contactnumber;
	}

	/**
	 * @return the branchId
	 */
	public long getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/**
	 * @return the students
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	/**
	 * @return the teachers
	 */
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers the teachers to set
	 */
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	/**
	 * @return the branchManagers
	 */
	public Set<BranchManager> getBranchManagers() {
		return branchManagers;
	}

	/**
	 * @param branchManagers the branchManagers to set
	 */
	public void setBranchManagers(Set<BranchManager> branchManagers) {
		this.branchManagers = branchManagers;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus the objStatus to set
	 */
	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.BRANCHID, this.branchId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.LOCATION, this.location);
		returnJson.put(Key.POSTALCODE, this.postalcode);
		returnJson.put(Key.CONTACTNUMBER, this.contactnumber);
		
		returnJson.put(Key.ADMIN, this.admin.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.BRANCHID, this.branchId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.LOCATION, this.location);
		returnJson.put(Key.POSTALCODE, this.postalcode);
		returnJson.put(Key.CONTACTNUMBER, this.contactnumber);
		
		returnJson.put(Key.ADMIN, this.admin.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		//branch manager, teacher, attendance, classroom, student
		
		JSONArray branchManagerArr = new JSONArray();
		for(BranchManager bm : BranchManagerDAO.getBranchManagersByBranch(this)){
			branchManagerArr.add(bm.toJson());
		}
		returnJson.put(Key.BRANCHMANAGERS, branchManagerArr);
		
		JSONArray teacherArr = new JSONArray();
		for(Teacher t : TeacherDAO.getTeachersByBranch(this)){
			teacherArr.add(t.toJson());
		}
		returnJson.put(Key.TEACHERS, teacherArr);
		
//		JSONArray attendanceArr = new JSONArray();
//		for(Attendance a : AttendanceDAO.getBillsByStudent(this)){
//			attendanceArr.add(a.toJson());
//		}
//		returnJson.put(Key.ATTENDANCE, attendanceArr);
		
		JSONArray classroomArr = new JSONArray();
		for(Classroom c : ClassroomDAO.getClassroomsByBranch(this)){
			classroomArr.add(c.toJson());
		}
		returnJson.put(Key.CLASSROOMS, classroomArr);
		
		JSONArray studentArr = new JSONArray();
		for(Student s : StudentDAO.getStudentsByBranch(this)){
			studentArr.add(s.toJson());
		}
		returnJson.put(Key.STUDENTS, studentArr);
		
		return returnJson;
	}
}
