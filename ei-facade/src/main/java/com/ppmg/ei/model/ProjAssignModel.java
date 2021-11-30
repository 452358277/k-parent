package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class ProjAssignModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 人员委派ID */
	private String assignId;

	/** 项目ID */
	private String projId;

	/** 流程ID */
	private String processInstId;

	/** 状态 */
	private String status;

	/** 秘书提交审批会议结果 */
	private String meetingFile;

	private String projUser;

	private String applyNo;


	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getProjUser() {
		return projUser;
	}

	public void setProjUser(String projUser) {
		this.projUser = projUser;
	}

	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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

}