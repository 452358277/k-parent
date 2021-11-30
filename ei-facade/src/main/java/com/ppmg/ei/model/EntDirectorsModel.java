package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EntDirectorsModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private String directorsId;

	/** 个人ID */
	private String personId;

	/** 企业ID */
	private String enterpriseId;

	/** 职位 */
	private String job;

	/** 是否元禾人员（0：否，1：是） */
	private String isYh;

	/** 个人持股比例 */
	private Double personRate;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	public String getDirectorsId() {
		return directorsId;
	}

	public void setDirectorsId(String directorsId) {
		this.directorsId = directorsId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIsYh() {
		return isYh;
	}

	public void setIsYh(String isYh) {
		this.isYh = isYh;
	}

	public Double getPersonRate() {
		return personRate;
	}

	public void setPersonRate(Double personRate) {
		this.personRate = personRate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}