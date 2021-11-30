package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundInvePhaseModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 早期（%） */
	private Double phaseA;

	/** 成长期（%） */
	private Double phaseB;

	/** 成熟期（%） */
	private Double phaseC;

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

	public Double getPhaseA() {
		return phaseA;
	}

	public void setPhaseA(Double phaseA) {
		this.phaseA = phaseA;
	}

	public Double getPhaseB() {
		return phaseB;
	}

	public void setPhaseB(Double phaseB) {
		this.phaseB = phaseB;
	}

	public Double getPhaseC() {
		return phaseC;
	}

	public void setPhaseC(Double phaseC) {
		this.phaseC = phaseC;
	}

}