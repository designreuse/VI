package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchCourseDAO;
import dataManager.BranchManagerDAO;
import dataManager.ClassroomDAO;
import dataManager.ParentDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import system.Config;
import system.Key;
import system.Value;

public class Branch {
	private long branchId;
	private String name;
	private String location;
	private String postalCode;
	private String contact;
	
	private Admin admin;
	private Set<Parent> parents;
	private Set<Student> students;
	private Set<Teacher> teachers;
	private Set<BranchManager> branchManagers;
	private Set<Classroom> classrooms;
	private Set<BranchCourse> branchCourses;
	private Set<Campaign> campaigns;
	private Set<GiftInventory> giftInventories;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Branch(){}

	/**
	 * @param name
	 * @param location
	 * @param postalCode
	 * @param contact
	 * @param admin
	 */
	public Branch(String name, String location, String postalCode, String contact, Admin admin) {
		super();
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
		this.contact = contact;
		this.admin = admin;
		this.setCreateDate(new Date());
		this.setObjStatus(Value.ACTIVED);
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
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * @return the parents
	 */
	public Set<Parent> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(Set<Parent> parents) {
		this.parents = parents;
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
	 * @return the classrooms
	 */
	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	/**
	 * @param classrooms the classrooms to set
	 */
	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
	/**
	 * @return the branchCourses
	 */
	public Set<BranchCourse> getBranchCourses() {
		return branchCourses;
	}

	/**
	 * @param branchCourses the branchCourses to set
	 */
	public void setBranchCourses(Set<BranchCourse> branchCourses) {
		this.branchCourses = branchCourses;
	}
	
	/**
	 * @return the campaigns
	 */
	public Set<Campaign> getCampaigns() {
		return campaigns;
	}

	/**
	 * @param campaigns the campaigns to set
	 */
	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	/**
	 * @return the giftInventories
	 */
	public Set<GiftInventory> getGiftInventories() {
		return giftInventories;
	}

	/**
	 * @param giftInventories the giftInventories to set
	 */
	public void setGiftInventories(Set<GiftInventory> giftInventories) {
		this.giftInventories = giftInventories;
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

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.BRANCHID, this.branchId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.LOCATION, this.location);
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.CONTACT, this.contact);
		
		returnJson.put(Key.ADMIN, this.admin.toJson());
		
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
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.CONTACT, this.contact);
		
		returnJson.put(Key.ADMIN, this.admin.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		//branch manager, teacher, classroom, student, parent
		
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
		
		JSONArray parentArr = new JSONArray();
		for(Parent p : ParentDAO.getParentsByBranch(this)){
			parentArr.add(p.toJson());
		}
		returnJson.put(Key.PARENTS, parentArr);
		
		JSONArray bcArr = new JSONArray();
		for(BranchCourse bc : BranchCourseDAO.getBranchCoursesByBranch(this)){
			bcArr.add(bc.toJson());
		}
		returnJson.put(Key.BRANCHCOURSES, bcArr);
		
		return returnJson;
	}
}
