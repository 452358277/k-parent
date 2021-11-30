package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class DueDiligenceRptModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String rptId;

	/** 载体(基金号、项目号) */
	private String carrier;

	/** 载体类别(1：融资，2：投资) */
	private String carrierType;

	/** 开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date beginDt;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDt;

	/** 合作方机构 */
	private String partnerOrga;

	/** 合作方类型 */
	private String orgaType;

	/** 关键字 */
	private String keyword;

	/** 附件 */
	private String rptFile;

	/** 状态(‘0’为正常 ‘1’为已删除) */
	private String status;

	/** 报告日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rptDate;

	/** 报告类型【1:财务、2:法律、3:综合、4:其他】 */
	private String rptType;

	/** 流程实例号 */
	private String processInstId;

	/** 审批状态 */
	private String processStatus;

	/** 合作方机构ID */
	private String partnerOrgaId;

	/** 尽调参与人员 */
	private String staff;

	/** 附件说明 */
	private String rptDetail;

	/** 尽职调查计划 */
	private String surveyPlan;

	/** 尽职调查计划ID */
	private String surveyPlanId;

	/** 资咨询委员会会议评议结果 */
	private String commentResult;

	private String commentFile;

	private String  isMeeting;

	private String projId;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getCommentFile() {
		return commentFile;
	}

	public void setCommentFile(String commentFile) {
		this.commentFile = commentFile;
	}

	public String getIsMeeting() {
		return isMeeting;
	}

	public void setIsMeeting(String isMeeting) {
		this.isMeeting = isMeeting;
	}

	public String getCommentResult() {
		return commentResult;
	}

	public void setCommentResult(String commentResult) {
		this.commentResult = commentResult;
	}

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public Date getBeginDt() {
		return beginDt;
	}

	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getPartnerOrga() {
		return partnerOrga;
	}

	public void setPartnerOrga(String partnerOrga) {
		this.partnerOrga = partnerOrga;
	}

	public String getOrgaType() {
		return orgaType;
	}

	public void setOrgaType(String orgaType) {
		this.orgaType = orgaType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Date getRptDate() {
		return rptDate;
	}

	public void setRptDate(Date rptDate) {
		this.rptDate = rptDate;
	}

	public String getRptType() {
		return rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getPartnerOrgaId() {
		return partnerOrgaId;
	}

	public void setPartnerOrgaId(String partnerOrgaId) {
		this.partnerOrgaId = partnerOrgaId;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getRptDetail() {
		return rptDetail;
	}

	public void setRptDetail(String rptDetail) {
		this.rptDetail = rptDetail;
	}

	public String getSurveyPlan() {
		return surveyPlan;
	}

	public void setSurveyPlan(String surveyPlan) {
		this.surveyPlan = surveyPlan;
	}

	public String getSurveyPlanId() {
		return surveyPlanId;
	}

	public void setSurveyPlanId(String surveyPlanId) {
		this.surveyPlanId = surveyPlanId;
	}

}