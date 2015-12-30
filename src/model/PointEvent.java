package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;

public class PointEvent {
	private long pointEventId;
	private long pointAmount;
	private String description;
	
	private Student student;
	private Teacher teacher;
	
	private Date createDate;
	
	public PointEvent(){}

	public PointEvent(long pointAmount, String description, Student student, Teacher teacher) {
		super();
		this.pointAmount = pointAmount;
		this.description = description;
		this.student = student;
		this.teacher = teacher;
		this.setCreateDate(new Date());
	}

	/**
	 * @return the pointEventId
	 */
	public long getPointEventId() {
		return pointEventId;
	}

	/**
	 * @param pointEventId the pointEventId to set
	 */
	public void setPointEventId(long pointEventId) {
		this.pointEventId = pointEventId;
	}

	/**
	 * @return the pointAmount
	 */
	public long getPointAmount() {
		return pointAmount;
	}

	/**
	 * @param pointAmount the pointAmount to set
	 */
	public void setPointAmount(long pointAmount) {
		this.pointAmount = pointAmount;
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

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.POINTEVENTID, this.pointEventId);
		returnJson.put(Key.POINTAMOUNT, this.pointAmount);
		returnJson.put(Key.DESCRIPTION, this.description);
		
		returnJson.put(Key.STUDENT, this.student.toJson());
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		
		return returnJson;
	}

}
