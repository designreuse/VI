package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import dataManager.SalaryDAO;
import dataManager.TeacherCourseDAO;
import system.Config;
import system.Key;
import system.Value;

public class Course {
	private long courseId;
	private String name;
	private String description;
	private String courseLevel;
	private String courseCost;
	private long courseCapacity;
	
	private Set<Result> results;
	private Set<TeacherCourse> teacherCourses;
	private Set<BranchCourse> branchCourses;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Course(){}
	
	public Course(String name, String description, String courseLevel, String courseCost, long courseCapacity) {
		super();
		this.name = name;
		this.description = description;
		this.courseLevel = courseLevel;
		this.courseCost = courseCost;
		this.courseCapacity = courseCapacity;
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
	 * @return the courseCapacity
	 */
	public long getCourseCapacity() {
		return courseCapacity;
	}

	/**
	 * @param courseCapacity the courseCapacity to set
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
	 * @return the teacherCourses
	 */
	public Set<TeacherCourse> getTeacherCourses() {
		return teacherCourses;
	}

	/**
	 * @param teacherCourses the teacherCourses to set
	 */
	public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}
	
	/**
	 * @return the branchCourses
	 */
	public Set<BranchCourse> getBranchCourses() {
		return branchCourses;
	}

	/**
	 * @param branchCourses the branchCourses to set
	 */
	public void setBranchCourses(Set<BranchCourse> branchCourses) {
		this.branchCourses = branchCourses;
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
		returnJson.put(Key.DESCRIPTION, this.description);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.COURSECOST, this.courseCost);
		returnJson.put(Key.COURSECAPACITY, this.courseCapacity);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.COURSEID, this.courseId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, this.description);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.COURSECOST, this.courseCost);
		returnJson.put(Key.COURSECAPACITY, this.courseCapacity);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray resultArr = new JSONArray();
		for(Result r : ResultDAO.getResultsByCourse(this)){
			resultArr.add(r.toJson());
		}
		returnJson.put(Key.RESULTS, resultArr);
		
		JSONArray teacherCourseArr = new JSONArray();
		for(TeacherCourse tc : TeacherCourseDAO.getTeacherCoursesByCourse(this)){
			teacherCourseArr.add(tc.toJson());
		}
		returnJson.put(Key.TEACHERCOURSES, teacherCourseArr);
		
		//TODO implement it if necessary
//		JSONArray branchCourseArr = new JSONArray();
//		for(BranchCourse bc : BranchCourseDAO.getBranchCoursesByCourse(this)){
//			branchCourseArr.add(bc.toJson());
//		}
//		returnJson.put(Key.TEACHERCOURSES, branchCourseArr);
		
		return returnJson;
	}
}
