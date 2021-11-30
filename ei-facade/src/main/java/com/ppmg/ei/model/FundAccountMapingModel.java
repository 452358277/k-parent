package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundAccountMapingModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金主键 */
	private String fundId;

	/** 用户名 */
	private String userId;

	/** 基金管理人ID/出资人代表ID */
	private String adminId;

	/** 是否出资人代表，是则为rep否则为空 */
	private String isRep;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getIsRep() {
		return isRep;
	}

	public void setIsRep(String isRep) {
		this.isRep = isRep;
	}

}