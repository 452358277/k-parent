package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundValuationVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
//	private String pkId;

	/** 评估人 */
	private String assessByName;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 投资估值 */
	private Double inveValuation;

	/** 投资估值 */
	private String inveValuationDisplay;

	/** 投资估值币种 */
	private String ivCurrencyName;

	/** 估值时间点 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date valuDt;

	/** IRR(%) */
	private Double irr;

	/** 附件 */
	private String attaFile;

	/*public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}*/

	/*public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundSts() {
		return fundSts;
	}

	public void setFundSts(String fundSts) {
		this.fundSts = fundSts;
	}

	public String getAssessBy() {
		return assessBy;
	}

	public void setAssessBy(String assessBy) {
		this.assessBy = assessBy;
	}*/

	public String getInveValuationDisplay() {
		return inveValuationDisplay;
	}

	public void setInveValuationDisplay(String inveValuationDisplay) {
		this.inveValuationDisplay = inveValuationDisplay;
	}

	public Date getAssessDt() {
		return assessDt;
	}

	public void setAssessDt(Date assessDt) {
		this.assessDt = assessDt;
	}

	public Double getInveValuation() {
		return inveValuation;
	}

	public void setInveValuation(Double inveValuation) {
		this.inveValuation = inveValuation;
	}

	public String getIvCurrencyName() {
		return ivCurrencyName;
	}

	public void setIvCurrencyName(String ivCurrencyName) {
		this.ivCurrencyName = ivCurrencyName;
	}

	public Date getValuDt() {
		return valuDt;
	}

	public void setValuDt(Date valuDt) {
		this.valuDt = valuDt;
	}

	public Double getIrr() {
		return irr;
	}

	public void setIrr(Double irr) {
		this.irr = irr;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getAssessByName() {
		return assessByName;
	}

	public void setAssessByName(String assessByName) {
		this.assessByName = assessByName;
	}
}