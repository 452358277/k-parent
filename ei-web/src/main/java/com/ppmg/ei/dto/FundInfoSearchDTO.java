package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;

public class FundInfoSearchDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 基金名称 */
	private String fundName;

	/** 基金注册名称 */
	private String regName;

	/** 基金形式（合伙制、公司制、契约制） */
	private String fundStruct;

	/** 基金一类类别（区域基金、国家基金、产业基金） */
	private String platProp;

	/** 基金二类类别 */
	private String subPlatProp;

	/** 基金状态 */
	private String fundSts;

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getFundStruct() {
		return fundStruct;
	}

	public void setFundStruct(String fundStruct) {
		this.fundStruct = fundStruct;
	}

	public String getPlatProp() {
		return platProp;
	}

	public void setPlatProp(String platProp) {
		this.platProp = platProp;
	}

	public String getSubPlatProp() {
		return subPlatProp;
	}

	public void setSubPlatProp(String subPlatProp) {
		this.subPlatProp = subPlatProp;
	}

	public String getFundSts() {
		return fundSts;
	}

	public void setFundSts(String fundSts) {
		this.fundSts = fundSts;
	}
}