package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class TEntBranchModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 公司注册号码 */
	private String orgNo;

	/** 企业ID */
	private String enterpriseId;

	/** 公司名称 */
	private String orgName;

	/** 登记机关 */
	private String belongOrg;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(String belongOrg) {
		this.belongOrg = belongOrg;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}