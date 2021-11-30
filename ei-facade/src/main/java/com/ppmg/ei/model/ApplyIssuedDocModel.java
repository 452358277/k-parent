package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ApplyIssuedDocModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 申请表单号 */
	private String applyNo;

	/** 文档编号 */
	private String documentNo;

	/** 发文状态:发文拟稿，部门核稿，办公室审核，部门会签，领导签发，停止签发，已签发，退回修改 */
	private String issuedStatus;

	/** 发文标题 */
	private String issuedTitle;

	/** 发文主题词 */
	private String issuedKeywords;

	/** 发文密级 */
	private String issuedLevel;

	/** 发文紧急程度 */
	private String issuedUrgency;

	/** 主要内容 */
	private String content;

	/** 附件 */
	private String attachment;

	/** 油印份数 */
	private Long printCopies;

	/** 备注 */
	private String remark;

	/** 分管领导 */
	private Long leaderId;

	/** 分管领导名称 */
	private String leaderName;

	/** 主送 */
	private String destinationMs;

	/** 抄送 */
	private String destinationCc;

	/** 消息通知人员ID */
	private String destinationMsg;

	/** 消息通知人员名称 */
	private String destinationMsgName;

	/** 是否党委发文(1:是) */
	private String isDangweiSend;

	/** 是否纪委发文(1:是) */
	private String isJiweiSend;

	/** 印发数量 */
	private Long printAmount;

	/** 会办部门 */
	private String meetingDepart;

	/** 发文日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date issueDocumentTime;

	/** 发文文号 */
	private String issueDocumentNo;

	/** 紧急程度（码值:11007） */
	private String urgencyDegree;

	/** 码值:11008 发文类型:请示、报告、批复、通知、函、其它 */
	private String issueDocumentType;

	/** 码值: 11009 发文类别: 内部发文、外部发文 */
	private String issueDocumentClass;

	/** 会办部门ID */
	private String meetingDepartId;


	private String destinationMsName;

	private String destinationCcName;


	public String getDestinationMsName() {
		return destinationMsName;
	}

	public void setDestinationMsName(String destinationMsName) {
		this.destinationMsName = destinationMsName;
	}

	public String getDestinationCcName() {
		return destinationCcName;
	}

	public void setDestinationCcName(String destinationCcName) {
		this.destinationCcName = destinationCcName;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getIssuedStatus() {
		return issuedStatus;
	}

	public void setIssuedStatus(String issuedStatus) {
		this.issuedStatus = issuedStatus;
	}

	public String getIssuedTitle() {
		return issuedTitle;
	}

	public void setIssuedTitle(String issuedTitle) {
		this.issuedTitle = issuedTitle;
	}

	public String getIssuedKeywords() {
		return issuedKeywords;
	}

	public void setIssuedKeywords(String issuedKeywords) {
		this.issuedKeywords = issuedKeywords;
	}

	public String getIssuedLevel() {
		return issuedLevel;
	}

	public void setIssuedLevel(String issuedLevel) {
		this.issuedLevel = issuedLevel;
	}

	public String getIssuedUrgency() {
		return issuedUrgency;
	}

	public void setIssuedUrgency(String issuedUrgency) {
		this.issuedUrgency = issuedUrgency;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Long getPrintCopies() {
		return printCopies;
	}

	public void setPrintCopies(Long printCopies) {
		this.printCopies = printCopies;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getDestinationMs() {
		return destinationMs;
	}

	public void setDestinationMs(String destinationMs) {
		this.destinationMs = destinationMs;
	}

	public String getDestinationCc() {
		return destinationCc;
	}

	public void setDestinationCc(String destinationCc) {
		this.destinationCc = destinationCc;
	}

	public String getDestinationMsg() {
		return destinationMsg;
	}

	public void setDestinationMsg(String destinationMsg) {
		this.destinationMsg = destinationMsg;
	}

	public String getDestinationMsgName() {
		return destinationMsgName;
	}

	public void setDestinationMsgName(String destinationMsgName) {
		this.destinationMsgName = destinationMsgName;
	}

	public String getIsDangweiSend() {
		return isDangweiSend;
	}

	public void setIsDangweiSend(String isDangweiSend) {
		this.isDangweiSend = isDangweiSend;
	}

	public String getIsJiweiSend() {
		return isJiweiSend;
	}

	public void setIsJiweiSend(String isJiweiSend) {
		this.isJiweiSend = isJiweiSend;
	}

	public Long getPrintAmount() {
		return printAmount;
	}

	public void setPrintAmount(Long printAmount) {
		this.printAmount = printAmount;
	}

	public String getMeetingDepart() {
		return meetingDepart;
	}

	public void setMeetingDepart(String meetingDepart) {
		this.meetingDepart = meetingDepart;
	}

	public Date getIssueDocumentTime() {
		return issueDocumentTime;
	}

	public void setIssueDocumentTime(Date issueDocumentTime) {
		this.issueDocumentTime = issueDocumentTime;
	}

	public String getIssueDocumentNo() {
		return issueDocumentNo;
	}

	public void setIssueDocumentNo(String issueDocumentNo) {
		this.issueDocumentNo = issueDocumentNo;
	}

	public String getUrgencyDegree() {
		return urgencyDegree;
	}

	public void setUrgencyDegree(String urgencyDegree) {
		this.urgencyDegree = urgencyDegree;
	}

	public String getIssueDocumentType() {
		return issueDocumentType;
	}

	public void setIssueDocumentType(String issueDocumentType) {
		this.issueDocumentType = issueDocumentType;
	}

	public String getIssueDocumentClass() {
		return issueDocumentClass;
	}

	public void setIssueDocumentClass(String issueDocumentClass) {
		this.issueDocumentClass = issueDocumentClass;
	}

	public String getMeetingDepartId() {
		return meetingDepartId;
	}

	public void setMeetingDepartId(String meetingDepartId) {
		this.meetingDepartId = meetingDepartId;
	}

}