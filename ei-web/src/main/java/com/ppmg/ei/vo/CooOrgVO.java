package com.ppmg.ei.vo;


import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;

/** 
 * 合作方机构库
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public class CooOrgVO extends BaseVO {


	/** 合作方机构ID */
	private String orgId;

	/** 机构名称 */
	private String orgName;

	/** 机构类型 */
	private String orgType;

	/** 地址 */
	private String orgAdd;

	/** 联系方式 */
	private String phoneNo;

	/** 机构代码 */
	private String orgCode;

	/** 联系人 */
	private String contact;

	/** 传真 */
	private String fax;

	/** 排序 */
	private String orderNo;

	/** 备注 */
	private String remark;

	/** 邮箱 */
	private String contactEmail;

	/** 资质 */
	private String qualification;

	/** 成功案例 */
	private String succCase;

	/** 企业荣誉 */
	private String honor;

	private String orgTypeName;

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgAdd() {
		return orgAdd;
	}

	public void setOrgAdd(String orgAdd) {
		this.orgAdd = orgAdd;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSuccCase() {
		return succCase;
	}

	public void setSuccCase(String succCase) {
		this.succCase = succCase;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}
}