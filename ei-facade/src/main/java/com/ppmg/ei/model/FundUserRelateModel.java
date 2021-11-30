package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundUserRelateModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private String userId;

	/** 基金表主键 */
	private String fundId;

	private String userRoles;

	private String accounts;

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

}