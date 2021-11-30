package com.ppmg.ei.dto;


import com.founder.ssm.core.dto.BaseDTO;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public class AdminPartnerDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 股东ID */
	private String partnerId;

	/** 基金管理人ID */
	private String adminId;

	/** 股东名称 */
	private String partnerName;

	/** 类别 */
	private String partnerType;

	/** 属性 */
	private String partnerPro;

	/** 认缴金额 */
	private Double planAmount;

	/** 实缴金额 */
	private Double paidAmount;

	/** 股比 */
	private Double shareRate;

	/**
	 * 用于删除
	 */
	private String deleteId;

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

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	public String getPartnerPro() {
		return partnerPro;
	}

	public void setPartnerPro(String partnerPro) {
		this.partnerPro = partnerPro;
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

	public String getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
}