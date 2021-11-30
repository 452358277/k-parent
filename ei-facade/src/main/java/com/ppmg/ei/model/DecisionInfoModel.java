package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class DecisionInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 项目 */
	private String projId;

	/** 企业 */
	private String enterpriseId;

	/** 董事席数 */
	private Long directorCount;

	/** 元禾董事席数 */
	private Long yhDireCount;

	/** 监事席数 */
	private Long supervisorCount;

	/** 元禾监事席数 */
	private Long yhSupeCount;

	/** 流程实例号 */
	private String processInstId;

	/** null */
	private String projGuid;

	/** 决策会议记录 */
	private String decisionAtta;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getDirectorCount() {
		return directorCount;
	}

	public void setDirectorCount(Long directorCount) {
		this.directorCount = directorCount;
	}

	public Long getYhDireCount() {
		return yhDireCount;
	}

	public void setYhDireCount(Long yhDireCount) {
		this.yhDireCount = yhDireCount;
	}

	public Long getSupervisorCount() {
		return supervisorCount;
	}

	public void setSupervisorCount(Long supervisorCount) {
		this.supervisorCount = supervisorCount;
	}

	public Long getYhSupeCount() {
		return yhSupeCount;
	}

	public void setYhSupeCount(Long yhSupeCount) {
		this.yhSupeCount = yhSupeCount;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getDecisionAtta() {
		return decisionAtta;
	}

	public void setDecisionAtta(String decisionAtta) {
		this.decisionAtta = decisionAtta;
	}

}