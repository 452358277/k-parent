package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjRegularAssessVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 投后主键（投后的HANDLE_ID） */
	private String iaId;

	/** 投资对象，被投对象（企业） */
	private String objectId;

	/** 当前阶段 */
	private String projStatus;

	/** 盈利状态 */
	private String profitable;

	/** 风险等级 */
	private String riskLevel;

	/** 关注程度 */
	private String focusLevel;

	/** 定期分析报告 */
	private String attaFile;

	/** 关联任务号 */
	private String taskId;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	/** null */
	private String rowId;

	/** 出资主体ID */
	private String afterFunderId;

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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getProfitable() {
		return profitable;
	}

	public void setProfitable(String profitable) {
		this.profitable = profitable;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getFocusLevel() {
		return focusLevel;
	}

	public void setFocusLevel(String focusLevel) {
		this.focusLevel = focusLevel;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssessBy() {
		return assessBy;
	}

	public void setAssessBy(String assessBy) {
		this.assessBy = assessBy;
	}

	public Date getAssessDt() {
		return assessDt;
	}

	public void setAssessDt(Date assessDt) {
		this.assessDt = assessDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getAfterFunderId() {
		return afterFunderId;
	}

	public void setAfterFunderId(String afterFunderId) {
		this.afterFunderId = afterFunderId;
	}

}