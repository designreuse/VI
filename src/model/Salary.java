package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Salary {
	private long salaryId;
	private double salaryAmount;
	private Date dueDate;
	
	private Teacher teacher;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Salary(){}
	
	
	public Salary(double salaryAmount, Date dueDate, Teacher teacher) {
		super();
		this.salaryAmount = salaryAmount;
		this.dueDate = dueDate;
		this.teacher = teacher;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the salaryId
	 */
	public long getSalaryId() {
		return salaryId;
	}
	/**
	 * @param salaryId the salaryId to set
	 */
	public void setSalaryId(long salaryId) {
		this.salaryId = salaryId;
	}
	/**
	 * @return the salaryAmount
	 */
	public double getSalaryAmount() {
		return salaryAmount;
	}
	/**
	 * @param salaryAmount the salaryAmount to set
	 */
	public void setSalaryAmount(double salaryAmount) {
		this.salaryAmount = salaryAmount;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		
		returnJson.put(Key.SALARYID, this.salaryId);
		returnJson.put(Key.SALARYAMOUNT, this.salaryAmount);
		returnJson.put(Key.DUEDATE, Config.SDF.format(this.dueDate));
		
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
