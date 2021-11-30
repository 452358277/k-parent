package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EnteLiabilitiesInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 企业编号 */
	private String enterpriseId;

	/** 货币类型 */
	private String currencyType;

	/** 借款总额 */
	private Double borrowAmount;

	/** 借款方 */
	private String borrower;

	/** 借款期限(月) */
	private Long borrowTerm;

	/** 年利率（%） */
	private Double annualRate;

	/** 担保方式 */
	private String guarantyStyle;

	/** 借款时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date borrowStartDt;

	/** 到期时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date borrowEndDt;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 企业外网信息确认 */
	private String confirm;

	/** null */
	private String projGuid;

	/** null */
	private String borrowTermBak;

	/** null */
	private String annualRateBak;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Double getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(Double borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public Long getBorrowTerm() {
		return borrowTerm;
	}

	public void setBorrowTerm(Long borrowTerm) {
		this.borrowTerm = borrowTerm;
	}

	public Double getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(Double annualRate) {
		this.annualRate = annualRate;
	}

	public String getGuarantyStyle() {
		return guarantyStyle;
	}

	public void setGuarantyStyle(String guarantyStyle) {
		this.guarantyStyle = guarantyStyle;
	}

	public Date getBorrowStartDt() {
		return borrowStartDt;
	}

	public void setBorrowStartDt(Date borrowStartDt) {
		this.borrowStartDt = borrowStartDt;
	}

	public Date getBorrowEndDt() {
		return borrowEndDt;
	}

	public void setBorrowEndDt(Date borrowEndDt) {
		this.borrowEndDt = borrowEndDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getBorrowTermBak() {
		return borrowTermBak;
	}

	public void setBorrowTermBak(String borrowTermBak) {
		this.borrowTermBak = borrowTermBak;
	}

	public String getAnnualRateBak() {
		return annualRateBak;
	}

	public void setAnnualRateBak(String annualRateBak) {
		this.annualRateBak = annualRateBak;
	}

}