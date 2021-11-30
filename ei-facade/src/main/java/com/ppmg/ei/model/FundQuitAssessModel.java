package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FundQuitAssessModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 基金 */
	private String fundId;

	/** 基金规模 */
	private Double raiseAmount;

	/** 币种 */
	private String raiseCurrency;

	/** 已投项目数 */
	private Long inveProjNumb;

	/** 累计投资金额 */
	private Double sumInveAmount;

	/** 累计回收金额 */
	private Double sumReturnAmount;

	/** 整体IRR(%) */
	private Double allIrr;

	/** 投资IRR(%) */
	private Double inveIrr;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 评估总结 */
	private String assessSummary;

	/** 评估附件 */
	private String assessFile;

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

	public String getRaiseCurrency() {
		return raiseCurrency;
	}

	public void setRaiseCurrency(String raiseCurrency) {
		this.raiseCurrency = raiseCurrency;
	}

	public Long getInveProjNumb() {
		return inveProjNumb;
	}

	public void setInveProjNumb(Long inveProjNumb) {
		this.inveProjNumb = inveProjNumb;
	}

	public Double getSumInveAmount() {
		return sumInveAmount;
	}

	public void setSumInveAmount(Double sumInveAmount) {
		this.sumInveAmount = sumInveAmount;
	}

	public Double getSumReturnAmount() {
		return sumReturnAmount;
	}

	public void setSumReturnAmount(Double sumReturnAmount) {
		this.sumReturnAmount = sumReturnAmount;
	}

	public Double getAllIrr() {
		return allIrr;
	}

	public void setAllIrr(Double allIrr) {
		this.allIrr = allIrr;
	}

	public Double getInveIrr() {
		return inveIrr;
	}

	public void setInveIrr(Double inveIrr) {
		this.inveIrr = inveIrr;
	}

	public String getAssessBy() {
		return assessBy;
	}

	public void setAssessBy(String assessBy) {
		this.assessBy = assessBy;
	}

	public Date getAssessDt() {
		return assessDt;
	}

	public void setAssessDt(Date assessDt) {
		this.assessDt = assessDt;
	}

	public String getAssessSummary() {
		return assessSummary;
	}

	public void setAssessSummary(String assessSummary) {
		this.assessSummary = assessSummary;
	}

	public String getAssessFile() {
		return assessFile;
	}

	public void setAssessFile(String assessFile) {
		this.assessFile = assessFile;
	}

}