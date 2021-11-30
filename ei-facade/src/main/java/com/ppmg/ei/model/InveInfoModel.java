package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class InveInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 投资人ID */
	private String investorId;

	/** 投资人名称 */
	private String investorName;

	/** 投资人类型(1:个人，2:机构，3：政府) */
	private String investorType;
	private String investorTypeName;

	public String getInvestorTypeName() {
		return investorTypeName;
	}

	public void setInvestorTypeName(String investorTypeName) {
		this.investorTypeName = investorTypeName;
	}

	/** 机构属性（1:国企，2:民企，3：外企） */
	private String subType;

	/** 性别 */
	private String gender;

	/** 国籍 */
	private String nationality;

	/** 所属公司 */
	private String company;

	/** 职位 */
	private String position;

	/** 证件号码/组织机构代码 */
	private String idNo;

	/** 注册名称 */
	private String regName;

	/** 注册地点 */
	private String regAdd;

	/** 注册日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date regDate;

	/** 注册资本 */
	private Double regCapital;

	/** 注册资本币种 */
	private String regCurrency;

	/** 办公地址 */
	private String officeAdd;

	/** 办公电话 */
	private String officeTel;

	/** 联系人 */
	private String contact;

	/** 联系电话 */
	private String phoneNo;

	/** 邮箱 */
	private String email;

	/** 投资阶段 */
	private String invePhase;

	/** 所在区域 */
	private String area;

	/** 关注行业 */
	private String watchIndustry;

	/** 客户经理 */
	private String custMag;

	/** 开户行 */
	private String accountBank;

	/** 账号 */
	private String accountNo;

	/** 投资人状态（0：正常，1:删除，2：其他） */
	private String status;

	/** 所属公司ID */
	private Long groupId;

	/** 企业ID */
	private String enteId;

	private String enteNature;

	private String investmentAttributes;

	public String getEnteNature() {
		return enteNature;
	}

	public void setEnteNature(String enteNature) {
		this.enteNature = enteNature;
	}

	public String getInvestmentAttributes() {
		return investmentAttributes;
	}

	public void setInvestmentAttributes(String investmentAttributes) {
		this.investmentAttributes = investmentAttributes;
	}

	/*简介*/
	private String summary;
	/*备注*/
	private String remark;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getInvestorType() {
		return investorType;
	}

	public void setInvestorType(String investorType) {
		this.investorType = investorType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegAdd() {
		return regAdd;
	}

	public void setRegAdd(String regAdd) {
		this.regAdd = regAdd;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Double getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegCurrency() {
		return regCurrency;
	}

	public void setRegCurrency(String regCurrency) {
		this.regCurrency = regCurrency;
	}

	public String getOfficeAdd() {
		return officeAdd;
	}

	public void setOfficeAdd(String officeAdd) {
		this.officeAdd = officeAdd;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
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

	public String getInvePhase() {
		return invePhase;
	}

	public void setInvePhase(String invePhase) {
		this.invePhase = invePhase;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWatchIndustry() {
		return watchIndustry;
	}

	public void setWatchIndustry(String watchIndustry) {
		this.watchIndustry = watchIndustry;
	}

	public String getCustMag() {
		return custMag;
	}

	public void setCustMag(String custMag) {
		this.custMag = custMag;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}

}