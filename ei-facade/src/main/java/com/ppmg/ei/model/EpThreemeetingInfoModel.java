package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class EpThreemeetingInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 载体(投后主键) */
	private String carrier;

	/** 是否三会事项（0：否，1：是） */
	private String isThree;

	/** 会议标题 */
	private String meetingTitle;

	/** 归档类别（1：董，2：监，3：股，4：普通资料） */
	private String archType;

	/** 归档年月 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date archDt;

	/** 事项类别 */
	private String eventType;

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

	/** null */
	private String rowId;

	/** null */
	private String processversioninstanceguid;

	/** 会后会议记录 */
	private String aftMeetFile;

	/** 是否为内幕消息(1表示是内幕) */
	private String isMimi;

	/** 出资主体ID */
	private String afterFunderId;

	/** 企业ID */
	private String objectId;

	/** 企业名称 */
	private String projObjectName;

	/** 所属公司 */
	private String groupId;

	/** 是否需要国有股权评估,102 */
	private String isGqpg;

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

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getProcessversioninstanceguid() {
		return processversioninstanceguid;
	}

	public void setProcessversioninstanceguid(String processversioninstanceguid) {
		this.processversioninstanceguid = processversioninstanceguid;
	}

	public String getAftMeetFile() {
		return aftMeetFile;
	}

	public void setAftMeetFile(String aftMeetFile) {
		this.aftMeetFile = aftMeetFile;
	}

	public String getIsMimi() {
		return isMimi;
	}

	public void setIsMimi(String isMimi) {
		this.isMimi = isMimi;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getIsGqpg() {
		return isGqpg;
	}

	public void setIsGqpg(String isGqpg) {
		this.isGqpg = isGqpg;
	}

}