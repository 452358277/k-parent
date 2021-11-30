package com.ppmg.ei.model;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 

public class ExpertModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 专家ID */
	private String expertId;

	/** 编号 */
	private String expertNo;

	/** 姓名 */
	private String expertName;

	/** 性别 */
	private String sex;

	/** 出生年月 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;

	/** 毕业院校 */
	private String university;

	/** 学历 */
	private String education;

	/** 专业 */
	private String major;

	/** 单位名称 */
	private String company;

	/** 职务 */
	private String position;

	/** 通信地址 */
	private String contractAddr;

	/** 技术职称 */
	private String techTitle;

	/** 单位电话 */
	private String companyPhone;

	/** 传真 */
	private String fax;

	/** Email */
	private String email;

	/** 手机 */
	private String mobilePhone;

	/** 受教育经历 */
	private String educationDetail;

	/** 工作履历 */
	private String workDetail;

	/** 主要业绩 */
	private String performance;

	/** 曾参与过的项目决策 */
	private String projDetail;

	/** 专长领域 */
	private String speDetail;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}

	public String getExpertNo() {
		return expertNo;
	}

	public void setExpertNo(String expertNo) {
		this.expertNo = expertNo;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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

	public String getContractAddr() {
		return contractAddr;
	}

	public void setContractAddr(String contractAddr) {
		this.contractAddr = contractAddr;
	}

	public String getTechTitle() {
		return techTitle;
	}

	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEducationDetail() {
		return educationDetail;
	}

	public void setEducationDetail(String educationDetail) {
		this.educationDetail = educationDetail;
	}

	public String getWorkDetail() {
		return workDetail;
	}

	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getProjDetail() {
		return projDetail;
	}

	public void setProjDetail(String projDetail) {
		this.projDetail = projDetail;
	}

	public String getSpeDetail() {
		return speDetail;
	}

	public void setSpeDetail(String speDetail) {
		this.speDetail = speDetail;
	}
}