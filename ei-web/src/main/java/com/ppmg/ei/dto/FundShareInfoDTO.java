package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;

public class FundShareInfoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金编号 */
	private String fundId;

	/** 投资人ID */
	private String investorId;

	/** 投资角色(1:LP,2:GP,3:GM) */
	private String inveRole;

	/** 认缴规模 */
	private Double inveAmount;

	/** 认缴金额币种 */
	private String inveCurrency;

	/** 累计缴款额 */
	private Double totalFinancial;

	/** 累计出资币种 */
	private String totalFinCurrency;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 企业ID */
	private String enteId;

	/** 出资来源（码值1000：1自有资金、2理财通道、3借贷资金） */
	private String capitalSource;

	/** 出资人性质（码值1001：1政府部门、2国有企业、3混合制企业、4民营企业、5个人） */
	private String inveType;

	/** 实缴规模 */
	private Double raiseAmount;

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

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInveRole() {
		return inveRole;
	}

	public void setInveRole(String inveRole) {
		this.inveRole = inveRole;
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

	public Double getTotalFinancial() {
		return totalFinancial;
	}

	public void setTotalFinancial(Double totalFinancial) {
		this.totalFinancial = totalFinancial;
	}

	public String getTotalFinCurrency() {
		return totalFinCurrency;
	}

	public void setTotalFinCurrency(String totalFinCurrency) {
		this.totalFinCurrency = totalFinCurrency;
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

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

}