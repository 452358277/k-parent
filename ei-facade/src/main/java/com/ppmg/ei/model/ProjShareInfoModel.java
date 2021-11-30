package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class ProjShareInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 关联股权结构信息 */
	private String osId;

	/** 投资人ID */
	private String investorId;

	/** 投资人 */
	private String investorName;

	/** 股比（%） */
	private Double shareRate;

	/** 投资金额 */
	private Double inveAmount;

	/** 金额币种 */
	private String inveCurrency;

	/** 状态（0：正常，1：删除） */
	private String status;


	private String investmentAttributes;

	/** 股东性质 */
	private String investorPro;

	/** 承诺出资额 */
	private Double planAmount;
	/** 占总认缴比例 */
	private Double totalRate;
	/** 累计出资总额 */
	private Double paidAmount;
	/** 出资进度 */
	private Double investRate;

	public String getInvestorPro() {
		return investorPro;
	}

	public void setInvestorPro(String investorPro) {
		this.investorPro = investorPro;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public Double getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(Double totalRate) {
		this.totalRate = totalRate;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getInvestRate() {
		return investRate;
	}

	public void setInvestRate(Double investRate) {
		this.investRate = investRate;
	}

	public String getInvestmentAttributes() {
		return investmentAttributes;
	}

	public void setInvestmentAttributes(String investmentAttributes) {
		this.investmentAttributes = investmentAttributes;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getOsId() {
		return osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public Double getShareRate() {
		return shareRate;
	}

	public void setShareRate(Double shareRate) {
		this.shareRate = shareRate;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
	}

	public String getInveCurrency() {
		return inveCurrency;
	}

	public void setInveCurrency(String inveCurrency) {
		this.inveCurrency = inveCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}