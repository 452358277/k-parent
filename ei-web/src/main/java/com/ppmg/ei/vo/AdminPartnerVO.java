package com.ppmg.ei.vo;


import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;

/** 
 * 股东信息
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public class AdminPartnerVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 股东ID */
	private String partnerId;

	/** 基金管理人ID */
	private String adminId;

	/** 股东名称 */
	private String partnerName;

	/** 类别 */
	private long partnerType;
	private String partnerTypeName;

	/** 属性 */
	private long partnerPro;
	private String partnerProName;

	/** 认缴金额 */
	private Double planAmount;

	/** 实缴金额 */
	private Double paidAmount;

	/** 股比 */
	private Double shareRate;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public long getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(long partnerType) {
		this.partnerType = partnerType;
	}

	public String getPartnerTypeName() {
		return partnerTypeName;
	}

	public void setPartnerTypeName(String partnerTypeName) {
		this.partnerTypeName = partnerTypeName;
	}

	public long getPartnerPro() {
		return partnerPro;
	}

	public void setPartnerPro(long partnerPro) {
		this.partnerPro = partnerPro;
	}

	public String getPartnerProName() {
		return partnerProName;
	}

	public void setPartnerProName(String partnerProName) {
		this.partnerProName = partnerProName;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getShareRate() {
		return shareRate;
	}

	public void setShareRate(Double shareRate) {
		this.shareRate = shareRate;
	}
}