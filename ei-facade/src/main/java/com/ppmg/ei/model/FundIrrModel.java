package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundIrrModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 年度 */
	private String fundYear;

	/** 季度 */
	private String fundQuarter;

	/** IRR */
	private Double fundIrr;

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

	public String getFundYear() {
		return fundYear;
	}

	public void setFundYear(String fundYear) {
		this.fundYear = fundYear;
	}

	public String getFundQuarter() {
		return fundQuarter;
	}

	public void setFundQuarter(String fundQuarter) {
		this.fundQuarter = fundQuarter;
	}

	public Double getFundIrr() {
		return fundIrr;
	}

	public void setFundIrr(Double fundIrr) {
		this.fundIrr = fundIrr;
	}

}