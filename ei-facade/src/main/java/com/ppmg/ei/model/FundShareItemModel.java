package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundShareItemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String itemId;

	/** 股比信息ID */
	private String pkId;

	/** 认缴时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveDt;

	/** 认缴总额 */
	private Double inveAmount;

	/** 第几期 */
	private Long closeRound;

	/** 状态（0：正常，1：删除） */
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

	/** 基金ID */
	private String fundId;

	/** 本期缴款比例 */
	private String duesPercent;

	/** 未缴款额 */
	private Double notPaidAmount;

	/** 出资人名称 */
	private String investorName;

	/**验资报告**/
	private String descFile;

	private String remark;

	private String shareFile;

	private  Integer totalCloseRound;

	private String processInstId;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShareFile() {
		return shareFile;
	}

	public void setShareFile(String shareFile) {
		this.shareFile = shareFile;
	}

	public Integer getTotalCloseRound() {
		return totalCloseRound;
	}

	public void setTotalCloseRound(Integer totalCloseRound) {
		this.totalCloseRound = totalCloseRound;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getDuesPercent() {
		return duesPercent;
	}

	public void setDuesPercent(String duesPercent) {
		this.duesPercent = duesPercent;
	}

	public Double getNotPaidAmount() {
		return notPaidAmount;
	}

	public void setNotPaidAmount(Double notPaidAmount) {
		this.notPaidAmount = notPaidAmount;
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

	public Date getInveDt() {
		return inveDt;
	}

	public void setInveDt(Date inveDt) {
		this.inveDt = inveDt;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
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