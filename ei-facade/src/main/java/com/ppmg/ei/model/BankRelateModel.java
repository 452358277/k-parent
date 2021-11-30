package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class BankRelateModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 基金表主键 */
	private String fundId;

	/** 银行编号 */
	private String bankId;

	/** 银行账户 */
	private String accountName;

	/** 银行帐号 */
	private String accounts;

	/** 资金额度 */
	private Double capitalQuota;

	/** 实到金额 */
	private Double arrivalAmount;

	/** 状态 */
	private String status;

	private String accountType;

	private String oldAccounts;

	public String getOldAccounts() {
		return oldAccounts;
	}

	public void setOldAccounts(String oldAccounts) {
		this.oldAccounts = oldAccounts;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public Double getCapitalQuota() {
		return capitalQuota;
	}

	public void setCapitalQuota(Double capitalQuota) {
		this.capitalQuota = capitalQuota;
	}

	public Double getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}