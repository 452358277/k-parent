package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class QuotaInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 指标名称 */
	private String quotaName;

	/** 指标单位 */
	private String quotaUnit;

	/** 是否整数 */
	private String isInt;

	/** 备注 */
	private String remark;

	/** 状态（0:正常 9:删除） */
	private String status;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
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

	public String getIsInt() {
		return isInt;
	}

	public void setIsInt(String isInt) {
		this.isInt = isInt;
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

}