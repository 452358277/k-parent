package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundAllocationModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String allId;

	/** 基金ID */
	private String fundId;

	/** 基金账号余额 */
	private Double accountAmount;

	/** 基金分配时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date allocationDt;

	/** 基金分配金额(万元) */
	private Double allocationAmount;

	/** 其中,分配金财投资金额(万元) */
	private Double allocationJcAmount;

	/** 状态 */
	private String status;

	/** 备注 */
	private String remark;

	/** 附件 */
	private String allocationFile;

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	public String getAllId() {
		return allId;
	}

	public void setAllId(String allId) {
		this.allId = allId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public Date getAllocationDt() {
		return allocationDt;
	}

	public void setAllocationDt(Date allocationDt) {
		this.allocationDt = allocationDt;
	}

	public Double getAllocationAmount() {
		return allocationAmount;
	}

	public void setAllocationAmount(Double allocationAmount) {
		this.allocationAmount = allocationAmount;
	}

	public Double getAllocationJcAmount() {
		return allocationJcAmount;
	}

	public void setAllocationJcAmount(Double allocationJcAmount) {
		this.allocationJcAmount = allocationJcAmount;
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

	public String getAllocationFile() {
		return allocationFile;
	}

	public void setAllocationFile(String allocationFile) {
		this.allocationFile = allocationFile;
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