package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CustMemberModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 对象编号 */
	private String custId;

	/** 类型（1：基金，2：企业，3：管理公司） */
	private String orgnType;

	/** 所属组织 */
	private String orgnId;

	/** 姓名 */
	private String custName;

	/** 性别（0：不详，1：男，2：女） */
	private String gender;

	/** 职位 */
	private String position;

	/** 董监高成员标识（0：否，1：是，三位组合） */
	private String memberFlag;

	/** 国籍 */
	private String nationality;

	/** 学历 */
	private String education;

	/** 任职起始日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date officeStartDt;

	/** 离任日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date officeEndDt;

	/** 在职状态（0：正常,1：离职，9：删除） */
	private String status;

	/** 电话 */
	private String telNo;

	/** 手机 */
	private String phoneNo;

	/** 邮箱 */
	private String email;

	/** 是否元禾员工（0：否，1：是） */
	private String isYhStaff;

	/** 对应元禾内部员工号 */
	private Long mappingUserId;

	/** 备注 */
	private String remark;

	/** 是否主联系人（0：否，1：是） */
	private String isMainContact;

	/** 出资主体ID */
	private String inveId;

	/** 项目ID */
	private String projId;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getOrgnType() {
		return orgnType;
	}

	public void setOrgnType(String orgnType) {
		this.orgnType = orgnType;
	}

	public String getOrgnId() {
		return orgnId;
	}

	public void setOrgnId(String orgnId) {
		this.orgnId = orgnId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMemberFlag() {
		return memberFlag;
	}

	public void setMemberFlag(String memberFlag) {
		this.memberFlag = memberFlag;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getOfficeStartDt() {
		return officeStartDt;
	}

	public void setOfficeStartDt(Date officeStartDt) {
		this.officeStartDt = officeStartDt;
	}

	public Date getOfficeEndDt() {
		return officeEndDt;
	}

	public void setOfficeEndDt(Date officeEndDt) {
		this.officeEndDt = officeEndDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
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

	public String getIsYhStaff() {
		return isYhStaff;
	}

	public void setIsYhStaff(String isYhStaff) {
		this.isYhStaff = isYhStaff;
	}

	public Long getMappingUserId() {
		return mappingUserId;
	}

	public void setMappingUserId(Long mappingUserId) {
		this.mappingUserId = mappingUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsMainContact() {
		return isMainContact;
	}

	public void setIsMainContact(String isMainContact) {
		this.isMainContact = isMainContact;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

}