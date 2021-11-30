package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjInfoDTO extends BaseDTO {

	/** 项目名称 */
	private String projName;

	/** 被投对象 */
	private String projObject;

	/** 被投对象名称 */
	private String projObjectName;

	/** 项目经理ID */
	private String userId;

	/** 项目经理Name */
	private String userName;

	/** 储备类型 */
	private String reserveType;

	/** 公司名称 */
	private String orgId;

	/** 项目类型 */
	private String projType;

	/** 项目状态 */
	private String projStatus;

	List<String> labels;

	private String projProperty;

	private String industryName;

	private String newIndustryName;

	private String isJs;

	private String  isTwelve;

	private String finaRounds;

	private String finaTimes;

	private Double  finaAmount;

	private String	inveRounds;

	private Double intendedAmount;

	private Double	actualAmount;

	private Double curTmoneyPer;

	private Double	allTmoney;

	private Double   funToPro;

	private Double 	allTmoneyPer;

	private Double   sumInveAmount;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date    actualPayDate;

	private Double  preMoney;

	private Double 		postMoney;

	private String inveType;

	private String 		inveRole;

	private String quitType;

	private Double revenue;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date  quitDt;

	private String appId;

	private String pqId;

	private String industry;

	private String newIndustry;

	private String inveId;

	private String InveName;


	private Double actualPayAmount;

	private String isDistribute;

	private Double distributeMoney;

	private Double distributeGovMoney;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date   distributeDt;

	private String isRegion;

	private String isRegister;

	private String isAfterListed;

	private String  industryCategory;

	private String  entePhase;

	public String getIsAfterListed() {
		return isAfterListed;
	}

	public void setIsAfterListed(String isAfterListed) {
		this.isAfterListed = isAfterListed;
	}

	public String getEntePhase() {
		return entePhase;
	}

	public void setEntePhase(String entePhase) {
		this.entePhase = entePhase;
	}

	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getIsRegion() {
		return isRegion;
	}

	public void setIsRegion(String isRegion) {
		this.isRegion = isRegion;
	}

	public String getIsDistribute() {
		return isDistribute;
	}

	public void setIsDistribute(String isDistribute) {
		this.isDistribute = isDistribute;
	}

	public Double getDistributeMoney() {
		return distributeMoney;
	}

	public void setDistributeMoney(Double distributeMoney) {
		this.distributeMoney = distributeMoney;
	}

	public Double getDistributeGovMoney() {
		return distributeGovMoney;
	}

	public void setDistributeGovMoney(Double distributeGovMoney) {
		this.distributeGovMoney = distributeGovMoney;
	}

	public Date getDistributeDt() {
		return distributeDt;
	}

	public void setDistributeDt(Date distributeDt) {
		this.distributeDt = distributeDt;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return InveName;
	}

	public void setInveName(String inveName) {
		InveName = inveName;
	}

	public Double getActualPayAmount() {
		return actualPayAmount;
	}

	public void setActualPayAmount(Double actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getNewIndustry() {
		return newIndustry;
	}

	public void setNewIndustry(String newIndustry) {
		this.newIndustry = newIndustry;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPqId() {
		return pqId;
	}

	public void setPqId(String pqId) {
		this.pqId = pqId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getNewIndustryName() {
		return newIndustryName;
	}

	public void setNewIndustryName(String newIndustryName) {
		this.newIndustryName = newIndustryName;
	}

	public String getIsJs() {
		return isJs;
	}

	public void setIsJs(String isJs) {
		this.isJs = isJs;
	}

	public String getIsTwelve() {
		return isTwelve;
	}

	public void setIsTwelve(String isTwelve) {
		this.isTwelve = isTwelve;
	}

	public String getFinaRounds() {
		return finaRounds;
	}

	public void setFinaRounds(String finaRounds) {
		this.finaRounds = finaRounds;
	}

	public String getFinaTimes() {
		return finaTimes;
	}

	public void setFinaTimes(String finaTimes) {
		this.finaTimes = finaTimes;
	}

	public Double getFinaAmount() {
		return finaAmount;
	}

	public void setFinaAmount(Double finaAmount) {
		this.finaAmount = finaAmount;
	}

	public String getInveRounds() {
		return inveRounds;
	}

	public void setInveRounds(String inveRounds) {
		this.inveRounds = inveRounds;
	}

	public Double getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(Double intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}



	public Double getAllTmoney() {
		return allTmoney;
	}

	public void setAllTmoney(Double allTmoney) {
		this.allTmoney = allTmoney;
	}

	public Double getCurTmoneyPer() {
		return curTmoneyPer;
	}

	public void setCurTmoneyPer(Double curTmoneyPer) {
		this.curTmoneyPer = curTmoneyPer;
	}

	public Double getFunToPro() {
		return funToPro;
	}

	public void setFunToPro(Double funToPro) {
		this.funToPro = funToPro;
	}

	public Double getAllTmoneyPer() {
		return allTmoneyPer;
	}

	public void setAllTmoneyPer(Double allTmoneyPer) {
		this.allTmoneyPer = allTmoneyPer;
	}

	public Double getSumInveAmount() {
		return sumInveAmount;
	}

	public void setSumInveAmount(Double sumInveAmount) {
		this.sumInveAmount = sumInveAmount;
	}

	public Date getActualPayDate() {
		return actualPayDate;
	}

	public void setActualPayDate(Date actualPayDate) {
		this.actualPayDate = actualPayDate;
	}

	public Double getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(Double preMoney) {
		this.preMoney = preMoney;
	}

	public Double getPostMoney() {
		return postMoney;
	}

	public void setPostMoney(Double postMoney) {
		this.postMoney = postMoney;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public String getInveRole() {
		return inveRole;
	}

	public void setInveRole(String inveRole) {
		this.inveRole = inveRole;
	}

	public String getQuitType() {
		return quitType;
	}

	public void setQuitType(String quitType) {
		this.quitType = quitType;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Date getQuitDt() {
		return quitDt;
	}

	public void setQuitDt(Date quitDt) {
		this.quitDt = quitDt;
	}

	public String getProjProperty() {
		return projProperty;
	}

	public void setProjProperty(String projProperty) {
		this.projProperty = projProperty;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjObject() {
		return projObject;
	}

	public void setProjObject(String projObject) {
		this.projObject = projObject;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
