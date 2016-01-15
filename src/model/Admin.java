package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import system.Config;
import system.Key;
import system.Value;

public class Admin {
	private long adminId;
	private String name;
	private String email;
	private String passwordSalt;
	private String passwordHash;

	private long objStatus;
	private Date createDate;
	private String remark;

	private Set<Branch> branches;

	public Admin() {
	}

	public Admin(String name, String email, String passwordSalt,
			String passwordHash) {
		super();
		this.name = name;
		this.email = email;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the adminId
	 */
	public long getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 *            the adminId to set
	 */
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param email
	 *            the email to set
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
	 * @param passwordSalt
	 *            the passwordSalt to set
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
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus
	 *            the objStatus to set
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
	 * @param createDate
	 *            the createDate to set
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
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the branches
	 */
	public Set<Branch> getBranches() {
		return branches;
	}

	/**
	 * @param branches
	 *            the branches to set
	 */
	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.ADMINID, this.adminId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}

	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.ADMINID, this.adminId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.EMAIL, this.email);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		JSONArray branchArr = new JSONArray();
		for (Branch b : BranchDAO.getBranchesByAdmin(this)) {
			branchArr.add(b.toJson());
		}
		returnJson.put(Key.BRANCHS, branchArr);

		return returnJson;
	}
}
