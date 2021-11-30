package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EiMyHandleModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** PK */
	private String handleId;

	/** 项目经理ID */
	private String userId;

	/** 项目经理名称 */
	private String userName;

	/** 企业PK */
	private String enterId;

	/** 企业名称 */
	private String enterName;

	/** 推送季度 */
	private String sendMonth;

	/** 出资主体PK */
	private String investId;

	/** 出资主体名称 */
	private String investName;

	/** 管理平台ID */
	private String groupId;

	/** 管理平台名称 */
	private String groupName;

	/** 推送类型：股权系统 */
	private String descPart1;

	/** 推送类型(1:企业基本投后信息;2:企业人事财务信息) */
	private String descPart2;

	/** 是否已完成更新(1:完成) */
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

	public String getEnterId() {
		return enterId;
	}

	public void setEnterId(String enterId) {
		this.enterId = enterId;
	}

	public String getEnterName() {
		return enterName;
	}

	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
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

	public String getDescPart1() {
		return descPart1;
	}

	public void setDescPart1(String descPart1) {
		this.descPart1 = descPart1;
	}

	public String getDescPart2() {
		return descPart2;
	}

	public void setDescPart2(String descPart2) {
		this.descPart2 = descPart2;
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