package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/** 
 * 出资人代表库
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public class AdminInvestDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 基金管理人ID */
	private String adminId;

	/** 名称 */
	private String adminName;

	/** 注册资本 */
	private Double regCapital;

	/** 注册时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date regDate;

	/** 注册地址 */
	private String regAdd;

	/** 办公地址 */
	private String officeAdd;

	/** 法人代表 */
	private String legal;

	/** 总经理 */
	private String manager;

	/** 联系人 */
	private String contact;

	/** 联系电话 */
	private String phoneNo;

	/** 联系邮箱 */
	private String email;

	/** 是否合作过 */
	private String isCoo;

	/** 备注 */
	private String remark;

	/** 组织形式 */
	private String org;

	/** 统一社会信用代码/注册号 */
	private String regCode;

	/** 是否符合征集条件 */
	private String isFit;

	/** 是否出资人代表(值为rep代表是) */
	private String isRep;

	/** 自有资产 */
	private Double asset1;

	/** 代理其它创业投资机构资产 */
	private Double asset2;

	/** 代理其它机构和个人资产 */
	private Double asset3;

	/** 管理资产规模合计 */
	private Double assetSum;

	/** 政府管理部门批准文件 */
	private String approveFile;

	/** 企业性质 */
	private String entePro;

	private String attachment;

	/** 通讯地址*/
	private String messAddress;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessAddress() {
		return messAddress;
	}

	public void setMessAddress(String messAddress) {
		this.messAddress = messAddress;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Double getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegAdd() {
		return regAdd;
	}

	public void setRegAdd(String regAdd) {
		this.regAdd = regAdd;
	}

	public String getOfficeAdd() {
		return officeAdd;
	}

	public void setOfficeAdd(String officeAdd) {
		this.officeAdd = officeAdd;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsCoo() {
		return isCoo;
	}

	public void setIsCoo(String isCoo) {
		this.isCoo = isCoo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}

	public String getIsRep() {
		return isRep;
	}

	public void setIsRep(String isRep) {
		this.isRep = isRep;
	}

	public Double getAsset1() {
		return asset1;
	}

	public void setAsset1(Double asset1) {
		this.asset1 = asset1;
	}

	public Double getAsset2() {
		return asset2;
	}

	public void setAsset2(Double asset2) {
		this.asset2 = asset2;
	}

	public Double getAsset3() {
		return asset3;
	}

	public void setAsset3(Double asset3) {
		this.asset3 = asset3;
	}

	public Double getAssetSum() {
		return assetSum;
	}

	public void setAssetSum(Double assetSum) {
		this.assetSum = assetSum;
	}

	public String getApproveFile() {
		return approveFile;
	}

	public void setApproveFile(String approveFile) {
		this.approveFile = approveFile;
	}

	public String getEntePro() {
		return entePro;
	}

	public void setEntePro(String entePro) {
		this.entePro = entePro;
	}


}