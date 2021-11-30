package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class QuarterReportModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String reportId;

	/** 基金ID */
	private String fundId;

	/** 基金名称 */
	private String fundName;

	/** 年度 */
	private String year;

	/** 季度 */
	private String quarter;

	/** 备注 */
	private String remart;

	/** 反馈点评 */
	private String checkResult;

	/** 附件 */
	private String attaFile;

	/** 状态（0正常，1删除） */
	private String status;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getRemart() {
		return remart;
	}

	public void setRemart(String remart) {
		this.remart = remart;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}