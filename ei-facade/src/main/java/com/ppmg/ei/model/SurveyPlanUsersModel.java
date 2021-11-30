package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class SurveyPlanUsersModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 尽调主键 */
	private String planId;

	/** 内部人员id */
	private String planUseId;

	/** 内部人员名称 */
	private String planName;

	/** null */
	private String status;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanUseId() {
		return planUseId;
	}

	public void setPlanUseId(String planUseId) {
		this.planUseId = planUseId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}