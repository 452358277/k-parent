package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MeetingRecordVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String meetingId;

	/** 会议主题 */
	private String meetingTitle;

	/** 会议日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date meetingDate;

	/** 会议开始时间 */
	private String meetingBeginTime;

	/** 会议结束时间 */
	private String meetingEndTime;

	/** 会议地点 */
	private String meetingRoom;

	/** 关联被投企业 */
	private String objectId;

	/** 是否初次拜访 */
	private String isFirst;

	/** 会议类型 */
	private String meetingType;

	/** 会议方式 */
	private String meetingMethod;

	/** 外部参会人员 */
	private String meetingPerson;

	/** 附件 */
	private String attachment;

	/** null */
	private String delFlag;

	/** 会议室名称 */
	private String meetingRoomName;

	/** 关联被投企业名称 */
	private String projectObjectName;

	/** 关联OA会议 */
	private String refOaMeeting;

	/** 关联OA会议名称 */
	private String refOaMeetingName;

	/** 所属平台 */
	private String groupId;

	/** null */
	private String meetingContent;

	/** 会议关注日程,关注日程：'0':'全员可见','1':'本公司可见','2':'参与人可见' */
	private String meetingFocusMode;

	//会议代表
	private String meetingRepresent;

	private String meetingTypeName;

	public String getMeetingTypeName() {
		return meetingTypeName;
	}

	public void setMeetingTypeName(String meetingTypeName) {
		this.meetingTypeName = meetingTypeName;
	}

	public String getMeetingRepresent() {
		return meetingRepresent;
	}

	public void setMeetingRepresent(String meetingRepresent) {
		this.meetingRepresent = meetingRepresent;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingBeginTime() {
		return meetingBeginTime;
	}

	public void setMeetingBeginTime(String meetingBeginTime) {
		this.meetingBeginTime = meetingBeginTime;
	}

	public String getMeetingEndTime() {
		return meetingEndTime;
	}

	public void setMeetingEndTime(String meetingEndTime) {
		this.meetingEndTime = meetingEndTime;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getMeetingMethod() {
		return meetingMethod;
	}

	public void setMeetingMethod(String meetingMethod) {
		this.meetingMethod = meetingMethod;
	}

	public String getMeetingPerson() {
		return meetingPerson;
	}

	public void setMeetingPerson(String meetingPerson) {
		this.meetingPerson = meetingPerson;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public String getProjectObjectName() {
		return projectObjectName;
	}

	public void setProjectObjectName(String projectObjectName) {
		this.projectObjectName = projectObjectName;
	}

	public String getRefOaMeeting() {
		return refOaMeeting;
	}

	public void setRefOaMeeting(String refOaMeeting) {
		this.refOaMeeting = refOaMeeting;
	}

	public String getRefOaMeetingName() {
		return refOaMeetingName;
	}

	public void setRefOaMeetingName(String refOaMeetingName) {
		this.refOaMeetingName = refOaMeetingName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}

	public String getMeetingFocusMode() {
		return meetingFocusMode;
	}

	public void setMeetingFocusMode(String meetingFocusMode) {
		this.meetingFocusMode = meetingFocusMode;
	}

}