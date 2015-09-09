package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Classroom {
	private long classroomId;
	private String name;
	private long roomCapacity;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Branch branch;
	private Set<Attendance> attendances;
	
	public Classroom(){}
	
	

	public Classroom(String name, long roomCapacity, Branch branch) {
		super();
		this.name = name;
		this.roomCapacity = roomCapacity;
		this.branch = branch;
		this.setCreateDate(new Date());
		this.setObjStatus(Value.ACTIVED);
	}



	public long getClassroomId() {
		return classroomId;
	}



	public void setClassroomId(long classroomId) {
		this.classroomId = classroomId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public long getRoomCapacity() {
		return roomCapacity;
	}



	public void setCapacity(long roomCapacity) {
		this.roomCapacity = roomCapacity;
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



	public Branch getBranch() {
		return branch;
	}



	public void setBranch(Branch branch) {
		this.branch = branch;
	}



	public Set<Attendance> getAttendances() {
		return attendances;
	}



	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}



	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		return returnJson;
	}
}
