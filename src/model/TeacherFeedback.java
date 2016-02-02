package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class TeacherFeedback {
	private long teacherFeedbackId;
	
	private String content;
	private Result result;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public TeacherFeedback(){}

	public TeacherFeedback(String content, Result result) {
		super();
		this.content = content;
		this.result = result;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the teacherFeedbackId
	 */
	public long getTeacherFeedbackId() {
		return teacherFeedbackId;
	}

	/**
	 * @param teacherFeedbackId the teacherFeedbackId to set
	 */
	public void setTeacherFeedbackId(long teacherFeedbackId) {
		this.teacherFeedbackId = teacherFeedbackId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
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
		returnJson.put(Key.TEACHERFEEDBACKID, this.teacherFeedbackId);
		returnJson.put(Key.CONTENT, this.content);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
//	public JSONObject toJsonShowStudentAndTeacher(){
//		JSONObject returnJson = new JSONObject();
//		returnJson.put(Key.TEACHERFEEDBACKID, this.teacherFeedbackId);
//		returnJson.put(Key.CONTENT, this.content);
//		returnJson.put(Key.TEACHERSTUDENTCOURSE, this.result.toJsonShowStudentAndTeacher());
//		
//		returnJson.put(Key.OBJSTATUS, this.objStatus);
//		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
//		returnJson.put(Key.REMARK, this.remark);
//		
//		return returnJson;
//	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.TEACHERFEEDBACKID, this.teacherFeedbackId);
		returnJson.put(Key.CONTENT, this.content);
		
		returnJson.put(Key.RESULT, this.result.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
}
