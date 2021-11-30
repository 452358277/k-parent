package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class QuotaConfDetailModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String detailId;

	/** 配置表主表ID */
	private String confId;

	/** 指标ID */
	private String quotaId;

	/** 计划值 */
	private Double planVal;

	/** 一季度考核结果 */
	private Double resultQ1;

	/** 二季度考核结果 */
	private Double resultQ2;

	/** 三季度考核结果 */
	private Double resultQ3;

	/** 四季度考核结果 */
	private Double resultQ4;

	/** 排序 */
	private String orderNum;

	/** 状态（0:正常 9:删除） */
	private String status;

	/** 完成比例 */
	private String newRate;

	private QuotaInfoModel quotaInfoModel;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getConfId() {
		return confId;
	}

	public void setConfId(String confId) {
		this.confId = confId;
	}

	public String getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	public Double getPlanVal() {
		return planVal;
	}

	public void setPlanVal(Double planVal) {
		this.planVal = planVal;
	}

	public Double getResultQ1() {
		return resultQ1;
	}

	public void setResultQ1(Double resultQ1) {
		this.resultQ1 = resultQ1;
	}

	public Double getResultQ2() {
		return resultQ2;
	}

	public void setResultQ2(Double resultQ2) {
		this.resultQ2 = resultQ2;
	}

	public Double getResultQ3() {
		return resultQ3;
	}

	public void setResultQ3(Double resultQ3) {
		this.resultQ3 = resultQ3;
	}

	public Double getResultQ4() {
		return resultQ4;
	}

	public void setResultQ4(Double resultQ4) {
		this.resultQ4 = resultQ4;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public QuotaInfoModel getQuotaInfoModel() {
		return quotaInfoModel;
	}

	public void setQuotaInfoModel(QuotaInfoModel quotaInfoModel) {
		this.quotaInfoModel = quotaInfoModel;
	}

	public String getNewRate() {
		return newRate;
	}

	public void setNewRate(String newRate) {
		this.newRate = newRate;
	}
}