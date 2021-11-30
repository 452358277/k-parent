package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.EiTAttachmentModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FundAnalysisReportVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String rptId;

	/** 基金ID */
	private String fundId;

	/** 报告标题 */
	private String rptTitle;

	/** 报告分类 */
	private String rptTypeName;

	/** 报告分类 */
	private String createByName;

	/** 报告日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rptDt;

	/** 报告附件文件 */
	private String rptFile;

	/** 备注说明 */
	private String remark;

	/** 报告分类 */
	private String rptType;

	private List<EiTAttachmentModel> attachmentList;

	public List<EiTAttachmentModel> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<EiTAttachmentModel> attachmentList) {
		this.attachmentList = attachmentList;
	}

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

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getRptTypeName() {
		return rptTypeName;
	}

	public void setRptTypeName(String rptTypeName) {
		this.rptTypeName = rptTypeName;
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

	public String getRptType() {
		return rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}
}