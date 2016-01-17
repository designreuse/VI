package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import system.Config;
import system.Key;
import system.Value;

public class ScheduleEvent {
	private long scheduleEventId;

	private Date planStartDate;
	private Date planEndDate;
	private long studentAmount;

	private Schedule schedule;
	private Classroom classroom;
	private Set<Attendance> attendances;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	public ScheduleEvent(){}
	
	public ScheduleEvent(Date planStartDate, Date planEndDate,
			Schedule schedule, Classroom classroom) {
		super();
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.schedule = schedule;
		this.classroom = classroom;
		this.studentAmount = 0;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the scheduleEventId
	 */
	public long getScheduleEventId() {
		return scheduleEventId;
	}

	/**
	 * @param scheduleEventId the scheduleEventId to set
	 */
	public void setScheduleEventId(long scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
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
	 * @return the studentAmount
	 */
	public long getStudentAmount() {
		return studentAmount;
	}

	/**
	 * @param studentAmount the studentAmount to set
	 */
	public void setStudentAmount(long studentAmount) {
		this.studentAmount = studentAmount;
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

	public JSONObject toJsonSimple(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.SCHEDULEEVENTID, this.scheduleEventId);
		returnJson.put(Key.PLANSTARTDATE, Config.SDF.format(this.planStartDate));
		returnJson.put(Key.PLANENDDATE, Config.SDF.format(this.planEndDate));
		returnJson.put(Key.STUDENTAMOUNT, this.studentAmount);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.SCHEDULEEVENTID, this.scheduleEventId);
		returnJson.put(Key.PLANSTARTDATE, Config.SDF.format(this.planStartDate));
		returnJson.put(Key.PLANENDDATE, Config.SDF.format(this.planEndDate));
		returnJson.put(Key.STUDENTAMOUNT, this.studentAmount);
		
		returnJson.put(Key.SCHEDULE, this.schedule.toJson());
		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.SCHEDULEEVENTID, this.scheduleEventId);
		returnJson.put(Key.PLANSTARTDATE, Config.SDF.format(this.planStartDate));
		returnJson.put(Key.PLANENDDATE, Config.SDF.format(this.planEndDate));
		returnJson.put(Key.STUDENTAMOUNT, this.studentAmount);
		
		returnJson.put(Key.SCHEDULE, this.schedule.toJsonSimpleTeacher());
		returnJson.put(Key.CLASSROOM, this.classroom.toJsonSimple());
		
		JSONArray attendanceArr = new JSONArray();
		for (Attendance a : AttendanceDAO.getAttendancesByScheduleEvent(this)) {
			attendanceArr.add(a.toJson());
		}
		returnJson.put(Key.ATTENDANCES, attendanceArr);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonStudent(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.SCHEDULEEVENTID, this.scheduleEventId);
		returnJson.put(Key.PLANSTARTDATE, Config.SDF.format(this.planStartDate));
		returnJson.put(Key.PLANENDDATE, Config.SDF.format(this.planEndDate));
		returnJson.put(Key.STUDENTAMOUNT, this.studentAmount);
		
		returnJson.put(Key.SCHEDULE, this.schedule.toJsonSimpleTeacher());
		returnJson.put(Key.CLASSROOM, this.classroom.toJsonSimple());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonAttendances(){
		JSONObject returnJson = new JSONObject();
				
		returnJson.put(Key.SCHEDULEEVENTID, this.scheduleEventId);
		returnJson.put(Key.PLANSTARTDATE, Config.SDF.format(this.planStartDate));
		returnJson.put(Key.PLANENDDATE, Config.SDF.format(this.planEndDate));
		returnJson.put(Key.STUDENTAMOUNT, this.studentAmount);
		
		JSONArray attendanceArr = new JSONArray();
		for (Attendance a : AttendanceDAO.getAttendancesByScheduleEvent(this)) {
			attendanceArr.add(a.toJson());
		}
		returnJson.put(Key.ATTENDANCES, attendanceArr);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
