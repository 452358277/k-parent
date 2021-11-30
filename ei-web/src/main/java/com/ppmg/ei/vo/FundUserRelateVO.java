package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class FundUserRelateVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private String userId;

	/** 基金表主键 */
	private String fundId;

	private String accounts;

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
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