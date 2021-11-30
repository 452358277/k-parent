package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundEnteModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业年度财务情况ID */
	private String pkId;

	/** 企业名称 */
	private String enteName;

	/** 年度 */
	private String year;

	/** 职工总人数 */
	private Double staffNum;

	/** 科技研发人数 */
	private Double rdStaffNum;

	/** 总资产 */
	private Double totalAssets;

	/** 总负债 */
	private Double totalLiabilities;

	/** 所有者权益 */
	private Double ownerEquity;

	/** 总收 */
	private Double totalIncome;

	/** 利润总额 */
	private Double totalProfit;

	/** 净利润 */
	private Double netProfit;

	/** 研发费用 */
	private Double rdFee;

	/** 上缴费用 */
	private Double payFee;

	/** 其中上缴地方财政税费 */
	private Double localPayFee;

	/** 备注 */
	private String remark;

	/** 状态 */
	private String status;

	/** 企业ID */
	private String enteId;

	/** 年报附件 */
	private String reportFile;

	/** 是否投资前上年度财务信息 */
	private String isPreInvest;


	private String inveStr;

	public String getInveStr() {
		return inveStr;
	}

	public void setInveStr(String inveStr) {
		this.inveStr = inveStr;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getEnteName() {
		return enteName;
	}

	public void setEnteName(String enteName) {
		this.enteName = enteName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Double staffNum) {
		this.staffNum = staffNum;
	}

	public Double getRdStaffNum() {
		return rdStaffNum;
	}

	public void setRdStaffNum(Double rdStaffNum) {
		this.rdStaffNum = rdStaffNum;
	}

	public Double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(Double totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public Double getOwnerEquity() {
		return ownerEquity;
	}

	public void setOwnerEquity(Double ownerEquity) {
		this.ownerEquity = ownerEquity;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}

	public Double getRdFee() {
		return rdFee;
	}

	public void setRdFee(Double rdFee) {
		this.rdFee = rdFee;
	}

	public Double getPayFee() {
		return payFee;
	}

	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}

	public Double getLocalPayFee() {
		return localPayFee;
	}

	public void setLocalPayFee(Double localPayFee) {
		this.localPayFee = localPayFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}

	public String getReportFile() {
		return reportFile;
	}

	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}

	public String getIsPreInvest() {
		return isPreInvest;
	}

	public void setIsPreInvest(String isPreInvest) {
		this.isPreInvest = isPreInvest;
	}

}