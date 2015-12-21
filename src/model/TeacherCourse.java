package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ResultDAO;
import dataManager.ScheduleDAO;
import system.Config;
import system.Key;
import system.Value;

public class TeacherCourse {
	private long teacherCourseId;

	private Course course;
	private Teacher teacher;
	private Set<Schedule> schedules;
	private Set<Result> results;

	private long objStatus;
	private Date createDate;
	private String remark;

	public TeacherCourse() {
	}

	/**
	 * @param course
	 * @param teacher
	 */
	public TeacherCourse(Course course, Teacher teacher) {
		super();
		this.course = course;
		this.teacher = teacher;
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
	 * @return the results
	 */
	public Set<Result> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Set<Result> results) {
		this.results = results;
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

		returnJson.put(Key.TEACHERCOURSEID, this.teacherCourseId);
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.COURSE, this.course.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}

	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERCOURSEID, this.teacherCourseId);
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.COURSE, this.course.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		JSONArray scheduleArr = new JSONArray();
		for (Schedule s : ScheduleDAO.getSchedulesByTeacherCourse(this)) {
			scheduleArr.add(s.toJson());
		}
		returnJson.put(Key.SCHEDULES, scheduleArr);
		
		JSONArray resultArr = new JSONArray();
		for(Result r : ResultDAO.getResultsByTeacherCourse(this)){
			resultArr.add(r.toJson());
		}
		returnJson.put(Key.RESULTS, resultArr);

		return returnJson;
	}

}
