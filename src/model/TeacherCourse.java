package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class TeacherCourse {
	private long teacherCourseId;
	private long courseId;
	private long teacherId;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public TeacherCourse(){}

	public TeacherCourse(long courseId, long teacherId) {
		super();
		this.courseId = courseId;
		this.teacherId = teacherId;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getTeacherCourseId() {
		return teacherCourseId;
	}

	public void setTeacherCourseId(long teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
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
		
		returnJson.put(Key.TEACHERCOURSEID, this.teacherCourseId);
		returnJson.put(Key.TEACHERID, this.teacherId);
		returnJson.put(Key.COURSEID, this.courseId);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}

}
