package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class EpValuRptModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 投后主键（投后的HANDLE_ID） */
	private String iaId;

	/** 报告人 */
	private String rptBy;

	/** 报告日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rptDt;

	/** 报告标题 */
	private String rptTitle;

	/** 报告总结 */
	private String rptDesc;

	/** 分析报告附件 */
	private String rptFile;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	/** null */
	private String projGuid;

	/** 任务ID */
	private String taskId;

	/** 任务名称 */
	private String taskName;

	/** 出资主体ID */
	private String afterFunderId;

	/** 企业ID */
	private String objectId;

	/** 企业名称 */
	private String projObjectName;

	/** 投后主键 */
	private String handleId;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getIaId() {
		return iaId;
	}

	public void setIaId(String iaId) {
		this.iaId = iaId;
	}

	public String getRptBy() {
		return rptBy;
	}

	public void setRptBy(String rptBy) {
		this.rptBy = rptBy;
	}

	public Date getRptDt() {
		return rptDt;
	}

	public void setRptDt(Date rptDt) {
		this.rptDt = rptDt;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getRptDesc() {
		return rptDesc;
	}

	public void setRptDesc(String rptDesc) {
		this.rptDesc = rptDesc;
	}

	public String getRptFile() {
		return rptFile;
	}

	public void setRptFile(String rptFile) {
		this.rptFile = rptFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAfterFunderId() {
		return afterFunderId;
	}

	public void setAfterFunderId(String afterFunderId) {
		this.afterFunderId = afterFunderId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

}