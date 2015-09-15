package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ResultDAO;
import dataManager.BillDAO;
import system.Config;
import system.Key;
import system.Value;

public class Student {
	private long studentId;
	private String name;
	private String email;
	private String contact;
	private String address;
	private String studentLevel;
	private String passwordSalt;
	private String passwordHash;
	
	private Branch branch;
	private Parent parent;
	private Set<Result> results;
	private Set<Bill> bills;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Student (){}
	
	public Student(String name, String email, String contact, String address, String studentLevel, Parent parent, Branch branch) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.studentLevel = studentLevel;
		this.branch = branch;
		this.parent = parent;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
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
	 * @return the studentLevel
	 */
	public String getStudentLevel() {
		return studentLevel;
	}
	/**
	 * @param studentLevel the studentLevel to set
	 */
	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}
	/**
	 * @return the parent
	 */
	public Parent getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the results
	 */
	public Set<Result> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(Set<Result> results) {
		this.results = results;
	}
	/**
	 * @return the bills
	 */
	public Set<Bill> getBills() {
		return bills;
	}
	/**
	 * @param bills the bills to set
	 */
	public void setBills(Set<Bill> bills) {
		this.bills = bills;
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
	
	
	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.STUDENTLEVEL, this.studentLevel);
		
		returnJson.put(Key.BRANCH, this.branch.toJson());//need to implement
		returnJson.put(Key.PARENT, this.parent.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}

	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.CONTACT, this.contact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.STUDENTLEVEL, this.studentLevel);
		
		returnJson.put(Key.BRANCH, this.branch.toJson());
		returnJson.put(Key.PARENT, this.parent.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray resultArr = new JSONArray();
		for(Result r : ResultDAO.getResultsByStudent(this)){
			resultArr.add(r.toJson());
		}
		returnJson.put(Key.RESULTS, resultArr);
		
		JSONArray billArr = new JSONArray();
		for(Bill k : BillDAO.getBillsByStudent(this)){
			billArr.add(k.toJson());
		}
		returnJson.put(Key.BILLS, billArr);
		
		return returnJson;
	}
	
}
