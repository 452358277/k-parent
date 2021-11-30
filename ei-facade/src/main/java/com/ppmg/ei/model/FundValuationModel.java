package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FundValuationModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金 */
	private String fundId;

	/** 基金状态 */
	private String fundSts;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 投资估值 */
	private Double inveValuation;

	/** 投资估值币种 */
	private String ivCurrency;

	/** 估值方式 */
	private String valuType;

	/** 估值依据说明 */
	private String valuDesc;

	/** 估值时间点 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date valuDt;

	/** IRR(%) */
	private Double irr;

	/** 附件 */
	private String attaFile;

	/** 关联任务号 */
	private String taskId;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	/** 评估人 */
	private String assessByName;

	/** 投资估值 */
	private String inveValuationDisplay;

	public String getAssessByName() {
		return assessByName;
	}

	public void setAssessByName(String assessByName) {
		this.assessByName = assessByName;
	}

	public String getInveValuationDisplay() {
		return inveValuationDisplay;
	}

	public void setInveValuationDisplay(String inveValuationDisplay) {
		this.inveValuationDisplay = inveValuationDisplay;
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

	public String getIvCurrency() {
		return ivCurrency;
	}

	public void setIvCurrency(String ivCurrency) {
		this.ivCurrency = ivCurrency;
	}

	public String getValuType() {
		return valuType;
	}

	public void setValuType(String valuType) {
		this.valuType = valuType;
	}

	public String getValuDesc() {
		return valuDesc;
	}

	public void setValuDesc(String valuDesc) {
		this.valuDesc = valuDesc;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}