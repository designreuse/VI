package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class StudentSchedule {
	private long studentScheduleId;
	
	private Student student;
	private Schedule schedule;
	
	private Set<Attendance> attendances;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public StudentSchedule(){}

	public StudentSchedule(Student student, Schedule schedule) {
		super();
		this.student = student;
		this.schedule = schedule;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the studentScheduleId
	 */
	public long getStudentScheduleId() {
		return studentScheduleId;
	}

	/**
	 * @param studentScheduleId the studentScheduleId to set
	 */
	public void setStudentScheduleId(long studentScheduleId) {
		this.studentScheduleId = studentScheduleId;
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

		returnJson.put(Key.STUDENT, this.student.toJson());
		returnJson.put(Key.SCHEDULE, this.schedule.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
}
