package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EiPushFundModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String handleId;

	/** 基金信息维护人ID */
	private String userId;

	/** 基金信息维护人 */
	private String userName;

	/** 基金ID */
	private String fundId;

	/** 基金名称 */
	private String fundName;

	/** 推送季度 */
	private String sendMonth;

	/** 管理平台ID */
	private String groupId;

	/** 管理平台名称 */
	private String groupName;

	/** 是否已完成更新 */
	private String isFinish;

	/** 流程实例号 */
	private String processInstId;

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

}