package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppEntBaseInfoListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String enterpriseId;

	/** 公司简介 */
	private String companyProfile;

	/** 当前运营情况 */
	private String currentOperationSituation;

	/** 未来前景及亮点 */
	private String inveEvaluation;

	/** 元禾投资情况 */
	private String InvestmentSituationYH;

	/** 企业简称 */
	private String enName;

	/** 法定代表人 */
	private String operName;

	/** 七大行业ID */
	private String sevenIndustry;

	/** 七大行业name */
	private String sevenIndustryName;

	/** 所在地 */
	private String addressDetails;

	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 注册金额 */
	private String registCapi;

	/** 注册金额数字 */
	private Double registAmount;

	/** 统一社会信用代码 */
	private String creditCode;

	/** 联系人 */
	private String contact;

	/** 联系人电话 */
	private String contactTel;

	/** 出资主体+被投对象的邮件 */
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	public String getCurrentOperationSituation() {
		return currentOperationSituation;
	}

	public void setCurrentOperationSituation(String currentOperationSituation) {
		this.currentOperationSituation = currentOperationSituation;
	}

	public String getInveEvaluation() {
		return inveEvaluation;
	}

	public void setInveEvaluation(String inveEvaluation) {
		this.inveEvaluation = inveEvaluation;
	}

	public String getInvestmentSituationYH() {
		return InvestmentSituationYH;
	}

	public void setInvestmentSituationYH(String investmentSituationYH) {
		InvestmentSituationYH = investmentSituationYH;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getSevenIndustry() {
		return sevenIndustry;
	}

	public void setSevenIndustry(String sevenIndustry) {
		this.sevenIndustry = sevenIndustry;
	}

	public String getSevenIndustryName() {
		return sevenIndustryName;
	}

	public void setSevenIndustryName(String sevenIndustryName) {
		this.sevenIndustryName = sevenIndustryName;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getRegistCapi() {
		return registCapi;
	}

	public void setRegistCapi(String registCapi) {
		this.registCapi = registCapi;
	}

	public Double getRegistAmount() {
		return registAmount;
	}

	public void setRegistAmount(Double registAmount) {
		this.registAmount = registAmount;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
}