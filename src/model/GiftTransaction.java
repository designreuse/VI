package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;

public class GiftTransaction {
	private long giftTransactionId;
	private long giftQuantity;
	private double studentPoints;
	
	private GiftInventory giftInventory;
	private Student student;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public GiftTransaction(){}
	
	public GiftTransaction(long giftQuantity, double studentPoints,
			GiftInventory giftInventory, Student student) {
		super();
		this.giftQuantity = giftQuantity;
		this.studentPoints = studentPoints;
		this.giftInventory = giftInventory;
		this.student = student;
	}

	/**
	 * @return the giftTransactionId
	 */
	public long getGiftTransactionId() {
		return giftTransactionId;
	}

	/**
	 * @param giftTransactionId the giftTransactionId to set
	 */
	public void setGiftTransactionId(long giftTransactionId) {
		this.giftTransactionId = giftTransactionId;
	}

	/**
	 * @return the giftQuantity
	 */
	public long getGiftQuantity() {
		return giftQuantity;
	}

	/**
	 * @param giftQuantity the giftQuantity to set
	 */
	public void setGiftQuantity(long giftQuantity) {
		this.giftQuantity = giftQuantity;
	}

	/**
	 * @return the studentPoints
	 */
	public double getStudentPoints() {
		return studentPoints;
	}

	/**
	 * @param studentPoints the studentPoints to set
	 */
	public void setStudentPoints(double studentPoints) {
		this.studentPoints = studentPoints;
	}

	/**
	 * @return the giftInventory
	 */
	public GiftInventory getGiftInventory() {
		return giftInventory;
	}

	/**
	 * @param giftInventory the giftInventory to set
	 */
	public void setGiftInventory(GiftInventory giftInventory) {
		this.giftInventory = giftInventory;
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
		returnJson.put(Key.GIFTTRANSACTIONID, this.giftTransactionId);
		returnJson.put(Key.GIFTQUANTITY, this.giftQuantity);
		returnJson.put(Key.STUDENTPOINTS, this.studentPoints);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
