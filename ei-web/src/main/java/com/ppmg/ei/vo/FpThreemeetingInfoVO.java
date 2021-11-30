package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import com.ppmg.ei.model.ProjMemberModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FpThreemeetingInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 载体(所投资的基金项目号) */
	private String carrier;

	/** 是否三会事项（0：否，1：是） */
	private String isThree;

	/** 会议标题 */
	private String meetingTitle;

	/** 归档类别（1：董，2：监，3：股，4：普通资料） */
	private String archType;
	private String meetingTypeName;

	public String getMeetingTypeName() {
		return meetingTypeName;
	}

	public void setMeetingTypeName(String meetingTypeName) {
		this.meetingTypeName = meetingTypeName;
	}

	/** 归档年月 */
	@DateTimeFormat(pattern="yyyy-MM")
	@JSONField(format="yyyy-MM")
	private Date archDt;

	/** 事项类别 */
	private String eventType;
	private String eventTypeName;

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	/** 重要性级别 */
	private String importLevel;

	/** 知会领导 */
	private String informLeader;

	/** 会议主题 */
	private String meetSubject;

	/** 关键字 */
	private String keyword;

	/** 附件 */
	private String attaFile;

	/** 三会事项状态 */
	private String status;

	/** 流程实例 */
	private String processInstId;

	/** 会后会议记录 */
	private String aftMeetFile;

	/** 三会事项状态 */
	private String statusName;
	/** 事项类别 */
	private String meetingType;
	/**发生日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date hapDate;
	/** 会议人员 */
	private String meetingNumber;
	/** 处理方式 */
	private String dealWay;
	/** 秘书提交审批会议结果 */
	private String meetingFile;
	//是否需要法务审批
	private String legalApproval;
	private String legalApprovalName;
	//項目名稱
	private String projName;
	//出資主體
	private String inveName;
	private String taskId;

	private String isFinanceApproval;

	public String getIsFinanceApproval() {
		return isFinanceApproval;
	}

	public void setIsFinanceApproval(String isFinanceApproval) {
		this.isFinanceApproval = isFinanceApproval;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getLegalApprovalName() {
		return legalApprovalName;
	}

	public void setLegalApprovalName(String legalApprovalName) {
		this.legalApprovalName = legalApprovalName;
	}

	public String getLegalApproval() {
		return legalApproval;
	}

	public void setLegalApproval(String legalApproval) {
		this.legalApproval = legalApproval;
	}
	private List<ProjMemberModel> projMemberModel;

	public List<ProjMemberModel> getProjMemberModel() {
		return projMemberModel;
	}

	public void setProjMemberModel(List<ProjMemberModel> projMemberModel) {
		this.projMemberModel = projMemberModel;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public Date getHapDate() {
		return hapDate;
	}

	public void setHapDate(Date hapDate) {
		this.hapDate = hapDate;
	}

	public String getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(String meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}

	public String getMeetingFile() {
		return meetingFile;
	}

	public void setMeetingFile(String meetingFile) {
		this.meetingFile = meetingFile;
	}

	private FixflowRunTaskinstanceModel instanceModel;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getIsThree() {
		return isThree;
	}

	public void setIsThree(String isThree) {
		this.isThree = isThree;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public String getArchType() {
		return archType;
	}

	public void setArchType(String archType) {
		this.archType = archType;
	}

	public Date getArchDt() {
		return archDt;
	}

	public void setArchDt(Date archDt) {
		this.archDt = archDt;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getImportLevel() {
		return importLevel;
	}

	public void setImportLevel(String importLevel) {
		this.importLevel = importLevel;
	}

	public String getInformLeader() {
		return informLeader;
	}

	public void setInformLeader(String informLeader) {
		this.informLeader = informLeader;
	}

	public String getMeetSubject() {
		return meetSubject;
	}

	public void setMeetSubject(String meetSubject) {
		this.meetSubject = meetSubject;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
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

	public String getAftMeetFile() {
		return aftMeetFile;
	}

	public void setAftMeetFile(String aftMeetFile) {
		this.aftMeetFile = aftMeetFile;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}
}