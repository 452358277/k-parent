package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundFinanceModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 财务信息ID */
	private String financeId;

	/** 基金ID */
	private String fundId;

	/** 季度 */
	private String quarter;

	/** 本季度管理费 */
	private Double manageFee;

	/** 本季度资金托管费用 */
	private Double collocationFee;

	/** 本季度其他费用 */
	private Double otherFee;

	/** 本季度利息收入 */
	private Double interestIncome;

	/** 本季度投资收益 */
	private Double investIncome;

	/** 本季度其他收益 */
	private Double otherIncome;

	/** 本季度结余 */
	private Double surplus;

	/** 状态 */
	private String status;

	/** 年份 */
	private String year;

	/** 累计管理费 */
	private Double totalManageFee;

	/** 累计资金托管费用 */
	private Double totalCollocationFee;

	/** 累计其他费用 */
	private Double totalOtherFee;

	/** 累计利息收入 */
	private Double totalInterestIncome;

	/** 累计投资收益 */
	private Double totalInvestIncome;

	/** 累计其他收益 */
	private Double totalOtherIncome;

	/** 累计结余 */
	private Double totalSurplus;


	private String financeFile;

	public String getFinanceFile() {
		return financeFile;
	}

	public void setFinanceFile(String financeFile) {
		this.financeFile = financeFile;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Double getManageFee() {
		return manageFee;
	}

	public void setManageFee(Double manageFee) {
		this.manageFee = manageFee;
	}

	public Double getCollocationFee() {
		return collocationFee;
	}

	public void setCollocationFee(Double collocationFee) {
		this.collocationFee = collocationFee;
	}

	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}

	public Double getInterestIncome() {
		return interestIncome;
	}

	public void setInterestIncome(Double interestIncome) {
		this.interestIncome = interestIncome;
	}

	public Double getInvestIncome() {
		return investIncome;
	}

	public void setInvestIncome(Double investIncome) {
		this.investIncome = investIncome;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getSurplus() {
		return surplus;
	}

	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getTotalManageFee() {
		return totalManageFee;
	}

	public void setTotalManageFee(Double totalManageFee) {
		this.totalManageFee = totalManageFee;
	}

	public Double getTotalCollocationFee() {
		return totalCollocationFee;
	}

	public void setTotalCollocationFee(Double totalCollocationFee) {
		this.totalCollocationFee = totalCollocationFee;
	}

	public Double getTotalOtherFee() {
		return totalOtherFee;
	}

	public void setTotalOtherFee(Double totalOtherFee) {
		this.totalOtherFee = totalOtherFee;
	}

	public Double getTotalInterestIncome() {
		return totalInterestIncome;
	}

	public void setTotalInterestIncome(Double totalInterestIncome) {
		this.totalInterestIncome = totalInterestIncome;
	}

	public Double getTotalInvestIncome() {
		return totalInvestIncome;
	}

	public void setTotalInvestIncome(Double totalInvestIncome) {
		this.totalInvestIncome = totalInvestIncome;
	}

	public Double getTotalOtherIncome() {
		return totalOtherIncome;
	}

	public void setTotalOtherIncome(Double totalOtherIncome) {
		this.totalOtherIncome = totalOtherIncome;
	}

	public Double getTotalSurplus() {
		return totalSurplus;
	}

	public void setTotalSurplus(Double totalSurplus) {
		this.totalSurplus = totalSurplus;
	}

}