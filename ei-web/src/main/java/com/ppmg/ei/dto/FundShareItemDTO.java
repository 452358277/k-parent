package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundShareItemDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String itemId;

	/** 股比信息ID */
	private String pkId;

	/** 第几期 */
	private Long closeRound;

	/** 状态 */
	private String status;

	/** 认缴金额币种 */
	private String inveCurrency;

	/** 本期应缴额 */
	private Double duesAmount;

	/** 本期实缴额 */
	private Double paymentAmount;

	/** 实到日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date arriveDate;

	/** 累计缴款额 */
	private Double totalFinancial;

	private Double inveAmount;

	private String descFile;

	private String fundId;

	private Double notPaidAmount;

	public Double getNotPaidAmount() {
		return notPaidAmount;
	}

	public void setNotPaidAmount(Double notPaidAmount) {
		this.notPaidAmount = notPaidAmount;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public Long getCloseRound() {
		return closeRound;
	}

	public void setCloseRound(Long closeRound) {
		this.closeRound = closeRound;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInveCurrency() {
		return inveCurrency;
	}

	public void setInveCurrency(String inveCurrency) {
		this.inveCurrency = inveCurrency;
	}

	public Double getDuesAmount() {
		return duesAmount;
	}

	public void setDuesAmount(Double duesAmount) {
		this.duesAmount = duesAmount;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Double getTotalFinancial() {
		return totalFinancial;
	}

	public void setTotalFinancial(Double totalFinancial) {
		this.totalFinancial = totalFinancial;
	}

}