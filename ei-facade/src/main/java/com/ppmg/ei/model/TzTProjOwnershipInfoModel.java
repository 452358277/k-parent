package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TzTProjOwnershipInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 股权结构信息主键 */
	private String osId;

	/** 项目编号 */
	private String projId;

	/** 记录日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 股权结构表 */
	private String esSheet;

	/** 验资报告 */
	private String cvRpt;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	private String fundId;


	private String fundName;


	private String enteId;

	private String enteName;


	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnteName() {
		return enteName;
	}

	public void setEnteName(String enteName) {
		this.enteName = enteName;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
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

	public String getOsId() {
		return osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getEsSheet() {
		return esSheet;
	}

	public void setEsSheet(String esSheet) {
		this.esSheet = esSheet;
	}

	public String getCvRpt() {
		return cvRpt;
	}

	public void setCvRpt(String cvRpt) {
		this.cvRpt = cvRpt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}