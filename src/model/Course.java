package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import dataManager.SalaryDAO;
import system.Config;
import system.Key;
import system.Value;

public class Course {
	private long courseId;
	private String name;
	private String courseLevel;
	private String courseCost;
	private long courseCapacity;
	
	private Set<Result> results;
	private Set<Attendance> attendances;
	private Teacher teacher;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Course(){}
	
	public Course(String name, String courseLevel, String courseCost,long courseCapacity,
			Teacher teacher) {
		super();
		this.name = name;
		this.courseLevel = courseLevel;
		this.courseCost = courseCost;
		this.courseCapacity = courseCapacity;
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
	public long getCourseCapacity() {
		return courseCapacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCourseCapacity(long courseCapacity) {
		this.courseCapacity = courseCapacity;
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
	 * @return the attendances
	 */
	public Set<Attendance> getAttendances() {
		return attendances;
	}
	/**
	 * @param attendances the attendances to set
	 */
	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
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
		
		returnJson.put(Key.COURSEID, this.courseId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.COURSECOST, this.courseCost);
		returnJson.put(Key.COURSECAPACITY, this.courseCapacity);
		
		returnJson.put(Key.TEACHER, this.teacher.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COURSEID, this.courseId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.COURSECOST, this.courseCost);
		returnJson.put(Key.COURSECAPACITY, this.courseCapacity);
		
		
		returnJson.put(Key.TEACHER, this.teacher.toJson());//need to implement
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray resultArr = new JSONArray();
		for(Result r : ResultDAO.getResultsByCourse(this)){
			resultArr.add(r.toJson());
		}
		returnJson.put(Key.RESULTS, resultArr);
		
//		JSONArray attendanceArr = new JSONArray();
//		for(Attendance a : AttendanceDAO.getBillsByStudent(this)){
//			attendanceArr.add(a.toJson());
//		}
//		returnJson.put(Key.ATTENDANCE, attendanceArr);
		
		return returnJson;
	}
}
