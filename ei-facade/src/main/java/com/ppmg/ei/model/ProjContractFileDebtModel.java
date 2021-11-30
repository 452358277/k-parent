package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class ProjContractFileDebtModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 合同主键 */
	private String fileId;

	/** 申请额度 */
	private String applyLimit;

	/** 期限 */
	private String timeLimit;

	/** 利率 */
	private Double rate;

	/** 保证方式 */
	private String guaranteeType;

	/** 还款方式 */
	private String repaymentType;

	/** 贷款用途 */
	private String loanDesc;

	/** 状态 */
	private String status;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(String applyLimit) {
		this.applyLimit = applyLimit;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getLoanDesc() {
		return loanDesc;
	}

	public void setLoanDesc(String loanDesc) {
		this.loanDesc = loanDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}