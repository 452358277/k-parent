package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class RegulationApproveModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String iraId;

	/** 企业注册状态(码值：) */
	private String entStatus;

	/** 被投企业ID */
	private String entId;

	/** 被投企业名称 */
	private String entName;

	/** 企业类型(码值：) */
	private String financeDate;

	/** 出资主体ID */
	private String inveId;

	/** 出资主体名称 */
	private String inveName;

	/** 投资金额 */
	private Double inveAmt;

	/** 所属行业 */
	private String industry;

	/** 所属行业名称 */
	private String industryName;

	/** 说明 */
	private String remark;

	/** 附件 */
	private String attaFile;

	/** 项目ID */
	private String projId;

	/** 项目名称 */
	private String projName;


	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	private String processInstId;

	private String processStatus;


	private String commFile;


	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getIraId() {
		return iraId;
	}

	public void setIraId(String iraId) {
		this.iraId = iraId;
	}

	public String getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(String entStatus) {
		this.entStatus = entStatus;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getFinanceDate() {
		return financeDate;
	}

	public void setFinanceDate(String financeDate) {
		this.financeDate = financeDate;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public Double getInveAmt() {
		return inveAmt;
	}

	public void setInveAmt(Double inveAmt) {
		this.inveAmt = inveAmt;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Long rowVersion) {
		this.rowVersion = rowVersion;
	}


	public String getCommFile() {
		return commFile;
	}

	public void setCommFile(String commFile) {
		this.commFile = commFile;
	}
}