package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundShareItemModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FundShareItemVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String itemId;

	/** 股比信息ID */
	private String pkId;

	/** 第几期 */
	private Long closeRound;

	/** 第几期 */
	private String closeRoundName;

	/** 出资人名称 */
	private String investorName;

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

	/** 认缴总额 */
	private Double inveAmount;

	/** 本期缴款比例 */
	private String duesPercent;

	/** 未缴款额 */
	private Double notPaidAmount;

	/**验资报告**/
	private String descFile;

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	private List<FundShareItemModel> fundShareItemList;

	public String getCloseRoundName() {
		return closeRoundName;
	}

	public void setCloseRoundName(String closeRoundName) {
		this.closeRoundName = closeRoundName;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
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

	public List<FundShareItemModel> getFundShareItemList() {
		return fundShareItemList;
	}

	public void setFundShareItemList(List<FundShareItemModel> fundShareItemList) {
		this.fundShareItemList = fundShareItemList;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
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