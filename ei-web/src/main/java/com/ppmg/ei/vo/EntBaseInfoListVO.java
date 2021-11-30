package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EntBaseInfoListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String enterpriseId;

	/** 是否境外企业（0：否，1：是） */
	private String isAbroad;

	/** 登记状态（存续，在业，注销，迁入，吊销，迁出，停业，清算，未注册） */
	private String status;

	/** 统一社会信用代码 */
	private String creditCode;

	/** 企查查内部代码 */
	private String keyNo;

	/** 企业全称 */
	private String name;

	/** 登记机关 */
	private String belongOrg;

	/** 法定代表人 */
	private String operName;

	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 注销/吊销日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 所在省份缩写 */
	private String privince;

	/** 注册资本 */
	private String registCapi;

	/** 注册金额 */
	private Double registAmount;

	/** 注册资本币种 */
	private String currency;

	/** 类型（1：个人独资，2：合伙企业，3：公司企业） */
	private String econKind;

	/** 住所省 */
	private String addressProvince;

	/** 住所市 */
	private String addressCity;

	/** 住所区 */
	private String addressArea;

	/** 住所详细地址 */
	private String addressDetails;

	/** 经营范围 */
	private String scope;

	/** 经营期限自 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date termStart;

	/** 经营期限至 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date termEnd;

	/** 核准日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date checkDate;

	/** 组织机构代码 */
	private String orgCode;

	/** 公司简介 */
	private String companyProfile;

	/** 公司简介附件 */
	private String profileFile;

	/** 股票代码 */
	private String stockCode;

	/** 股票名称 */
	private String stockName;

	/** 英文名 */
	private String enName;

	/** 清科行业 */
	private String qkIndustry;

	/** 清科行业-汉字名称 */
	private String qkIndustryNam;

	/** 七大行业 */
	private String sevenIndustry;

	/** 运营主体 */
	private String operation;

	/** 联系人 */
	private String contact;

	/** 联系人电话 */
	private String contactTel;

	/** 是否上市公司（0：否，1：是） */
	private String isPublicComp;

	/** 创建所属公司 */
	private String createOrg;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	/**实缴资本**/
	private String recCap;

	/**实缴资本金额**/
	private Double recCapAmount;

	/**实缴币种*/
	private String recCapCurrency;

	public String getRecCap() {
		return recCap;
	}

	public void setRecCap(String recCap) {
		this.recCap = recCap;
	}

	public Double getRecCapAmount() {
		return recCapAmount;
	}

	public void setRecCapAmount(Double recCapAmount) {
		this.recCapAmount = recCapAmount;
	}

	public String getRecCapCurrency() {
		return recCapCurrency;
	}

	public void setRecCapCurrency(String recCapCurrency) {
		this.recCapCurrency = recCapCurrency;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(String isAbroad) {
		this.isAbroad = isAbroad;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getKeyNo() {
		return keyNo;
	}

	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(String belongOrg) {
		this.belongOrg = belongOrg;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEconKind() {
		return econKind;
	}

	public void setEconKind(String econKind) {
		this.econKind = econKind;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getTermStart() {
		return termStart;
	}

	public void setTermStart(Date termStart) {
		this.termStart = termStart;
	}

	public Date getTermEnd() {
		return termEnd;
	}

	public void setTermEnd(Date termEnd) {
		this.termEnd = termEnd;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	public String getProfileFile() {
		return profileFile;
	}

	public void setProfileFile(String profileFile) {
		this.profileFile = profileFile;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getQkIndustry() {
		return qkIndustry;
	}

	public void setQkIndustry(String qkIndustry) {
		this.qkIndustry = qkIndustry;
	}

	public String getQkIndustryNam() {
		return qkIndustryNam;
	}

	public void setQkIndustryNam(String qkIndustryNam) {
		this.qkIndustryNam = qkIndustryNam;
	}

	public String getSevenIndustry() {
		return sevenIndustry;
	}

	public void setSevenIndustry(String sevenIndustry) {
		this.sevenIndustry = sevenIndustry;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	public String getIsPublicComp() {
		return isPublicComp;
	}

	public void setIsPublicComp(String isPublicComp) {
		this.isPublicComp = isPublicComp;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}