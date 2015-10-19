package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class BranchManager {
	private long branchManagerId;
	private String email;
	private String passwordSalt;
	private String passwordHash;
	private String contactNumber;
	private String branchMangerNric;
	
	private Branch branch;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public BranchManager(){
		
	}
	
	public BranchManager(String email, String passwordSalt, String passwordHash, Branch branch, String contactNumber, String branchManagerNric) {
		super();
		this.email = email;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.branch = branch;
		this.setCreateDate(new Date());
		this.setObjStatus(Value.ACTIVED);
		this.contactNumber = contactNumber;
		this.branchMangerNric = branchManagerNric;
	}

	/**
	 * @return the branchManagerId
	 */
	public long getBranchManagerId() {
		return branchManagerId;
	}

	/**
	 * @param branchManagerId the branchManagerId to set
	 */
	public void setBranchManagerId(long branchManagerId) {
		this.branchManagerId = branchManagerId;
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
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the branchMangerNric
	 */
	public String getBranchMangerNric() {
		return branchMangerNric;
	}

	/**
	 * @param branchMangerNric the branchMangerNric to set
	 */
	public void setBranchMangerNric(String branchMangerNric) {
		this.branchMangerNric = branchMangerNric;
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

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.BRANCHMANAGERID, this.branchManagerId);
		returnJson.put(Key.EMAIL, this.email);
		returnJson.put(Key.CONTACTNUMBER, this.contactNumber);
		
		returnJson.put(Key.BRANCH, this.branch.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}


}
