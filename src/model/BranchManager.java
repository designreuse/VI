package model;

import java.util.Date;

import org.json.simple.JSONObject;

public class BranchManager {
	private long branchManagerId;
	private String email;
	private String passwordSalt;
	private String passwordHash;
	
	private Branch branch;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public BranchManager(){
		
	}
	
	public BranchManager(String email, String passwordSalt, String passwordHash, Branch branch) {
		super();
		this.email = email;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.branch = branch;
	}

	public long getBranchManagerId() {
		return branchManagerId;
	}

	public void setBranchManagerId(long branchManagerId) {
		this.branchManagerId = branchManagerId;
	}

	public String getEmail() {
		return email;
	}

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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public long getObjStatus() {
		return objStatus;
	}

	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		
		return returnJson;
	}

}
