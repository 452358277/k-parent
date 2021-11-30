package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class QuotaConfDetailVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String detailId;

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

	/** 指标名称 */
	private String quotaName;

	/** 指标单位 */
	private String quotaUnit;

	/** 完成比例 */
	private String newRate;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
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

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getQuotaUnit() {
		return quotaUnit;
	}

	public void setQuotaUnit(String quotaUnit) {
		this.quotaUnit = quotaUnit;
	}

	public String getNewRate() {
		return newRate;
	}

	public void setNewRate(String newRate) {
		this.newRate = newRate;
	}
}