package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Announcement {
	private long announcementId;
	private String name;
	private String content;
	private String image;
	private Date startDate;
	private Date endDate;
	private long announcementType;
	
	private Branch branch;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Announcement(){}
	
	public Announcement(String name, String content, String image,
			Date startDate, Date endDate, Branch branch) {
		super();
		this.name = name;
		this.content = content;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.branch = branch;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());	
	}
	
	public Announcement(String name, String content, String image,
			Date startDate, Date endDate, long announcementType, Branch branch) {
		super();
		this.name = name;
		this.content = content;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.announcementType = announcementType;
		this.branch = branch;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());	
	}

	/**
	 * @return the announcementId
	 */
	public long getAnnouncementId() {
		return announcementId;
	}

	/**
	 * @param announcementId the announcementId to set
	 */
	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the announcementType
	 */
	public long getAnnouncementType() {
		return announcementType;
	}

	/**
	 * @param announcementType the announcementType to set
	 */
	public void setAnnouncementType(long announcementType) {
		this.announcementType = announcementType;
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

		returnJson.put(Key.ANNOUNCEMENTID, this.announcementId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTENT, this.content);
		returnJson.put(Key.IMAGE, this.image);
		returnJson.put(Key.STARTDATE, Config.SDF.format(this.startDate));
		returnJson.put(Key.ENDDATE, Config.SDF.format(this.endDate));
		returnJson.put(Key.ANNOUNCEMENTTYPE, this.announcementType);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
