package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundMethodModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String methodId;

	/** null */
	private String fundId;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date archDt;

	/** null */
	private String methodContent;

	/** null */
	private String methodFiles;

	/** null */
	private String status;

	/** null */
	private String title;

	/** null */
	private String approvalNum;

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Date getArchDt() {
		return archDt;
	}

	public void setArchDt(Date archDt) {
		this.archDt = archDt;
	}

	public String getMethodContent() {
		return methodContent;
	}

	public void setMethodContent(String methodContent) {
		this.methodContent = methodContent;
	}

	public String getMethodFiles() {
		return methodFiles;
	}

	public void setMethodFiles(String methodFiles) {
		this.methodFiles = methodFiles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

}