package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FundAnalysisReportModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String rptId;

	/** 基金ID */
	private String fundId;

	/** 报告标题 */
	private String rptTitle;

	/** 报告分类 */
	private String rptType;

	/** 报告日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rptDt;

	/** 报告附件文件 */
	private String rptFile;

	/** 备注说明 */
	private String remark;

	/** 状态（0：正常，1：删除） */
	private String status;

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getRptType() {
		return rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}

	public Date getRptDt() {
		return rptDt;
	}

	public void setRptDt(Date rptDt) {
		this.rptDt = rptDt;
	}

	public String getRptFile() {
		return rptFile;
	}

	public void setRptFile(String rptFile) {
		this.rptFile = rptFile;
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