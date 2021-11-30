package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EnteFinanceReportModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String stopId;

	/** 企业编号 */
	private String enterpriseId;

	/** 融资时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date financeDate;

	/** 融资金额(万元) */
	private Double financeAmt1;

	/** 基金本轮投资金额(万元) */
	private Double fundInvestAmt;

	/** 基金最新占比 */
	private Double fundRatio;

	/** 最新投后估值(万元) */
	private Double lastPostValue;

	/** 融资轮数(码值：213) */
	private String finaRounds;

	/** 融资次数(码值：214) */
	private String finaTimes;

	/** 最新注册资本(万元) */
	private Double projType;

	/** 融资方式(码值：1029) */
	private String stopType;

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Date getFinanceDate() {
		return financeDate;
	}

	public void setFinanceDate(Date financeDate) {
		this.financeDate = financeDate;
	}

	public Double getFinanceAmt1() {
		return financeAmt1;
	}

	public void setFinanceAmt1(Double financeAmt1) {
		this.financeAmt1 = financeAmt1;
	}

	public Double getFundInvestAmt() {
		return fundInvestAmt;
	}

	public void setFundInvestAmt(Double fundInvestAmt) {
		this.fundInvestAmt = fundInvestAmt;
	}

	public Double getFundRatio() {
		return fundRatio;
	}

	public void setFundRatio(Double fundRatio) {
		this.fundRatio = fundRatio;
	}

	public Double getLastPostValue() {
		return lastPostValue;
	}

	public void setLastPostValue(Double lastPostValue) {
		this.lastPostValue = lastPostValue;
	}

	public String getFinaRounds() {
		return finaRounds;
	}

	public void setFinaRounds(String finaRounds) {
		this.finaRounds = finaRounds;
	}

	public String getFinaTimes() {
		return finaTimes;
	}

	public void setFinaTimes(String finaTimes) {
		this.finaTimes = finaTimes;
	}

	public Double getProjType() {
		return projType;
	}

	public void setProjType(Double projType) {
		this.projType = projType;
	}

	public String getStopType() {
		return stopType;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Long rowVersion) {
		this.rowVersion = rowVersion;
	}

}