package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class HandleRecordModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID，SEQ_EI_MY_HANDLE_RECORD */
	private String pkId;

	/** 关联投后更新表主键 */
	private String handleId;

	/** 当前季度 */
	private String sendMonth;

	/** 管理平台ID */
	private String groupId;

	/** 出资主体 */
	private String investId;

	/** 被投对象（企业ID） */
	private String enterId;

	/** 字段名 */
	private String columName;

	/** 内容 */
	private String content;

	/** 字段描述 */
	private String columRemark;

	/** 字段来源（1.立项；2.决策 等等） */
	private String columResource;

	/** 项目/基金id */
	private String projectOrFundId;

	private String  contentOld;

	public String getContentOld() {
		return contentOld;
	}

	public void setContentOld(String contentOld) {
		this.contentOld = contentOld;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
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

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getEnterId() {
		return enterId;
	}

	public void setEnterId(String enterId) {
		this.enterId = enterId;
	}

	public String getColumName() {
		return columName;
	}

	public void setColumName(String columName) {
		this.columName = columName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getColumRemark() {
		return columRemark;
	}

	public void setColumRemark(String columRemark) {
		this.columRemark = columRemark;
	}

	public String getColumResource() {
		return columResource;
	}

	public void setColumResource(String columResource) {
		this.columResource = columResource;
	}

	public String getProjectOrFundId() {
		return projectOrFundId;
	}

	public void setProjectOrFundId(String projectOrFundId) {
		this.projectOrFundId = projectOrFundId;
	}

}