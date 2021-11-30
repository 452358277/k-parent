package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OtherThreeMeetingListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String meetingId;

	/** 项目ID */
	private String projId;

	/** 项目名称 */
	private String projName;

	/** 会议召开时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date meetingDate;

	/** 会议内容 */
	private String remark;

	/** 附件 */
	private String attaFile;

	/** 流程实例ID */
	private String processInstId;

	/** 部门ID */
	private String deptId;

	/** 部门名称 */
	private String deptName;

	/** 平台ID */
	private String groupId;

	/** 平台名称 */
	private String groupName;

	/** 状态 */
	private String status;

	/** 编号 */
	private String sn;

	/** 是否重大(0:重大;1:非重大) */
	private String isImportant;

	/** 董监事ID */
	private String djUserid;

	/** 董监事姓名 */
	private String djUsername;

	/** 项目全称 */
	private String projFullName;

	private FixflowRunTaskinstanceModel instanceModel;

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}

	public String getDjUserid() {
		return djUserid;
	}

	public void setDjUserid(String djUserid) {
		this.djUserid = djUserid;
	}

	public String getDjUsername() {
		return djUsername;
	}

	public void setDjUsername(String djUsername) {
		this.djUsername = djUsername;
	}

	public String getProjFullName() {
		return projFullName;
	}

	public void setProjFullName(String projFullName) {
		this.projFullName = projFullName;
	}

}