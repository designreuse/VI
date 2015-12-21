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
	private String name;
	private String description;
	private double resultValue;
	private Date resultDate;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	private TeacherCourse teacherCourse;
	private Student student;
	
	public Result(){}
	
	public Result(String name, String description, double resultValue, Date resultDate, TeacherCourse teacherCourse, Student student) {
		super();
		this.name = name;
		this.description = description;
		this.resultValue = resultValue;
		this.resultDate = resultDate;
		this.teacherCourse = teacherCourse;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the teacherCourse
	 */
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}

	/**
	 * @param teacherCourse the teacherCourse to set
	 */
	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
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

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, this.description);
		returnJson.put(Key.RESULTID, this.resultId);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
		returnJson.put(Key.RESULTDATE, Config.SDF.format(this.resultDate));
		
		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
		returnJson.put(Key.STUDENT, this.student.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
