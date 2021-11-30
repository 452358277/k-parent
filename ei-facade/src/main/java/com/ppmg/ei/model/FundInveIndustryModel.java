package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundInveIndustryModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 行业名称 */
	private String industryName;

	/** 投资行业 */
	private String inveIndustry;

	/** 行业比重（%） */
	private Double industryRatio;

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

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getInveIndustry() {
		return inveIndustry;
	}

	public void setInveIndustry(String inveIndustry) {
		this.inveIndustry = inveIndustry;
	}

	public Double getIndustryRatio() {
		return industryRatio;
	}

	public void setIndustryRatio(Double industryRatio) {
		this.industryRatio = industryRatio;
	}

}