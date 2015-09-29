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
	
	private PointEvent(){}

	public PointEvent(long pointAmount, String description, Student student, Teacher teacher) {
		super();
		this.pointAmount = pointAmount;
		this.description = description;
		this.student = student;
		this.teacher = teacher;
		this.setCreateDate(new Date());
	}

	public long getPointEventId() {
		return pointEventId;
	}

	public void setPointEventId(long pointEventId) {
		this.pointEventId = pointEventId;
	}

	public long getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(long pointAmount) {
		this.pointAmount = pointAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.POINTEVENTID, this.pointEventId);
		returnJson.put(Key.POINTAMOUNT, this.pointAmount);
		returnJson.put(Key.DESCRIPTION, this.description);
		
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		
		return returnJson;
	}

}
