package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Attendance {
	private long attendanceId;
	private Date actualStartDate;
	private Date actualEndDate;
	private Date planStartDate;
	private Date planEndDate;
	private long attendanceStatus;
	private StudentSchedule studentSchedule;
	private Classroom classroom;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Attendance(){}

	public Attendance(StudentSchedule studentSchedule, Classroom classroom) {
		super();
		this.attendanceStatus = 0;
		this.setStudentSchedule(studentSchedule);
		this.setClassroom(classroom);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public Attendance(Date planStartDate, Date planEndDate, StudentSchedule studentSchedule, Classroom classroom) {
		super();
		this.attendanceStatus = 0;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.setStudentSchedule(studentSchedule);
		this.setClassroom(classroom);
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
	 * @return the actualEndDate
	 */
	public Date getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate the actualEndDate to set
	 */
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
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
	 * @return the planEndDate
	 */
	public Date getPlanEndDate() {
		return planEndDate;
	}

	/**
	 * @param planEndDate the planEndDate to set
	 */
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
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
	 * @return the studentSchedule
	 */
	public StudentSchedule getStudentSchedule() {
		return studentSchedule;
	}

	/**
	 * @param studentSchedule the studentSchedule to set
	 */
	public void setStudentSchedule(StudentSchedule studentSchedule) {
		this.studentSchedule = studentSchedule;
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
	
	public JSONObject toJsonSimple(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		returnJson.put(Key.PLANSTARTDATE, this.planStartDate);
		returnJson.put(Key.PLANENDDATE, this.planEndDate);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	//shall i add in a checker to return actual date?
	//no, the logic checking is handle in the controller.
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
		returnJson.put(Key.PLANSTARTDATE, this.planStartDate);
		returnJson.put(Key.PLANENDDATE, this.planEndDate);
		
		returnJson.put(Key.STUDENTSCHEDULE, this.studentSchedule.toJson());
		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
		
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

		returnJson.put(Key.STUDENTSCHEDULE, this.studentSchedule.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toScheduleJson(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
		returnJson.put(Key.PLANSTARTDATE, this.planStartDate);
		returnJson.put(Key.PLANENDDATE, this.planEndDate);
		
		returnJson.put(Key.STUDENTSCHEDULE, this.studentSchedule.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
//	public JSONObject toScheduleJsonMark(){
//		JSONObject returnJson = new JSONObject();
//				
//		returnJson.put(Key.ATTENDANCEID, this.attendanceId);
//		returnJson.put(Key.ATTENDANCESTATUS, this.attendanceStatus);
//		returnJson.put(Key.ACTUALSTARTDATE, Config.SDF.format(this.actualStartDate));
//
//		returnJson.put(Key.STUDENT, this.classroom.toJson());
//
//		returnJson.put(Key.OBJSTATUS, this.objStatus);
//		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
//		returnJson.put(Key.REMARK, this.remark);
//		
//		return returnJson;
//	}
}
