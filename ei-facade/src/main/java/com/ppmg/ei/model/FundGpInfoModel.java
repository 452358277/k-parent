package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundGpInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** GP编号 */
	private String gpId;

	/** 基金表主键 */
	private String fundId;

	/** 出资人名称 */
	private String gpName;

	/** 实缴规模 */
	private Double raiseAmount;

	/** 认缴规模 */
	private Double currentAmount;

	/** 出资来源 */
	private String capitalSource;

	/** 出资人性质 */
	private String gpProperty;

	/** 是否主要出资人 */
	private String isMain;

	/** 出资人类型(1普通合伙人，2出资人) */
	private String gpType;

	public String getGpId() {
		return gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public String getGpProperty() {
		return gpProperty;
	}

	public void setGpProperty(String gpProperty) {
		this.gpProperty = gpProperty;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getGpType() {
		return gpType;
	}

	public void setGpType(String gpType) {
		this.gpType = gpType;
	}

}