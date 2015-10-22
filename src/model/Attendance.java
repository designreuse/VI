package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Attendance {
	private long attendanceId;
	private Date actualStartDate;
	private long attendanceStatus;
	
	private Schedule schedule;
	private Student student;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Attendance(){}

	public Attendance(Schedule schedule, Student student) {
		super();
		this.attendanceStatus = 0;
		this.schedule = schedule;
		this.student = student;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the attendanceId
	 */
	public long getAttendanceId() {
		return attendanceId;
	}

	/**
	 * @param attendanceId the attendanceId to set
	 */
	public void setAttendanceId(long attendanceId) {
		this.attendanceId = attendanceId;
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
	 * @return the attendanceStatus
	 */
	public long getAttendanceStatus() {
		return attendanceStatus;
	}

	/**
	 * @param attendanceStatus the attendanceStatus to set
	 */
	public void setAttendanceStatus(long attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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
	
	//shall i add in a checker to return actual date?
	//no, the logic checking is handle in the controller.
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		
		returnJson.put(Key.SCHEDULE, this.schedule.toJson());
		returnJson.put(Key.STUDENT, this.student.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonMark(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		returnJson.put(Key.ACTUALSTARTDATE, Config.SDF.format(this.actualStartDate));

		returnJson.put(Key.SCHEDULE, this.schedule.toJson());
		returnJson.put(Key.STUDENT, this.student.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toScheduleJson(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		
		returnJson.put(Key.STUDENT, this.student.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toScheduleJsonMark(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		returnJson.put(Key.ACTUALSTARTDATE, Config.SDF.format(this.actualStartDate));

		returnJson.put(Key.STUDENT, this.student.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
