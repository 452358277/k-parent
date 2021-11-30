package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundInvcommemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 成员ID */
	private String memId;

	/** 基金ID */
	private String fundId;

	/** 姓名 */
	private String name;

	/** 手机号 */
	private String phoneNo;

	/** 身份（0：成员，1：主席） */
	private String duty;

	/** 是否元禾人员 */
	private String isYhkg;

	/** 状态 */
	private String status;

	/** 委员会ID */
	private String comId;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getIsYhkg() {
		return isYhkg;
	}

	public void setIsYhkg(String isYhkg) {
		this.isYhkg = isYhkg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

}