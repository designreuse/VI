package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class BranchCourse {
	private long branchCourseId;
	
	private Branch branch;
	private Course course;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public BranchCourse(){}

	public BranchCourse(Branch branch, Course course){
		super();
		this.setBranch(branch);
		this.setCourse(course);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	/**
	 * @return the branchCourseId
	 */
	public long getBranchCourseId() {
		return branchCourseId;
	}

	/**
	 * @param branchCourseId the branchCourseId to set
	 */
	public void setBranchCourseId(long branchCourseId) {
		this.branchCourseId = branchCourseId;
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
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.BRANCHCOURSEID, this.branchCourseId);
		returnJson.put(Key.BRANCH, this.branch.toJson());
		returnJson.put(Key.COURSE, this.course.toJson());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
