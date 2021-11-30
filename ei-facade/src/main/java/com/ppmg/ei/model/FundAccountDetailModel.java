package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundAccountDetailModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 基金id */
	private String fundId;

	/** 备注 */
	private String remark;

	/** null */
	private String fileDesc;

	/** 账户 */
	private String fundAccount;

	/** 分配金额 */
	private Double disAmount;


	private String disAmountStr;

	/** 分配时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date disData;

	/** 分配金财金额 */
	private Double disJcAmount;

	/** 账户余额 */
	private Double accountBalance;

	/** 基金分配金额 */
	private Double fundAmount;


	private String fundAmountStr;

	/** 附件 */
	private String disFile;

	private String status;

	public String getFundAmountStr() {
		return fundAmountStr;
	}

	public void setFundAmountStr(String fundAmountStr) {
		this.fundAmountStr = fundAmountStr;
	}

	private String disJcAmountStr;


	public String getDisJcAmountStr() {
		return disJcAmountStr;
	}

	public void setDisJcAmountStr(String disJcAmountStr) {
		this.disJcAmountStr = disJcAmountStr;
	}

	public String getDisAmountStr() {
		return disAmountStr;
	}

	public void setDisAmountStr(String disAmountStr) {
		this.disAmountStr = disAmountStr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public Double getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(Double disAmount) {
		this.disAmount = disAmount;
	}

	public Date getDisData() {
		return disData;
	}

	public void setDisData(Date disData) {
		this.disData = disData;
	}

	public Double getDisJcAmount() {
		return disJcAmount;
	}

	public void setDisJcAmount(Double disJcAmount) {
		this.disJcAmount = disJcAmount;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double getFundAmount() {
		return fundAmount;
	}

	public void setFundAmount(Double fundAmount) {
		this.fundAmount = fundAmount;
	}

	public String getDisFile() {
		return disFile;
	}

	public void setDisFile(String disFile) {
		this.disFile = disFile;
	}

}