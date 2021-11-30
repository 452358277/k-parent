package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundIrrVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 年度 */
	private String fundYear;

	/** 季度 */
	private String fundQuarter;

	/** IRR */
	private Double fundIrr;

	/** 累计投资成本 */
	private Double investAmount;

	/** 累计回收金额 */
	private Double backAmount;

	/** 在投项目估值总计 */
	private Double inveValuation;

	/** 估值日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date valuDt;

	/** DPI */
	private Double DPI;

	/** TVPI */
	private Double TVPI;

	/** PIC */
	private Double PIC;

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

	public String getFundYear() {
		return fundYear;
	}

	public void setFundYear(String fundYear) {
		this.fundYear = fundYear;
	}

	public String getFundQuarter() {
		return fundQuarter;
	}

	public void setFundQuarter(String fundQuarter) {
		this.fundQuarter = fundQuarter;
	}

	public Double getFundIrr() {
		return fundIrr;
	}

	public void setFundIrr(Double fundIrr) {
		this.fundIrr = fundIrr;
	}

	public Double getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}

	public Double getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(Double backAmount) {
		this.backAmount = backAmount;
	}

	public Double getInveValuation() {
		return inveValuation;
	}

	public void setInveValuation(Double inveValuation) {
		this.inveValuation = inveValuation;
	}

	public Date getValuDt() {
		return valuDt;
	}

	public void setValuDt(Date valuDt) {
		this.valuDt = valuDt;
	}

	public Double getDPI() {
		return DPI;
	}

	public void setDPI(Double DPI) {
		this.DPI = DPI;
	}

	public Double getTVPI() {
		return TVPI;
	}

	public void setTVPI(Double TVPI) {
		this.TVPI = TVPI;
	}

	public Double getPIC() {
		return PIC;
	}

	public void setPIC(Double PIC) {
		this.PIC = PIC;
	}
}