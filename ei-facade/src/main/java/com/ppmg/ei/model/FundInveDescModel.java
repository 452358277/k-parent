package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundInveDescModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 基金ID */
	private String fundId;

	/** 延长期管理费 */
	private Double mgtFeeRate;

	/** 管理费说明 */
	private String mgtFeeDesc;

	/** 投资期(年) */
	private String invePeriod;

	/** 回收期(年) */
	private String paybackPeriod;

	/** 投资期延长 */
	private String rollOver;

	/** 存续期说明 */
	private String durationDesc;

	/** 门槛收益率 */
	private Double hurdleRate;

	/** 超额分配比例-有限合伙人比例 */
	private Double performanceFee;

	/** 收益分配说明 */
	private String incomeDistDesc;

	/** 投资方向及目标 */
	private String inveStrategy;

	/** 投资标准 */
	private String inveStandard;

	/** 业绩报酬提取方式 */
	private String payMethod;

	/** 备注 */
	private String remark;

	/** 投资期起算日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveStartDate;

	/** 投资期截止日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveEndDate;

	/** 退出期延长 */
	private String paybackOver;

	/** 存续期 */
	private String durationPeriod;

	/** 管理费方式(码值1010：1按认缴，2按实缴) */
	private String mgtOrPaid;

	/** 超额分配比例-管理人比例 */
	private Double mcFee;
	/** 投资期管理费 */
	private String inveFeeRate;
	/** 退出期管理费 */
	private String paybackFeeRate;

	private String isRollOver;

	private String isPaybackOver;

	private String  investmentStrategy;

	private Double addPayBackOver;

	private String  isDurationOver;

	private String  durationOver;

	public String getIsDurationOver() {
		return isDurationOver;
	}

	public void setIsDurationOver(String isDurationOver) {
		this.isDurationOver = isDurationOver;
	}

	public String getDurationOver() {
		return durationOver;
	}

	public void setDurationOver(String durationOver) {
		this.durationOver = durationOver;
	}

	public Double getAddPayBackOver() {
		return addPayBackOver;
	}

	public void setAddPayBackOver(Double addPayBackOver) {
		this.addPayBackOver = addPayBackOver;
	}

	public String getInvestmentStrategy() {
		return investmentStrategy;
	}

	public void setInvestmentStrategy(String investmentStrategy) {
		this.investmentStrategy = investmentStrategy;
	}

	public String getIsRollOver() {
		return isRollOver;
	}

	public void setIsRollOver(String isRollOver) {
		this.isRollOver = isRollOver;
	}

	public String getIsPaybackOver() {
		return isPaybackOver;
	}

	public void setIsPaybackOver(String isPaybackOver) {
		this.isPaybackOver = isPaybackOver;
	}

	public String getInveFeeRate() {
		return inveFeeRate;
	}

	public void setInveFeeRate(String inveFeeRate) {
		this.inveFeeRate = inveFeeRate;
	}

	public String getPaybackFeeRate() {
		return paybackFeeRate;
	}

	public void setPaybackFeeRate(String paybackFeeRate) {
		this.paybackFeeRate = paybackFeeRate;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Double getMgtFeeRate() {
		return mgtFeeRate;
	}

	public void setMgtFeeRate(Double mgtFeeRate) {
		this.mgtFeeRate = mgtFeeRate;
	}

	public String getMgtFeeDesc() {
		return mgtFeeDesc;
	}

	public void setMgtFeeDesc(String mgtFeeDesc) {
		this.mgtFeeDesc = mgtFeeDesc;
	}

	public String getInvePeriod() {
		return invePeriod;
	}

	public void setInvePeriod(String invePeriod) {
		this.invePeriod = invePeriod;
	}

	public String getPaybackPeriod() {
		return paybackPeriod;
	}

	public void setPaybackPeriod(String paybackPeriod) {
		this.paybackPeriod = paybackPeriod;
	}

	public String getRollOver() {
		return rollOver;
	}

	public void setRollOver(String rollOver) {
		this.rollOver = rollOver;
	}

	public String getDurationDesc() {
		return durationDesc;
	}

	public void setDurationDesc(String durationDesc) {
		this.durationDesc = durationDesc;
	}

	public Double getHurdleRate() {
		return hurdleRate;
	}

	public void setHurdleRate(Double hurdleRate) {
		this.hurdleRate = hurdleRate;
	}

	public Double getPerformanceFee() {
		return performanceFee;
	}

	public void setPerformanceFee(Double performanceFee) {
		this.performanceFee = performanceFee;
	}

	public String getIncomeDistDesc() {
		return incomeDistDesc;
	}

	public void setIncomeDistDesc(String incomeDistDesc) {
		this.incomeDistDesc = incomeDistDesc;
	}

	public String getInveStrategy() {
		return inveStrategy;
	}

	public void setInveStrategy(String inveStrategy) {
		this.inveStrategy = inveStrategy;
	}

	public String getInveStandard() {
		return inveStandard;
	}

	public void setInveStandard(String inveStandard) {
		this.inveStandard = inveStandard;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInveStartDate() {
		return inveStartDate;
	}

	public void setInveStartDate(Date inveStartDate) {
		this.inveStartDate = inveStartDate;
	}

	public Date getInveEndDate() {
		return inveEndDate;
	}

	public void setInveEndDate(Date inveEndDate) {
		this.inveEndDate = inveEndDate;
	}

	public String getPaybackOver() {
		return paybackOver;
	}

	public void setPaybackOver(String paybackOver) {
		this.paybackOver = paybackOver;
	}

	public String getDurationPeriod() {
		return durationPeriod;
	}

	public void setDurationPeriod(String durationPeriod) {
		this.durationPeriod = durationPeriod;
	}

	public String getMgtOrPaid() {
		return mgtOrPaid;
	}

	public void setMgtOrPaid(String mgtOrPaid) {
		this.mgtOrPaid = mgtOrPaid;
	}

	public Double getMcFee() {
		return mcFee;
	}

	public void setMcFee(Double mcFee) {
		this.mcFee = mcFee;
	}

}