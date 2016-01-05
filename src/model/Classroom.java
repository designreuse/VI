package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ScheduleDAO;
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

	/**
	 * @return the classroomId
	 */
	public long getClassroomId() {
		return classroomId;
	}

	/**
	 * @param classroomId the classroomId to set
	 */
	public void setClassroomId(long classroomId) {
		this.classroomId = classroomId;
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
	 * @return the roomCapacity
	 */
	public long getRoomCapacity() {
		return roomCapacity;
	}

	/**
	 * @param roomCapacity the roomCapacity to set
	 */
	public void setRoomCapacity(long roomCapacity) {
		this.roomCapacity = roomCapacity;
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

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
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

	public JSONObject toJsonSimple(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.CLASSROOMID, this.classroomId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.ROOMCAPACITY, this.roomCapacity);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.CLASSROOMID, this.classroomId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.ROOMCAPACITY, this.roomCapacity);
		
		returnJson.put(Key.BRANCH, this.branch.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.CLASSROOMID, this.classroomId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.ROOMCAPACITY, this.roomCapacity);
		
		returnJson.put(Key.BRANCH, this.branch.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
//		JSONArray scheduleArr = new JSONArray();
//		for(Schedule s: ScheduleDAO.getSchedulesByClassroom(this)){
//			scheduleArr.add(s.toJson());
//		}
//		returnJson.put(Key.SCHEDULES, scheduleArr);
		
		return returnJson;
	}
}
