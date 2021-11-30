package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ScParamModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String paramId;

	/** 客户ID */
	private String cusId;

	/** 客户名称 */
	private String cusName;

	/** 所属子系统(1:债权;2:股权投前企业;3:股权投前基金;4:股权-母基金  ;5股权投后企业;6:股权投后基金) */
	private String sysType;

	/** 客户类型(1自然人2企业3政府平台) */
	private String cusType;

	/** 客户当前评级 */
	private String cusLevel;

	/** 认定时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date confirmDt;

	/** 年销售收入 */
	private Long inmoneyY;

	/** 行业 */
	private String industry;

	/** 是否科技型企业 */
	private String isTec;

	/** 是否房地产融资项目 */
	private String isEstate;

	/** 1：草稿；2：审批中；3：审批同意；4：已否决；9：删除 */
	private String status;

	/** 流程实例号 */
	private String processInstId;

	/** null */
	private String cardIds;

	/** 最终得分 */
	private Long finalScore;

	/** 审批人1 */
	private String applyUser1;

	/** 审批人2 */
	private String applyUser2;

	/** 审批人3 */
	private String applyUser3;

	/** 是否生效1:生效0：失效 */
	private String isActive;

	/** 项目ID */
	private String projId;

	/** 项目名称 */
	private String projName;

	/** 评估年 */
	private String evaluateYear;

	/** 评估JIDU */
	private String evaluateQ;

	/** 合并提交质量评估 */
	private String projIdTogether;

	/** 1：草稿；2：审批中；3：审批同意；4：已否决；9：删除 */
	private String statusName;

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusLevel() {
		return cusLevel;
	}

	public void setCusLevel(String cusLevel) {
		this.cusLevel = cusLevel;
	}

	public Date getConfirmDt() {
		return confirmDt;
	}

	public void setConfirmDt(Date confirmDt) {
		this.confirmDt = confirmDt;
	}

	public Long getInmoneyY() {
		return inmoneyY;
	}

	public void setInmoneyY(Long inmoneyY) {
		this.inmoneyY = inmoneyY;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIsTec() {
		return isTec;
	}

	public void setIsTec(String isTec) {
		this.isTec = isTec;
	}

	public String getIsEstate() {
		return isEstate;
	}

	public void setIsEstate(String isEstate) {
		this.isEstate = isEstate;
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

	public String getCardIds() {
		return cardIds;
	}

	public void setCardIds(String cardIds) {
		this.cardIds = cardIds;
	}

	public Long getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Long finalScore) {
		this.finalScore = finalScore;
	}

	public String getApplyUser1() {
		return applyUser1;
	}

	public void setApplyUser1(String applyUser1) {
		this.applyUser1 = applyUser1;
	}

	public String getApplyUser2() {
		return applyUser2;
	}

	public void setApplyUser2(String applyUser2) {
		this.applyUser2 = applyUser2;
	}

	public String getApplyUser3() {
		return applyUser3;
	}

	public void setApplyUser3(String applyUser3) {
		this.applyUser3 = applyUser3;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getEvaluateYear() {
		return evaluateYear;
	}

	public void setEvaluateYear(String evaluateYear) {
		this.evaluateYear = evaluateYear;
	}

	public String getEvaluateQ() {
		return evaluateQ;
	}

	public void setEvaluateQ(String evaluateQ) {
		this.evaluateQ = evaluateQ;
	}

	public String getProjIdTogether() {
		return projIdTogether;
	}

	public void setProjIdTogether(String projIdTogether) {
		this.projIdTogether = projIdTogether;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}