package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DataQuarterModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String taskId;

	/** 年度 */
	private String year;

	/** 季度 */
	private String quarter;

	/** 身份类型(2：省政府基金管理人；3：省政府基金托管行；4：社保基金企业；5：直投平台出资人代表/管理人；) */
	private String userType;

	/** 用户账号表名 */
	private String userTableName;

	/** 用户账号表主键名 */
	private String userBizKey;

	/** 用户账号表主键值 */
	private String userBizValue;

	/** 填报任务名称 */
	private String taskName;

	/** 填报截止时间 */
	private String endDate;

	/** 状态(码值：0未完成，1已完成) */
	private String status;

	/** 完成时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date finishDate;

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	private String phoneNo;

	private String loginName;

	private List<DataQuarterItemModel> listItem;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public List<DataQuarterItemModel> getListItem() {
		return listItem;
	}

	public void setListItem(List<DataQuarterItemModel> listItem) {
		this.listItem = listItem;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserTableName() {
		return userTableName;
	}

	public void setUserTableName(String userTableName) {
		this.userTableName = userTableName;
	}

	public String getUserBizKey() {
		return userBizKey;
	}

	public void setUserBizKey(String userBizKey) {
		this.userBizKey = userBizKey;
	}

	public String getUserBizValue() {
		return userBizValue;
	}

	public void setUserBizValue(String userBizValue) {
		this.userBizValue = userBizValue;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Long rowVersion) {
		this.rowVersion = rowVersion;
	}

}