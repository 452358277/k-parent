package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EnteEmployeeOverviewModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 企业编号 */
	private String enterpriseId;

	/** 季度 */
	private String quarter;

	/** 员工人数 */
	private Long totalEmployees;

	/** 兼职人数 */
	private Long partTime;

	/** 博士人数 */
	private Long doctoral;

	/** 硕士人数 */
	private Long master;

	/** 本科人数 */
	private Long bachelor;

	/** 海归人数 */
	private Long returnees;

	/** 其它人员数量 */
	private Long other;

	/** 海外归国人员信息 */
	private String returneesInfo;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 关联任务号 */
	private String taskId;

	/** 从事研究开发人员数 */
	private Long developer;

	/** null */
	private String projGuid;

	/** 企业外网信息确认 */
	private String confirm;

	/** null */
	private String returneesInfoBak;

	/** 出资主体ID */
	private String investId;

	/** 企业人事推送HANDLE_ID */
	private String handleId;

	/** 是否提供人事数据code 102 */
	private String rsdata;

	/** 无法获取人事信息原因 */
	private String remarkRs;


	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public Long getPartTime() {
		return partTime;
	}

	public void setPartTime(Long partTime) {
		this.partTime = partTime;
	}

	public Long getDoctoral() {
		return doctoral;
	}

	public void setDoctoral(Long doctoral) {
		this.doctoral = doctoral;
	}

	public Long getMaster() {
		return master;
	}

	public void setMaster(Long master) {
		this.master = master;
	}

	public Long getBachelor() {
		return bachelor;
	}

	public void setBachelor(Long bachelor) {
		this.bachelor = bachelor;
	}

	public Long getReturnees() {
		return returnees;
	}

	public void setReturnees(Long returnees) {
		this.returnees = returnees;
	}

	public Long getOther() {
		return other;
	}

	public void setOther(Long other) {
		this.other = other;
	}

	public String getReturneesInfo() {
		return returneesInfo;
	}

	public void setReturneesInfo(String returneesInfo) {
		this.returneesInfo = returneesInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getDeveloper() {
		return developer;
	}

	public void setDeveloper(Long developer) {
		this.developer = developer;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getReturneesInfoBak() {
		return returneesInfoBak;
	}

	public void setReturneesInfoBak(String returneesInfoBak) {
		this.returneesInfoBak = returneesInfoBak;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getRsdata() {
		return rsdata;
	}

	public void setRsdata(String rsdata) {
		this.rsdata = rsdata;
	}

	public String getRemarkRs() {
		return remarkRs;
	}

	public void setRemarkRs(String remarkRs) {
		this.remarkRs = remarkRs;
	}

}