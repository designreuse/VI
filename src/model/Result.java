package model;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.SalaryDAO;
import system.Config;
import system.Key;
import system.Value;

public class Result {
	private long resultId;
	private double resultValue;
	private Date resultDate;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Course course;
	private Student student;
	
	public Result(){}
	
	public Result(double resultValue, Course course, Student student) {
		super();
		this.resultValue = resultValue;
		this.course = course;
		this.student = student;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the resultId
	 */
	public long getResultId() {
		return resultId;
	}

	/**
	 * @param resultId the resultId to set
	 */
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	/**
	 * @return the resultValue
	 */
	public double getResultValue() {
		return resultValue;
	}

	/**
	 * @param resultValue the resultValue to set
	 */
	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
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
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	

	/**
	 * @return the resultDate
	 */
	public Date getResultDate() {
		return resultDate;
	}
	/**
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.RESULT, this.resultId);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
		returnJson.put(Key.RESULTDATE, this.resultDate);
		
		returnJson.put(Key.COURSE, this.course.toJson());//need to implement
		returnJson.put(Key.STUDENT, this.student.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
