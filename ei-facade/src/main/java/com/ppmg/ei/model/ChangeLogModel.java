package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ChangeLogModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String logId;

	/** 企业ID */
	private String enterpriseId;

	/** 变更模块 */
	private String chgModule;

	/** 变更项,变更类型 */
	private String chgItem;

	/** 变更后内容 */
	private String chgAfterContent;

	/** 变更前内容 */
	private String chgBeforeContent;

	/** 变更日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date chgDate;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getChgModule() {
		return chgModule;
	}

	public void setChgModule(String chgModule) {
		this.chgModule = chgModule;
	}

	public String getChgItem() {
		return chgItem;
	}

	public void setChgItem(String chgItem) {
		this.chgItem = chgItem;
	}

	public String getChgAfterContent() {
		return chgAfterContent;
	}

	public void setChgAfterContent(String chgAfterContent) {
		this.chgAfterContent = chgAfterContent;
	}

	public String getChgBeforeContent() {
		return chgBeforeContent;
	}

	public void setChgBeforeContent(String chgBeforeContent) {
		this.chgBeforeContent = chgBeforeContent;
	}

	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}