package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundInvestInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 *//*
	private String fundId;

	*//** 基金名称 *//*
	private String fundName;*/

	/** 累计决策项目个数 */
	private String totalInvestCount;

	/** 累计投资金额	 */
	private String totalInvestAmount;

	/** 今年投资项目个数 */
	private String thisYearInvestCount;

	/** 今年投资金额 */
	private String thisYearInvestAmount;

	/** 在投项目数 */
	private String investingCount;

	/*public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}*/

	public String getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(String totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	public String getTotalInvestAmount() {
		return totalInvestAmount;
	}

	public void setTotalInvestAmount(String totalInvestAmount) {
		this.totalInvestAmount = totalInvestAmount;
	}

	public String getThisYearInvestCount() {
		return thisYearInvestCount;
	}

	public void setThisYearInvestCount(String thisYearInvestCount) {
		this.thisYearInvestCount = thisYearInvestCount;
	}

	public String getThisYearInvestAmount() {
		return thisYearInvestAmount;
	}

	public void setThisYearInvestAmount(String thisYearInvestAmount) {
		this.thisYearInvestAmount = thisYearInvestAmount;
	}

	public String getInvestingCount() {
		return investingCount;
	}

	public void setInvestingCount(String investingCount) {
		this.investingCount = investingCount;
	}
}