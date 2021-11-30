package com.ppmg.ei.model;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public class AdminFundModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 管理基金ID */
	private String fundId;

	/** 基金管理人ID */
	private String adminId;

	/** 基金名称 */
	private String fundName;

	/** 基金规模 */
	private Double fundSize;

	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date estDate;

	/** 注册地 */
	private String regAdd;

	/** 组织形式 */
	private String org;

	/** 管理人 */
	private String adminName;

	/** 联系人 */
	private String contact;

	/** 联系方式 */
	private String phoneNo;

	/** 基金状态 */
	private String status;

	/** 投资项目概况 */
	private String remark;

	/** 投资项目文件 */
	private String atta;

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Double getFundSize() {
		return fundSize;
	}

	public void setFundSize(Double fundSize) {
		this.fundSize = fundSize;
	}

	public Date getEstDate() {
		return estDate;
	}

	public void setEstDate(Date estDate) {
		this.estDate = estDate;
	}

	public String getRegAdd() {
		return regAdd;
	}

	public void setRegAdd(String regAdd) {
		this.regAdd = regAdd;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public String getAtta() {
		return atta;
	}

	public void setAtta(String atta) {
		this.atta = atta;
	}
}