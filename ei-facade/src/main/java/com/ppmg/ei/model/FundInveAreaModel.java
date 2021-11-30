package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundInveAreaModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** null */
	private String fundId;

	/** null */
	private Double areaA;

	/** null */
	private Double areaB;

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

	public Double getAreaA() {
		return areaA;
	}

	public void setAreaA(Double areaA) {
		this.areaA = areaA;
	}

	public Double getAreaB() {
		return areaB;
	}

	public void setAreaB(Double areaB) {
		this.areaB = areaB;
	}

}