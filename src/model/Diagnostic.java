package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Diagnostic {
	private long diagnosticId;
	private String subjectName;
	private String resultValue;
	private String feedback;
	
	private Student student;
	private Course course;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Diagnostic(){}

	public Diagnostic(String resultValue, Student student, Course course) {
		super();
		this.resultValue = resultValue;
		this.student = student;
		this.course = course;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public Diagnostic(String subjectName, String resultValue, Student student, Course course) {
		super();
		this.subjectName = subjectName;
		this.resultValue = resultValue;
		this.student = student;
		this.course = course;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public Diagnostic(String subjectName, String resultValue, String feedback, Student student) {
		super();
		this.subjectName = subjectName;
		this.resultValue = resultValue;
		this.feedback = feedback;
		this.student = student;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the diagnosticId
	 */
	public long getDiagnosticId() {
		return diagnosticId;
	}

	/**
	 * @param diagnosticId the diagnosticId to set
	 */
	public void setDiagnosticId(long diagnosticId) {
		this.diagnosticId = diagnosticId;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * @return the resultValue
	 */
	public String getResultValue() {
		return resultValue;
	}

	/**
	 * @param resultValue the resultValue to set
	 */
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
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
	
	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.DIAGNOSTICID, this.diagnosticId);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
		returnJson.put(Key.FEEDBACK	, this.feedback);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.DIAGNOSTICID, this.diagnosticId);
		returnJson.put(Key.SUBJECTNAME, this.subjectName);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
		returnJson.put(Key.FEEDBACK	, this.feedback);

		returnJson.put(Key.STUDENT, this.student.toJsonSimple());
		returnJson.put(Key.COURSE, this.course.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}

}
