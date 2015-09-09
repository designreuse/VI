package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Value;

public class Attendance {
	private long attendaceId;
	private Date planStartDate;
	private String location;
	private Date actualStartDate;
	
	private Course course;
	private Student student;
	private Classroom classroom;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Attendance(){}
	
	public Attendance(Date planStartDate, Date planEndDate, String location,
			Date actualStartDate, Date actualEndDate, Teacher teacher,
			Course course, Student student) {
		super();
		this.planStartDate = planStartDate;
		this.location = location;
		this.actualStartDate = actualStartDate;
		this.course = course;
		this.student = student;
		this.setCreateDate(new Date());
		this.setObjStatus(Value.ACTIVED);
	}

	/**
	 * @return the attendaceId
	 */
	public long getScheduleId() {
		return attendaceId;
	}
	/**
	 * @param attendaceId the attendaceId to set
	 */
	public void setScheduleId(long attendaceId) {
		this.attendaceId = attendaceId;
	}
	/**
	 * @return the planStartDate
	 */
	public Date getPlanStartDate() {
		return planStartDate;
	}
	/**
	 * @param planStartDate the planStartDate to set
	 */
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the actualStartDate
	 */
	public Date getActualStartDate() {
		return actualStartDate;
	}
	/**
	 * @param actualStartDate the actualStartDate to set
	 */
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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
	
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		return returnJson;
	}
}
