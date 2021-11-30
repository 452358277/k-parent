package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class SurveyPlanModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 计划ID */
	private String planId;

	/** 载体(基金号、项目号) */
	private String carrier;

	/** 载体名称 */
	private String carrierName;

	/** 载体类别(1：融资，2：投资，3：企业投后) */
	private String carrierType;

	/** 计划名称 */
	private String planName;

	/** 尽调范围 */
	private String surveyContent;

	/** 拟聘机构 */
	private String impOrg;

	/** 尽调费用 */
	private Double surveyFee;

	/** 计划开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 计划结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 流程ID */
	private String processInstId;

	/** 状态 */
	private String status;

	/** 秘书提交审批会议结果 */
	private String meetingFile;

	/** 附件说明 */
	private String attaDetail;

	/** 附件 */
	private String attaFile;

	/** 尽调机构ID */
	private String partnerOrgaId;
	/** 内部外部 */
	private String interior;

	private String projId;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getSurveyContent() {
		return surveyContent;
	}

	public void setSurveyContent(String surveyContent) {
		this.surveyContent = surveyContent;
	}

	public String getImpOrg() {
		return impOrg;
	}

	public void setImpOrg(String impOrg) {
		this.impOrg = impOrg;
	}

	public Double getSurveyFee() {
		return surveyFee;
	}

	public void setSurveyFee(Double surveyFee) {
		this.surveyFee = surveyFee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeetingFile() {
		return meetingFile;
	}

	public void setMeetingFile(String meetingFile) {
		this.meetingFile = meetingFile;
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

	public String getPartnerOrgaId() {
		return partnerOrgaId;
	}

	public void setPartnerOrgaId(String partnerOrgaId) {
		this.partnerOrgaId = partnerOrgaId;
	}

}