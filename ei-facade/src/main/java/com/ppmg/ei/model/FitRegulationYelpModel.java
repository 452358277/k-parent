package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FitRegulationYelpModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fitId;

	/** 合规类型 */
	private String fitType;

	/** 项目/基金ID */
	private String projOrFundId;

	/** 项目/基金名称 */
	private String projOrFundName;

	/** 基金管理人 */
	private String mcId;

	/** 指标ID */
	private String indexId;

	/** 指标名称 */
	private String indexName;

	/** 问题描述 */
	private String problemDesc;

	/** 核查结果 */
	private String checkResult;

	/** 附件 */
	private String attaFile;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFitId() {
		return fitId;
	}

	public void setFitId(String fitId) {
		this.fitId = fitId;
	}

	public String getFitType() {
		return fitType;
	}

	public void setFitType(String fitType) {
		this.fitType = fitType;
	}

	public String getProjOrFundId() {
		return projOrFundId;
	}

	public void setProjOrFundId(String projOrFundId) {
		this.projOrFundId = projOrFundId;
	}

	public String getProjOrFundName() {
		return projOrFundName;
	}

	public void setProjOrFundName(String projOrFundName) {
		this.projOrFundName = projOrFundName;
	}

	public String getMcId() {
		return mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

}