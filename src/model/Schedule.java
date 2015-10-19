package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import system.Config;
import system.Key;
import system.Value;

public class Schedule {
	private long scheduleId;
	private Date planStartDate;
	
	private TeacherCourse teacherCourse;
	private Classroom classroom;
	
	private Set<Attendance> attendances; 
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Schedule(){}

	public Schedule(Date planStartDate, TeacherCourse teacherCourse,
			Classroom classroom) {
		super();
		this.planStartDate = planStartDate;
		this.teacherCourse = teacherCourse;
		this.classroom = classroom;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the scheduleId
	 */
	public long getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
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
	 * @return the classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * @param classroom the classroom to set
	 */
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
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
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.PLANNEDSTARTDATE, Config.SDF.format(this.planStartDate));
		
		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.PLANNEDSTARTDATE, Config.SDF.format(this.planStartDate));
		
		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray attendanceArr = new JSONArray();
		for (Attendance a : AttendanceDAO.getAttendancesBySchedule(this)) {
			attendanceArr.add(a.toJson());
		}
		returnJson.put(Key.ATTENDANCES, attendanceArr);
		
		return returnJson;
	}
}
