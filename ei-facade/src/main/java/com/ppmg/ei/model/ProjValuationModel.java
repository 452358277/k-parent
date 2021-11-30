package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjValuationModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 投后主键（投后的HANDLE_ID） */
	private String iaId;

	/** 投资项目投资评估外键 */
	private String praId;

	/** 评估对象，被投对象（企业） */
	private String objectId;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 当前公司总估值 */
	private Double totalValue;

	/** 总估值币种 */
	private String totalCurr;

	/** 评估对象当前阶段 */
	private String currPhase;

	/** 当前所占股比（%） */
	private Double holdShare;

	/** 估值方式 */
	private String valuType;

	/** 投资估值（持有股份部分估值） */
	private Double inveValuation;

	/** 投资估值币种 */
	private String ivCurrency;

	/** 已实现投资收益 */
	private Double realizedReturn;

	/** 已实现投资收益币种 */
	private String realizedCurr;

	/** 未实现投资收益 */
	private Double unrealizedReturn;

	/** 未实现投资收益币种 */
	private String unrealizedCurr;

	/** 估值依据说明 */
	private String valuDesc;

	/** 估值时间点 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date valuDt;

	/** IRR(%) */
	private Double irr;

	/** 评估报告 */
	private String attaFile;

	/** 关联任务号 */
	private String taskId;

	/** 评估类型（关键字） */
	private String assessType;

	/** 评估总结 */
	private String assessSummary;

	/** 备注 */
	private String remark;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	/** 投资回报倍数 */
	private Double returnRatio;

	/** null */
	private String rowId;

	/** 整体估值 */
	private Double wholeValue;

	/** 投后估值 */
	private Double entePostValue;

	/** 投资金额投后估值*/
	private Double inveNewValue;




	public Double getEntePostValue() {
		return entePostValue;
	}

	public void setEntePostValue(Double entePostValue) {
		this.entePostValue = entePostValue;
	}

	public Double getInveNewValue() {
		return inveNewValue;
	}

	public void setInveNewValue(Double inveNewValue) {
		this.inveNewValue = inveNewValue;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getIaId() {
		return iaId;
	}

	public void setIaId(String iaId) {
		this.iaId = iaId;
	}

	public String getPraId() {
		return praId;
	}

	public void setPraId(String praId) {
		this.praId = praId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getTotalCurr() {
		return totalCurr;
	}

	public void setTotalCurr(String totalCurr) {
		this.totalCurr = totalCurr;
	}

	public String getCurrPhase() {
		return currPhase;
	}

	public void setCurrPhase(String currPhase) {
		this.currPhase = currPhase;
	}

	public Double getHoldShare() {
		return holdShare;
	}

	public void setHoldShare(Double holdShare) {
		this.holdShare = holdShare;
	}

	public String getValuType() {
		return valuType;
	}

	public void setValuType(String valuType) {
		this.valuType = valuType;
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

	public Double getRealizedReturn() {
		return realizedReturn;
	}

	public void setRealizedReturn(Double realizedReturn) {
		this.realizedReturn = realizedReturn;
	}

	public String getRealizedCurr() {
		return realizedCurr;
	}

	public void setRealizedCurr(String realizedCurr) {
		this.realizedCurr = realizedCurr;
	}

	public Double getUnrealizedReturn() {
		return unrealizedReturn;
	}

	public void setUnrealizedReturn(Double unrealizedReturn) {
		this.unrealizedReturn = unrealizedReturn;
	}

	public String getUnrealizedCurr() {
		return unrealizedCurr;
	}

	public void setUnrealizedCurr(String unrealizedCurr) {
		this.unrealizedCurr = unrealizedCurr;
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

	public String getAssessType() {
		return assessType;
	}

	public void setAssessType(String assessType) {
		this.assessType = assessType;
	}

	public String getAssessSummary() {
		return assessSummary;
	}

	public void setAssessSummary(String assessSummary) {
		this.assessSummary = assessSummary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getReturnRatio() {
		return returnRatio;
	}

	public void setReturnRatio(Double returnRatio) {
		this.returnRatio = returnRatio;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public Double getWholeValue() {
		return wholeValue;
	}

	public void setWholeValue(Double wholeValue) {
		this.wholeValue = wholeValue;
	}

}