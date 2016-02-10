package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ScheduleEventDAO;
import system.Config;
import system.Key;
import system.Value;

public class Schedule {
	private long scheduleId;
	private String name;
	private String description;
	private long dayOfWeek; //1-Mon, 2-Tue ...
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	private long recFrequency;
	private long duration;
	
	private Course course;
	private Teacher teacher;
	
	private Set<ScheduleEvent> scheduleEvents; 
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Schedule(){}

	public Schedule(String name, String description, Date scheduleStartDate,
					Date scheduleEndDate, long duration, Course course, Teacher teacher) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setDayOfWeek(dayOfWeek);
		this.setScheduleStartDate(scheduleStartDate);
		this.setScheduleEndDate(scheduleEndDate);
		this.setTeacher(teacher);
		this.setCourse(course);
		this.setRecFrequency(7);
		this.setDuration(duration);
	
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
	 * @return the dayOfWeek
	 */
	public long getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(long dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the scheduleStartDate
	 */
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * @param scheduleStartDate the scheduleStartDate to set
	 */
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the scheduleEndDate
	 */
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * @param scheduleEndDate the scheduleEndDate to set
	 */
	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the recFrequency
	 */
	public long getRecFrequency() {
		return recFrequency;
	}

	/**
	 * @param recFrequency the recFrequency to set
	 */
	public void setRecFrequency(long recFrequency) {
		this.recFrequency = recFrequency;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
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
	 * @return the scheduleEvents
	 */
	public Set<ScheduleEvent> getScheduleEvents() {
		return scheduleEvents;
	}

	/**
	 * @param scheduleEvents the scheduleEvents to set
	 */
	public void setScheduleEvents(Set<ScheduleEvent> scheduleEvents) {
		this.scheduleEvents = scheduleEvents;
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
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.DAYOFWEEK, dayOfWeek);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		returnJson.put(Key.RECFREQUENCY, this.recFrequency);
		returnJson.put(Key.DURATION, this.duration);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonSimpleTeacher(){
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.DAYOFWEEK, dayOfWeek);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		returnJson.put(Key.RECFREQUENCY, this.recFrequency);
		returnJson.put(Key.DURATION, this.duration);
		
		returnJson.put(Key.TEACHER, this.teacher.toJsonSimple());
		returnJson.put(Key.COURSE, this.course.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.DAYOFWEEK, dayOfWeek);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		returnJson.put(Key.RECFREQUENCY, this.recFrequency);
		returnJson.put(Key.DURATION, this.duration);
		
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.COURSE, this.course.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonCourse() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.DAYOFWEEK, dayOfWeek);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		returnJson.put(Key.RECFREQUENCY, this.recFrequency);
		returnJson.put(Key.DURATION, this.duration);
		
		returnJson.put(Key.COURSE, this.course.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	//May not need it anymore, delete once confirmation is made
//	public JSONObject toCalendarJson() {
//		JSONObject returnJson = new JSONObject();
//		returnJson.put(Key.ID, this.scheduleId);
//		returnJson.put(Key.TITLE, this.name);
//		returnJson.put(Key.DESCRIPTION, description);
//		returnJson.put(Key.START, Config.SDF.format(this.scheduleStartDate));
//		returnJson.put(Key.END, Config.SDF.format(this.scheduleEndDate));
//		returnJson.put(Key.ALLDAY, false);
//		
//		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
//		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
//		
//		returnJson.put(Key.OBJSTATUS, this.objStatus);
//		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
//		returnJson.put(Key.REMARK, this.remark);
//		
//		return returnJson;
//	}
	
	//TODO finish the method
	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.DAYOFWEEK, dayOfWeek);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		returnJson.put(Key.RECFREQUENCY, this.recFrequency);
		returnJson.put(Key.DURATION, this.duration);
		
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.COURSE, this.course.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray scheduleEventArr = new JSONArray();
		for (ScheduleEvent se : ScheduleEventDAO.getScheduleEventsBySchedule(this)) {
			scheduleEventArr.add(se.toScheduleJson());
		}
		returnJson.put(Key.ATTENDANCES, scheduleEventArr);
		
		return returnJson;
	}
}
