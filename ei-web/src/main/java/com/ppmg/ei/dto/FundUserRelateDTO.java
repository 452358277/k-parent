package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;

public class FundUserRelateDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private String userId;

	/** 基金表主键 */
	private String fundId;

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