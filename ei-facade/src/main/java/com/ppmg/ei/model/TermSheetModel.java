package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class TermSheetModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String sheetId;

	/** 项目 */
	private String projId;

	/** 意向书类型 */
	private String sheetType;

	/** 签订时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date signDt;

	/** 电子版 */
	private String eDoc;

	/** 正式版 */
	private String formVersion;

	/** 状态（0：草稿，1：审核中，2：已审核，9：删除） */
	private String status;

	/** 流程实例 */
	private String processInstId;
	
	private List<EiTAttachmentModel> eDocAttList;
	
	private List<EiTAttachmentModel> formVersionAttList;

	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public Date getSignDt() {
		return signDt;
	}

	public void setSignDt(Date signDt) {
		this.signDt = signDt;
	}

	public String getEDoc() {
		return eDoc;
	}

	public void setEDoc(String eDoc) {
		this.eDoc = eDoc;
	}

	public String getFormVersion() {
		return formVersion;
	}

	public void setFormVersion(String formVersion) {
		this.formVersion = formVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String geteDoc() {
		return eDoc;
	}

	public void seteDoc(String eDoc) {
		this.eDoc = eDoc;
	}

	public List<EiTAttachmentModel> geteDocAttList() {
		return eDocAttList;
	}

	public void seteDocAttList(List<EiTAttachmentModel> eDocAttList) {
		this.eDocAttList = eDocAttList;
	}

	public List<EiTAttachmentModel> getFormVersionAttList() {
		return formVersionAttList;
	}

	public void setFormVersionAttList(List<EiTAttachmentModel> formVersionAttList) {
		this.formVersionAttList = formVersionAttList;
	}


}