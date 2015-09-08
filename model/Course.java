package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Value;

public class Course {
	private long courseId;
	private String name;
	private String courseLevel;
	private String courseCost;
	private long capacity;
	
	private Set<Result> results;
	private Set<Schedule> schedules;
	private Teacher teacher;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Course(){}
	
	public Course(String name, String courseLevel, String courseCost,long capacity,
			Teacher teacher) {
		super();
		this.name = name;
		this.courseLevel = courseLevel;
		this.courseCost = courseCost;
		this.capacity = capacity;
		this.teacher = teacher;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the courseId
	 */
	public long getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
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
	 * @return the courseLevel
	 */
	public String getCourseLevel() {
		return courseLevel;
	}
	/**
	 * @param courseLevel the courseLevel to set
	 */
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	/**
	 * @return the courseCost
	 */
	public String getCourseCost() {
		return courseCost;
	}
	/**
	 * @param courseCost the courseCost to set
	 */
	public void setCourseCost(String courseCost) {
		this.courseCost = courseCost;
	}
	
	/**
	 * @return the capacity
	 */
	public long getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(long capacity) {
		this.capacity = capacity;
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
	 * @return the schedules
	 */
	public Set<Schedule> getSchedules() {
		return schedules;
	}
	/**
	 * @param schedules the schedules to set
	 */
	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
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
		
		return returnJson;
	}
}
