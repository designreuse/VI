package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CourseDAO;
import dataManager.ResultDAO;
import dataManager.SalaryDAO;
import dataManager.ScheduleDAO;
import dataManager.TeacherCourseDAO;
import system.Config;
import system.Key;
import system.Value;

public class Course {
	private long courseId;
	private String name;
	private String description;
	private String courseCost;
	private long courseCapacity;
	
	private Set<Schedule> schedules;
	private Set<BranchCourse> branchCourses;
	private Set<TeacherStudentCourse> teacherStudentCourses;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Course(){}
	
	public Course(String name, String description, String courseCost, long courseCapacity) {
		super();
		this.name = name;
		this.description = description;
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
	 * @return the teacherStudentCourses
	 */
	public Set<TeacherStudentCourse> getTeacherStudentCourses() {
		return teacherStudentCourses;
	}

	/**
	 * @param teacherStudentCourses the teacherStudentCourses to set
	 */
	public void setTeacherStudentCourses(Set<TeacherStudentCourse> teacherStudentCourses) {
		this.teacherStudentCourses = teacherStudentCourses;
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
		returnJson.put(Key.COURSECOST, this.courseCost);
		returnJson.put(Key.COURSECAPACITY, this.courseCapacity);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray scheduleArr = new JSONArray();
		for (Schedule s : ScheduleDAO.getSchedulesByCourse(this)) {
			scheduleArr.add(s.toJson());
		}
		returnJson.put(Key.SCHEDULES, scheduleArr);
		
		//TODO implement it if necessary
//		JSONArray branchCourseArr = new JSONArray();
//		for(BranchCourse bc : BranchCourseDAO.getBranchCoursesByCourse(this)){
//			branchCourseArr.add(bc.toJson());
//		}
//		returnJson.put(Key.TEACHERCOURSES, branchCourseArr);
		
		return returnJson;
	}
}
