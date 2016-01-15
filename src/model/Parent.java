package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.StudentDAO;
import system.Config;
import system.Key;
import system.Value;

public class Parent {
	private long parentId;
	private String name;
	private String passwordSalt;
	private String passwordHash;
	private String contact;
	private String email;
	private String parentNric;
	private String occupation;
	private String relationship;

	private Set<Student> students;
	private Branch branch;

	private long objStatus;
	private Date createDate;
	private String remark;

	public Parent() {}

	public Parent(String name, String passwordSalt, String passwordHash, String contact, String email,
			String parentNric, String occupation, String relationship, Branch branch) {
		this.name = name;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.contact = contact;
		this.email = email;
		this.parentNric = parentNric;
		this.occupation = occupation;
		this.relationship = relationship;
		this.branch = branch;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
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

//	/**
//	 * @return the address
//	 */
//	public String getAddress() {
//		return address;
//	}
//
//	/**
//	 * @param address the address to set
//	 */
//	public void setAddress(String address) {
//		this.address = address;
//	}

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
	 * @return the parentNric
	 */
	public String getParentNric() {
		return parentNric;
	}

	/**
	 * @param parentNric the parentNric to set
	 */
	public void setParentNric(String parentNric) {
		this.parentNric = parentNric;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
	
	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.PARENTID, this.parentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.PARENTNRIC, this.parentNric);
		returnJson.put(Key.OCCUPATION, this.occupation);
		returnJson.put(Key.RELATIONSHIP, this.relationship);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.PARENTID, this.parentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.PARENTNRIC, this.parentNric);
		returnJson.put(Key.OCCUPATION, this.occupation);
		returnJson.put(Key.RELATIONSHIP, this.relationship);
		returnJson.put(Key.BRANCH, this.branch.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.PARENTID, this.parentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.PARENTNRIC, this.parentNric);
		returnJson.put(Key.OCCUPATION, this.occupation);
		returnJson.put(Key.RELATIONSHIP, this.relationship);
		returnJson.put(Key.BRANCH, this.branch.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray studentArr = new JSONArray();
		for(Student s : StudentDAO.getStudentsByParent(this)){
			studentArr.add(s.toJson());
		}
		returnJson.put(Key.STUDENTS, studentArr);

		return returnJson;
	}

	
}
