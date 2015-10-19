package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.SalaryDAO;
import system.Config;
import system.Key;
import system.Value;

public class Teacher {
	private long teacherId;
	private String name;
	private String email;
	private String passwordSalt;
	private String passwordHash;
	private String contact;
	private String address;
	private String qualification;
	private String teacherNric;

	private long objStatus;
	private Date createDate;
	private String remark;

	private Branch branch;
	private Set<Course> courses;
	private Set<Salary> salaries;
	private Set<TeacherCourse> teacherCourses;
	private Set<PointEvent> pointEvents;

	public Teacher() {
	}

	public Teacher(String name, String email, String passwordSalt,
			String passwordHash, String contact, String address,
			String qualification, String teacherNric, Branch branch) {
		super();
		this.name = name;
		this.email = email;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.contact = contact;
		this.address = address;
		this.qualification = qualification;
		this.teacherNric = teacherNric;
		this.branch = branch;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the teacherId
	 */
	public long getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}

	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the teacherNric
	 */
	public String getTeacherNric() {
		return teacherNric;
	}

	/**
	 * @param teacherNric the teacherNric to set
	 */
	public void setTeacherNric(String teacherNric) {
		this.teacherNric = teacherNric;
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

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the salaries
	 */
	public Set<Salary> getSalaries() {
		return salaries;
	}

	/**
	 * @param salaries the salaries to set
	 */
	public void setSalaries(Set<Salary> salaries) {
		this.salaries = salaries;
	}

	/**
	 * @return the teacherCourses
	 */
	public Set<TeacherCourse> getTeacherCourses() {
		return teacherCourses;
	}

	/**
	 * @param teacherCourses the teacherCourses to set
	 */
	public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}

	/**
	 * @return the pointEvents
	 */
	public Set<PointEvent> getPointEvents() {
		return pointEvents;
	}

	/**
	 * @param pointEvents the pointEvents to set
	 */
	public void setPointEvents(Set<PointEvent> pointEvents) {
		this.pointEvents = pointEvents;
	}

	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.TEACHERID, this.teacherId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.QUALIFICATION, this.qualification);
		returnJson.put(Key.TEACHERNRIC, this.teacherNric);
		returnJson.put(Key.BRANCH, this.branch.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}

	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERID, this.teacherId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.QUALIFICATION, this.qualification);
		returnJson.put(Key.TEACHERNRIC, this.teacherNric);
		returnJson.put(Key.BRANCH, this.branch.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		JSONArray salaryArr = new JSONArray();
		for (Salary s : SalaryDAO.getSalariesByTeacher(this)) {
			salaryArr.add(s.toJson());
		}
		returnJson.put(Key.SALARYS, salaryArr);

		JSONArray courseArr = new JSONArray();
		for (Course c : CourseDAO.getCoursesByTeacher(this)) {
			courseArr.add(c.toJson());
		}
		returnJson.put(Key.COURSES, courseArr);
		
		//TODO teacherCourses pointEvents
		
		return returnJson;
	}

}
