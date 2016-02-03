package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class GiftInventory {
	private long giftInventoryId;
	private String giftName;
	private String giftImage;
	private String description;
	private long stockQuantity;
	private String giftTier;
	private double giftPoints;
	
	private Branch branch;
	private Set<GiftTransaction> giftTransactions;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public GiftInventory(){}
	
	public GiftInventory(String giftName, String giftImage, String description, 
			long stockQuantity, String giftTier, double giftPoints, Branch branch) {
		super();
		this.giftName = giftName;
		this.giftImage = giftImage;
		this.description = description;
		this.stockQuantity = stockQuantity;
		this.giftTier = giftTier;
		this.giftPoints = giftPoints;
		this.branch = branch;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the giftInventoryId
	 */
	public long getGiftInventoryId() {
		return giftInventoryId;
	}

	/**
	 * @param giftInventoryId the giftInventoryId to set
	 */
	public void setGiftInventoryId(long giftInventoryId) {
		this.giftInventoryId = giftInventoryId;
	}

	/**
	 * @return the giftName
	 */
	public String getGiftName() {
		return giftName;
	}

	/**
	 * @param giftName the giftName to set
	 */
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	/**
	 * @return the giftImage
	 */
	public String getGiftImage() {
		return giftImage;
	}

	/**
	 * @param giftImage the giftImage to set
	 */
	public void setGiftImage(String giftImage) {
		this.giftImage = giftImage;
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
	 * @return the stockQuantity
	 */
	public long getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity the stockQuantity to set
	 */
	public void setStockQuantity(long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return the giftTier
	 */
	public String getGiftTier() {
		return giftTier;
	}

	/**
	 * @param giftTier the giftTier to set
	 */
	public void setGiftTier(String giftTier) {
		this.giftTier = giftTier;
	}

	/**
	 * @return the giftPoints
	 */
	public double getGiftPoints() {
		return giftPoints;
	}

	/**
	 * @param giftPoints the giftPoints to set
	 */
	public void setGiftPoints(double giftPoints) {
		this.giftPoints = giftPoints;
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
	 * @return the giftTransactions
	 */
	public Set<GiftTransaction> getGiftTransactions() {
		return giftTransactions;
	}

	/**
	 * @param giftTransactions the giftTransactions to set
	 */
	public void setGiftTransactions(Set<GiftTransaction> giftTransactions) {
		this.giftTransactions = giftTransactions;
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

		returnJson.put(Key.GIFTINVENTORYID, this.giftInventoryId);
		returnJson.put(Key.GIFTNAME, this.giftName);
		returnJson.put(Key.GIFTIMAGE, this.giftImage);
		returnJson.put(Key.DESCRIPTION, this.description);
		returnJson.put(Key.STOCKQUANTITY, this.stockQuantity);
		returnJson.put(Key.GIFTTIER, this.giftTier);
		returnJson.put(Key.GIFTPOINTS, this.giftPoints);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
}
