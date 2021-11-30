package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class BdTFitRegulationSelfModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String projId;

	/** 基金ID */
	private String fundId;

	/** 本期投资金额 */
	private Double curTmoney;

	/** 基金对项目累计投资金额 */
	private Double allTmoney;

	/** 本期投资金额占基金认缴出资总额的比例 */
	private Double curTmoneyPer;

	/** 基金对项目累计投资金额占基金认缴出资总额的比例 */
	private Double allTmoneyPer;

	/** 基金对项目的累计出资比例 */
	private Double funToPro;

	/** 基金是否为最大出资人或股东 */
	private String isSholder;

	/** 项目所属行业/领域 */
	private String industry;

	/** 是否属于基金主投领域 */
	private String isMfield;

	/** 已完成投资项目中非主投领域项目合计投资金额 */
	private Double nmainTmoney;

	/** 已完成投资项目中非主投领域项目合计投资金额占本基金认缴出资总额的比例 */
	private Double nmainTmoneyPer;

	/** 项目是否属于江苏省内企业 */
	private String isJs;

	/** 项目属第12条江苏省内企业的何种情形 */
	private String isTwelve;

	/** 基金已完成投资项目属于情形（一）投资金额 */
	private Double tmoneyOne;

	/** 基金已完成投资项目属于情形（一）投资金额占本基金认缴出资总额的比例 */
	private Double tmoneyOnePer;

	/** 基金已完成投资项目属于情形（二）、（三）、（四）投资金额 */
	private Double tmoneyTtf;

	/** 基金已完成投资项目属于情形（二）、（三）、（四）投资金额占本基金认缴出资总额的比例 */
	private Double tmoneyTtfPer;

	/** 基金已完成对江苏省外企业投资项目的总投资金额 */
	private Double outjsTmoney;

	/** 基金已完成对江苏省外企业投资项目的总投资金额占本基金认缴出资总额的比例 */
	private Double outjsTmoneyPer;

	/** 是否关联交易 */
	private String isCorr;

	/** 关联关系说明 */
	private String corrExplain;

	/** 是否列入《政府核准的投资项目目录》 */
	private String isAllow;

	/** 属于《产业结构调整指导目录》何种类别 */
	private String whichClass;

	/** 是否列入《市场准入负面清单草案（试点版》 */
	private String isNlist;

	/** 是否属于第13条所列业务 */
	private String isThirteen;

	/** 有无其他违反本基金协议的情形 */
	private String anyNlist;

	/** 其他需要说明的情况 */
	private String explain;

	/** 自查结论 */
	private String selfConclusion;

	private String status;

	private ProjInfoModel projInfoModel;

	private ProjAppInfoModel projAppInfoModel;

	private FundInfoModel fundInfoModel;

	private String regulationFile;

	private Double secTmoneyPer;

	private Double thrTmoneyPer;

	private String processInstId;

	private String flowStatus;


	private String confirmLetterFile;

	private Double sumFileSignAmount;

	private Double sumXjlActualPayAmount;

	public Double getSumFileSignAmount() {
		return sumFileSignAmount;
	}

	public void setSumFileSignAmount(Double sumFileSignAmount) {
		this.sumFileSignAmount = sumFileSignAmount;
	}

	public Double getSumXjlActualPayAmount() {
		return sumXjlActualPayAmount;
	}

	public void setSumXjlActualPayAmount(Double sumXjlActualPayAmount) {
		this.sumXjlActualPayAmount = sumXjlActualPayAmount;
	}

	public String getConfirmLetterFile() {
		return confirmLetterFile;
	}

	public void setConfirmLetterFile(String confirmLetterFile) {
		this.confirmLetterFile = confirmLetterFile;
	}

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public Double getSecTmoneyPer() {
		return secTmoneyPer;
	}

	public void setSecTmoneyPer(Double secTmoneyPer) {
		this.secTmoneyPer = secTmoneyPer;
	}

	public Double getThrTmoneyPer() {
		return thrTmoneyPer;
	}

	public void setThrTmoneyPer(Double thrTmoneyPer) {
		this.thrTmoneyPer = thrTmoneyPer;
	}

	public String getRegulationFile() {
		return regulationFile;
	}

	public void setRegulationFile(String regulationFile) {
		this.regulationFile = regulationFile;
	}

	public FundInfoModel getFundInfoModel() {
		return fundInfoModel;
	}

	public void setFundInfoModel(FundInfoModel fundInfoModel) {
		this.fundInfoModel = fundInfoModel;
	}

	public ProjAppInfoModel getProjAppInfoModel() {
		return projAppInfoModel;
	}

	public void setProjAppInfoModel(ProjAppInfoModel projAppInfoModel) {
		this.projAppInfoModel = projAppInfoModel;
	}

	public ProjInfoModel getProjInfoModel() {
		return projInfoModel;
	}

	public void setProjInfoModel(ProjInfoModel projInfoModel) {
		this.projInfoModel = projInfoModel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Double getCurTmoney() {
		return curTmoney;
	}

	public void setCurTmoney(Double curTmoney) {
		this.curTmoney = curTmoney;
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

	public Double getAllTmoneyPer() {
		return allTmoneyPer;
	}

	public void setAllTmoneyPer(Double allTmoneyPer) {
		this.allTmoneyPer = allTmoneyPer;
	}

	public Double getFunToPro() {
		return funToPro;
	}

	public void setFunToPro(Double funToPro) {
		this.funToPro = funToPro;
	}

	public String getIsSholder() {
		return isSholder;
	}

	public void setIsSholder(String isSholder) {
		this.isSholder = isSholder;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIsMfield() {
		return isMfield;
	}

	public void setIsMfield(String isMfield) {
		this.isMfield = isMfield;
	}

	public Double getNmainTmoney() {
		return nmainTmoney;
	}

	public void setNmainTmoney(Double nmainTmoney) {
		this.nmainTmoney = nmainTmoney;
	}

	public Double getNmainTmoneyPer() {
		return nmainTmoneyPer;
	}

	public void setNmainTmoneyPer(Double nmainTmoneyPer) {
		this.nmainTmoneyPer = nmainTmoneyPer;
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

	public Double getTmoneyOne() {
		return tmoneyOne;
	}

	public void setTmoneyOne(Double tmoneyOne) {
		this.tmoneyOne = tmoneyOne;
	}

	public Double getTmoneyOnePer() {
		return tmoneyOnePer;
	}

	public void setTmoneyOnePer(Double tmoneyOnePer) {
		this.tmoneyOnePer = tmoneyOnePer;
	}

	public Double getTmoneyTtf() {
		return tmoneyTtf;
	}

	public void setTmoneyTtf(Double tmoneyTtf) {
		this.tmoneyTtf = tmoneyTtf;
	}

	public Double getTmoneyTtfPer() {
		return tmoneyTtfPer;
	}

	public void setTmoneyTtfPer(Double tmoneyTtfPer) {
		this.tmoneyTtfPer = tmoneyTtfPer;
	}

	public Double getOutjsTmoney() {
		return outjsTmoney;
	}

	public void setOutjsTmoney(Double outjsTmoney) {
		this.outjsTmoney = outjsTmoney;
	}

	public Double getOutjsTmoneyPer() {
		return outjsTmoneyPer;
	}

	public void setOutjsTmoneyPer(Double outjsTmoneyPer) {
		this.outjsTmoneyPer = outjsTmoneyPer;
	}

	public String getIsCorr() {
		return isCorr;
	}

	public void setIsCorr(String isCorr) {
		this.isCorr = isCorr;
	}

	public String getCorrExplain() {
		return corrExplain;
	}

	public void setCorrExplain(String corrExplain) {
		this.corrExplain = corrExplain;
	}

	public String getIsAllow() {
		return isAllow;
	}

	public void setIsAllow(String isAllow) {
		this.isAllow = isAllow;
	}

	public String getWhichClass() {
		return whichClass;
	}

	public void setWhichClass(String whichClass) {
		this.whichClass = whichClass;
	}

	public String getIsNlist() {
		return isNlist;
	}

	public void setIsNlist(String isNlist) {
		this.isNlist = isNlist;
	}

	public String getIsThirteen() {
		return isThirteen;
	}

	public void setIsThirteen(String isThirteen) {
		this.isThirteen = isThirteen;
	}

	public String getAnyNlist() {
		return anyNlist;
	}

	public void setAnyNlist(String anyNlist) {
		this.anyNlist = anyNlist;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getSelfConclusion() {
		return selfConclusion;
	}

	public void setSelfConclusion(String selfConclusion) {
		this.selfConclusion = selfConclusion;
	}

}