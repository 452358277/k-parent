package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class ProjMemberModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 所属项目 */
	private String projId;

	/** 成员编号 */
	private String memberId;

	/** 成员名称 */
	private String memberName;

	/** 是否项目经理,0.经办人，1.项目经理，2是分管副总 */
	private String isPm;

	/** null */
	private String projGuid;

	/** 项目权限审批状态(0草稿，1审批中，2审批通过，3删除) */
	private String approveStatus;

	/** 是否项目所属平台人员(0是，1否) */
	private String isGroup;

	/** 流程实例号(项目权限审批) */
	private String processInstId;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getIsPm() {
		return isPm;
	}

	public void setIsPm(String isPm) {
		this.isPm = isPm;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

}