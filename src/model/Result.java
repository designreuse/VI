package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Result {
	private long resultId;
	private long courseLevel;
	private long bookletLevel;
	private String resultValue;
	private Date resultDate;
	private long pointAmount;

	private TeacherStudentCourse teacherStudentCourse;

	private long objStatus;
	private Date createDate;
	private String remark;

	public Result() {
	}

	public Result(long courseLevel, long bookletLevel, String resultValue,
			long pointAmount, TeacherStudentCourse teacherStudentCourse) {
		super();
		this.courseLevel = courseLevel;
		this.bookletLevel = bookletLevel;
		this.resultValue = resultValue;
		this.pointAmount = pointAmount;
		this.teacherStudentCourse = teacherStudentCourse;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public Result(long courseLevel, long bookletLevel, String resultValue,
			Date resultDate, long pointAmount,
			TeacherStudentCourse teacherStudentCourse) {
		super();
		this.courseLevel = courseLevel;
		this.bookletLevel = bookletLevel;
		this.resultValue = resultValue;
		this.resultDate = resultDate;
		this.pointAmount = pointAmount;
		this.teacherStudentCourse = teacherStudentCourse;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the resultId
	 */
	public long getResultId() {
		return resultId;
	}

	/**
	 * @param resultId
	 *            the resultId to set
	 */
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	/**
	 * @return the courseLevel
	 */
	public long getCourseLevel() {
		return courseLevel;
	}

	/**
	 * @param courseLevel
	 *            the courseLevel to set
	 */
	public void setCourseLevel(long courseLevel) {
		this.courseLevel = courseLevel;
	}

	/**
	 * @return the bookletLevel
	 */
	public long getBookletLevel() {
		return bookletLevel;
	}

	/**
	 * @param bookletLevel
	 *            the bookletLevel to set
	 */
	public void setBookletLevel(long bookletLevel) {
		this.bookletLevel = bookletLevel;
	}

	/**
	 * @return the resultValue
	 */
	public String getResultValue() {
		return resultValue;
	}

	/**
	 * @param resultValue
	 *            the resultValue to set
	 */
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	/**
	 * @return the resultDate
	 */
	public Date getResultDate() {
		return resultDate;
	}

	/**
	 * @param resultDate
	 *            the resultDate to set
	 */
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	/**
	 * @return the pointAmount
	 */
	public long getPointAmount() {
		return pointAmount;
	}

	/**
	 * @param pointAmount
	 *            the pointAmount to set
	 */
	public void setPointAmount(long pointAmount) {
		this.pointAmount = pointAmount;
	}

	/**
	 * @return the teacherStudentCourse
	 */
	public TeacherStudentCourse getTeacherStudentCourse() {
		return teacherStudentCourse;
	}

	/**
	 * @param teacherStudentCourse
	 *            the teacherStudentCourse to set
	 */
	public void setTeacherStudentCourse(
			TeacherStudentCourse teacherStudentCourse) {
		this.teacherStudentCourse = teacherStudentCourse;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus
	 *            the objStatus to set
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
	 * @param createDate
	 *            the createDate to set
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
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.RESULTID, this.resultId);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
//		returnJson.put(Key.RESULTDATE, Config.SDF.format(this.resultDate));
		returnJson.put(Key.POINTAMOUNT, this.pointAmount);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.RESULTID, this.resultId);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);
		returnJson.put(Key.RESULTVALUE, this.resultValue);
//		returnJson.put(Key.RESULTDATE, Config.SDF.format(this.resultDate));
		returnJson.put(Key.POINTAMOUNT, this.pointAmount);

		returnJson.put(Key.TEACHERSTUDENTCOURSE,
				this.teacherStudentCourse.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
