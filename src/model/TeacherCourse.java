package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class TeacherCourse {
	private long teacherCourseId;
	
	private Teacher teacher;
	private Course course;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public TeacherCourse(){}
	
	public TeacherCourse(Teacher teacher, Course course) {
		super();
		this.teacher = teacher;
		this.course = course;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the teacherCourseId
	 */
	public long getTeacherCourseId() {
		return teacherCourseId;
	}

	/**
	 * @param teacherCourseId the teacherCourseId to set
	 */
	public void setTeacherCourseId(long teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
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

	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.TEACHERCOURSEID, this.teacherCourseId);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.TEACHERCOURSEID, this.teacherCourseId);
		returnJson.put(Key.TEACHER, this.teacher.toJsonSimple());
		returnJson.put(Key.COURSE, this.course.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
