package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class StaffListModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 项目 */
	private String projId;

	/** 被投对象编号 */
	private String objectId;

	/** 对应元禾内部员工号 */
	private String userId;

	/** 是否董事成员（0：否，1：是） */
	private String isDirector;

	/** 是否监事成员（0：否，1：是） */
	private String isSupervisor;

	/** 是否高管 */
	private String isTopManage;

	/** 咨询委员会成员（0：否，1：是） */
	private String isAdvisory;

	/** 观察员（0：否，1：是） */
	private String isObserver;

	/** 投委会委员（0：否，1：是） */
	private String isIc;

	/** 任职起始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date jobStartDate;

	/** 任职结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date jobEndDate;

	/** 是否领取报酬（0：否，1：是） */
	private String isReward;

	/** 状态（0：正常，1,：离任） */
	private String status;

	/** 备注(委派人员职位) */
	private String remark;

	/** 人员名字 */
	private String userName;

	/** null */
	private String projGuid;

	/** 委派人员类型 */
	private String userType;

	/** 人员委派安排ID */
	private String assignId;

	private String applyNo;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsDirector() {
		return isDirector;
	}

	public void setIsDirector(String isDirector) {
		this.isDirector = isDirector;
	}

	public String getIsSupervisor() {
		return isSupervisor;
	}

	public void setIsSupervisor(String isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	public String getIsTopManage() {
		return isTopManage;
	}

	public void setIsTopManage(String isTopManage) {
		this.isTopManage = isTopManage;
	}

	public String getIsAdvisory() {
		return isAdvisory;
	}

	public void setIsAdvisory(String isAdvisory) {
		this.isAdvisory = isAdvisory;
	}

	public String getIsObserver() {
		return isObserver;
	}

	public void setIsObserver(String isObserver) {
		this.isObserver = isObserver;
	}

	public String getIsIc() {
		return isIc;
	}

	public void setIsIc(String isIc) {
		this.isIc = isIc;
	}

	public Date getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Date jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public Date getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(Date jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	public String getIsReward() {
		return isReward;
	}

	public void setIsReward(String isReward) {
		this.isReward = isReward;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

}