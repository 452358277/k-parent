package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundReportModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String reportId;

	/** 基金id */
	private String fundId;

	/**年份 */
	private String year;

	/** 季度 */
	private String quarter;

	/** 附件说明 */
	private String attaDetail;

	/** 附件 */
	private String attaFile;

	/** 状态 */
	private String status;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public String getAttaDetail() {
		return attaDetail;
	}

	public void setAttaDetail(String attaDetail) {
		this.attaDetail = attaDetail;
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