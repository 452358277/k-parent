package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String projId;

	/** 项目编号 */
	private String projNo;

	/** 项目名称 */
	private String projName;

	/** 项目类型（1：投企业，2：投基金，3:子基金项目） */
	private String projType;

	/** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatus;

	/** 被投对象 */
	private String projObject;

	/** 被投对象名称 */
	private String projObjectName;

	/** 所投企业当前所属阶段 */
	private String entePhase;

	/** 项目来源 */
	private String projSrc;

	/** 来源说明 */
	private String srcDesc;

	/** 出资主体编号 */
	private String inveId;

	/** 出资主体名称 */
	private String inveName;

	/** 出资主体类型（1：基金，2：直投部门） */
	private String inveType;

	/** 分管合伙人 */
	private String chargePartner;

	/** 保密等级 */
	private String secrecyLevel;

	/** 附件 */
	private String inveFile;

	/** 项目立项时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date setupDt;

	/** 投资决策时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date decisionDt;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date upDt;

	/** 项目所属公司ID */
	private Long groupId;

	/** 项目所属公司NAME */
	private String groupName;

	/** 流程实例号 */
	private String processInstId;

	/** null */
	private String projGuid;

	/** 是否为投平台项目（0：否，1：是） */
	private String isPlat;

	/** 立项暂缓（1：表示暂缓:空表示无暂缓） */
	private String pauseApply;

	/** 决策暂缓（1：表示暂缓:空表示无暂缓） */
	private String pauseDecision;

	/** 项目概况 */
	private String projOverview;

	/** 投资经理对项目评价 */
	private String inveEvaluation;

	/** 备注 */
	private String remark;

	/** 附件说明 */
	private String fileDesc;

	/** 决策事项/董监事安排 */
	private String majorMatter;

	/** 项目概况附件 */
	private String projOverviewAtt;

	/** 投资经理对项目评价附件 */
	private String inveEvaluationAtt;

	/** 备注附件 */
	private String remarkAtt;

	/** 决策事项/董监事安排附件 */
	private String majorMatterAtt;

	/** 质量评估评级结果 */
	private Long scoreResult;

	/** 所在区域 */
	private String area;

	/** 邮箱 */
	private String email;

	/** 邮箱密码 */
	private String emailPassword;

	/** 出资主体社会信用代码 */
	private String inveCreditCode;

	/** 母基金名称 */
	private String pefofName;

	/** 母基金社会信用代码 */
	private String pefofCreditCode;

	/** 原项目所属公司ID */
	private Long groupIdOld;

	/** 批量立项标志 */
	private String investTogether;

	/** 咨询委员会成员 */
	private String advisoryCommId;

	/** 咨询委员会成员姓名 */
	private String advisoryCommName;

	/** 商业计划书附件 */
	private String bizPlanAtt;

	/** 立项报告附件 */
	private String projReportAtt;

	/** 其他附件 */
	private String otherAtt;

	private String subFundPlanAmount;

	private String subFundPlanAmountCurr;

	private String subFundPlanAmountRmb;

	private String currentOperationSituation;

	private String reserveType;

	/** 当前子基金规模 */
	private String subFundAmontDisplay;

	/** 认缴金额 */
	private String currentAmountDisplay;

	/** 累计出资 */
	private String allRequestMoney;

	/** 退出出资 */
	private String quitMoney;

	/** 最新占比 */
	private String currentRatio;

	/** 协议金额 */
	private String signAmountDisplay;

   /**是否上合伙人会议**/
	private String	isMeeting;

/**项目性质（码值517）**/
	private String projProperty;

	private String uniqueCoding ;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date instorageDt;
	/**管理人**/
	private String projMc;

    /**所属行业名称**/
	private String industryName;
	/**所属行业**/
	private String industry;
	/**战略新兴产业**/
	private String newIndustry;
	/**战略新兴产业名称**/
	private String newIndustryName;
	/**是否spv基金投项目**/
	private String isSpvFundId;
	/**SPV基金名称**/
	private String isSpvFundName;
	/**是否SPV基金**/
	private String isSpvProj;

	private String spvType;

	private String creditCode;

	private String status;

	private Double amount;

	private String isJs;

	private String isCorr;

	private String isSpv;

	private String inveRounds;

	private String isRegister;

	private String twoFundName;

	private String threeFundName;

	private String entName;

	private String isRegion;

	private String oldEnterpriseId;

	private String enterpriseIdStr;

	private EnteInfoModel  enteInfoModel;

	private String isAfterListed;

	private String industryCategory;


	private String addressDetails;


	private String oneIndustry;

	/** 住所省 */
	private String province;

	/** 住所市 */
	private String city;

	/** 住所区 */
	private String addArea;



	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddArea() {
		return addArea;
	}

	public void setAddArea(String addArea) {
		this.addArea = addArea;
	}

	public String getOneIndustry() {
		return oneIndustry;
	}

	public void setOneIndustry(String oneIndustry) {
		this.oneIndustry = oneIndustry;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	public String getIsAfterListed() {
		return isAfterListed;
	}

	public void setIsAfterListed(String isAfterListed) {
		this.isAfterListed = isAfterListed;
	}

	public Date getUpDt() {
		return upDt;
	}

	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}

	public EnteInfoModel getEnteInfoModel() {
		return enteInfoModel;
	}

	public void setEnteInfoModel(EnteInfoModel enteInfoModel) {
		this.enteInfoModel = enteInfoModel;
	}

	public String getOldEnterpriseId() {
		return oldEnterpriseId;
	}

	public void setOldEnterpriseId(String oldEnterpriseId) {
		this.oldEnterpriseId = oldEnterpriseId;
	}

	public String getEnterpriseIdStr() {
		return enterpriseIdStr;
	}

	public void setEnterpriseIdStr(String enterpriseIdStr) {
		this.enterpriseIdStr = enterpriseIdStr;
	}

	public String getIsRegion() {
		return isRegion;
	}

	public void setIsRegion(String isRegion) {
		this.isRegion = isRegion;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getTwoFundName() {
		return twoFundName;
	}

	public void setTwoFundName(String twoFundName) {
		this.twoFundName = twoFundName;
	}

	public String getThreeFundName() {
		return threeFundName;
	}

	public void setThreeFundName(String threeFundName) {
		this.threeFundName = threeFundName;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getInveRounds() {
		return inveRounds;
	}

	public void setInveRounds(String inveRounds) {
		this.inveRounds = inveRounds;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getIsSpv() {
		return isSpv;
	}

	public void setIsSpv(String isSpv) {
		this.isSpv = isSpv;
	}

	public String getIsCorr() {
		return isCorr;
	}

	public void setIsCorr(String isCorr) {
		this.isCorr = isCorr;
	}

	public String getIsJs() {
		return isJs;
	}

	public void setIsJs(String isJs) {
		this.isJs = isJs;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpvType() {
		return spvType;
	}

	public void setSpvType(String spvType) {
		this.spvType = spvType;
	}

	public String getIsSpvProj() {
		return isSpvProj;
	}

	public void setIsSpvProj(String isSpvProj) {
		this.isSpvProj = isSpvProj;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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

	public String getNewIndustryName() {
		return newIndustryName;
	}

	public void setNewIndustryName(String newIndustryName) {
		this.newIndustryName = newIndustryName;
	}

	public String getIsSpvFundId() {
		return isSpvFundId;
	}

	public void setIsSpvFundId(String isSpvFundId) {
		this.isSpvFundId = isSpvFundId;
	}

	public String getIsSpvFundName() {
		return isSpvFundName;
	}

	public void setIsSpvFundName(String isSpvFundName) {
		this.isSpvFundName = isSpvFundName;
	}

	public String getProjMc() {
		return projMc;
	}

	public void setProjMc(String projMc) {
		this.projMc = projMc;
	}

	public String getUniqueCoding() {
		return uniqueCoding;
	}

	public void setUniqueCoding(String uniqueCoding) {
		this.uniqueCoding = uniqueCoding;
	}

	public Date getInstorageDt() {
		return instorageDt;
	}

	public void setInstorageDt(Date instorageDt) {
		this.instorageDt = instorageDt;
	}


	public String getIsMeeting() {
		return isMeeting;
	}

	public void setIsMeeting(String isMeeting) {
		this.isMeeting = isMeeting;
	}

	public String getProjProperty() {
		return projProperty;
	}

	public void setProjProperty(String projProperty) {
		this.projProperty = projProperty;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSignAmountDisplay() {
		return signAmountDisplay;
	}

	public void setSignAmountDisplay(String signAmountDisplay) {
		this.signAmountDisplay = signAmountDisplay;
	}

	public String getAllRequestMoney() {
		return allRequestMoney;
	}

	public void setAllRequestMoney(String allRequestMoney) {
		this.allRequestMoney = allRequestMoney;
	}

	public String getQuitMoney() {
		return quitMoney;
	}

	public void setQuitMoney(String quitMoney) {
		this.quitMoney = quitMoney;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public String getSubFundAmontDisplay() {
		return subFundAmontDisplay;
	}

	public void setSubFundAmontDisplay(String subFundAmontDisplay) {
		this.subFundAmontDisplay = subFundAmontDisplay;
	}

	public String getCurrentAmountDisplay() {
		return currentAmountDisplay;
	}

	public void setCurrentAmountDisplay(String currentAmountDisplay) {
		this.currentAmountDisplay = currentAmountDisplay;
	}

	public String getSubFundPlanAmount() {
		return subFundPlanAmount;
	}

	public void setSubFundPlanAmount(String subFundPlanAmount) {
		this.subFundPlanAmount = subFundPlanAmount;
	}

	public String getSubFundPlanAmountCurr() {
		return subFundPlanAmountCurr;
	}

	public void setSubFundPlanAmountCurr(String subFundPlanAmountCurr) {
		this.subFundPlanAmountCurr = subFundPlanAmountCurr;
	}

	public String getSubFundPlanAmountRmb() {
		return subFundPlanAmountRmb;
	}

	public void setSubFundPlanAmountRmb(String subFundPlanAmountRmb) {
		this.subFundPlanAmountRmb = subFundPlanAmountRmb;
	}

	public String getCurrentOperationSituation() {
		return currentOperationSituation;
	}

	public void setCurrentOperationSituation(String currentOperationSituation) {
		this.currentOperationSituation = currentOperationSituation;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	private ProjAppInfoModel projAppInfoModel;

	private ProjMemberModel mangerModel;

	private List<ProjMemberModel> memberList;

	private List<EiTAttachmentModel> bizPlanAttList;

	private List<EiTAttachmentModel> projReportAttList;

	private List<EiTAttachmentModel> otherAttList;

	private List<EiTAttachmentModel> remarkAttList;

	private List<EiTAttachmentModel> decisionAttList;

	private  List<CommTLabelItemModel> listcommT;

	private  List<FundInfoModel> fundInfoModel;

	private  FundInfoModel fundInfo;

	private List<DirectProjResult> directProjResult;

	private BdTFitRegulationSelfModel bdTFitRegulationSelfModel;

	private String investAmountDisplay;

	private String projMembers;

	/** 决策金额 */
	private String intendedAmount;

	/** 协议金额 */
	private String signAmount;

	/** 协议日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date signDt;

	private String mcName;

	private String sumInveAmount;

	private ResultModel resultModel;

	private ProjQuitApplModel projQuitApplModel;

	private EntBaseInfoModel entBaseInfoModel;

	private XjlTPaymentRequestModel xjlTPaymentRequestModel;

	public List<CommTLabelItemModel> getListcommT() {
		return listcommT;
	}

	public void setListcommT(List<CommTLabelItemModel> listcommT) {
		this.listcommT = listcommT;
	}

	public XjlTPaymentRequestModel getXjlTPaymentRequestModel() {
		return xjlTPaymentRequestModel;
	}

	public void setXjlTPaymentRequestModel(XjlTPaymentRequestModel xjlTPaymentRequestModel) {
		this.xjlTPaymentRequestModel = xjlTPaymentRequestModel;
	}

	public EntBaseInfoModel getEntBaseInfoModel() {
		return entBaseInfoModel;
	}

	public void setEntBaseInfoModel(EntBaseInfoModel entBaseInfoModel) {
		this.entBaseInfoModel = entBaseInfoModel;
	}

	public ProjQuitApplModel getProjQuitApplModel() {
		return projQuitApplModel;
	}

	public void setProjQuitApplModel(ProjQuitApplModel projQuitApplModel) {
		this.projQuitApplModel = projQuitApplModel;
	}

	public BdTFitRegulationSelfModel getBdTFitRegulationSelfModel() {
		return bdTFitRegulationSelfModel;
	}

	public void setBdTFitRegulationSelfModel(BdTFitRegulationSelfModel bdTFitRegulationSelfModel) {
		this.bdTFitRegulationSelfModel = bdTFitRegulationSelfModel;
	}

	public FundInfoModel getFundInfo() {
		return fundInfo;
	}

	public void setFundInfo(FundInfoModel fundInfo) {
		this.fundInfo = fundInfo;
	}

	public List<DirectProjResult> getDirectProjResult() {
		return directProjResult;
	}

	public void setDirectProjResult(List<DirectProjResult> directProjResult) {
		this.directProjResult = directProjResult;
	}

	public List<FundInfoModel> getFundInfoModel() {
		return fundInfoModel;
	}

	public void setFundInfoModel(List<FundInfoModel> fundInfoModel) {
		this.fundInfoModel = fundInfoModel;
	}

	public ResultModel getResultModel() {
		return resultModel;
	}

	public void setResultModel(ResultModel resultModel) {
		this.resultModel = resultModel;
	}

	public String getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(String intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getSignAmount() {
		return signAmount;
	}

	public void setSignAmount(String signAmount) {
		this.signAmount = signAmount;
	}

	public Date getSignDt() {
		return signDt;
	}

	public void setSignDt(Date signDt) {
		this.signDt = signDt;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
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

	public String getEntePhase() {
		return entePhase;
	}

	public void setEntePhase(String entePhase) {
		this.entePhase = entePhase;
	}

	public String getProjSrc() {
		return projSrc;
	}

	public void setProjSrc(String projSrc) {
		this.projSrc = projSrc;
	}

	public String getSrcDesc() {
		return srcDesc;
	}

	public void setSrcDesc(String srcDesc) {
		this.srcDesc = srcDesc;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public String getChargePartner() {
		return chargePartner;
	}

	public void setChargePartner(String chargePartner) {
		this.chargePartner = chargePartner;
	}

	public String getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(String secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getInveFile() {
		return inveFile;
	}

	public void setInveFile(String inveFile) {
		this.inveFile = inveFile;
	}

	public Date getSetupDt() {
		return setupDt;
	}

	public void setSetupDt(Date setupDt) {
		this.setupDt = setupDt;
	}

	public Date getDecisionDt() {
		return decisionDt;
	}

	public void setDecisionDt(Date decisionDt) {
		this.decisionDt = decisionDt;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getIsPlat() {
		return isPlat;
	}

	public void setIsPlat(String isPlat) {
		this.isPlat = isPlat;
	}

	public String getPauseApply() {
		return pauseApply;
	}

	public void setPauseApply(String pauseApply) {
		this.pauseApply = pauseApply;
	}

	public String getPauseDecision() {
		return pauseDecision;
	}

	public void setPauseDecision(String pauseDecision) {
		this.pauseDecision = pauseDecision;
	}

	public String getProjOverview() {
		return projOverview;
	}

	public void setProjOverview(String projOverview) {
		this.projOverview = projOverview;
	}

	public String getInveEvaluation() {
		return inveEvaluation;
	}

	public void setInveEvaluation(String inveEvaluation) {
		this.inveEvaluation = inveEvaluation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getMajorMatter() {
		return majorMatter;
	}

	public void setMajorMatter(String majorMatter) {
		this.majorMatter = majorMatter;
	}

	public String getProjOverviewAtt() {
		return projOverviewAtt;
	}

	public void setProjOverviewAtt(String projOverviewAtt) {
		this.projOverviewAtt = projOverviewAtt;
	}

	public String getInveEvaluationAtt() {
		return inveEvaluationAtt;
	}

	public void setInveEvaluationAtt(String inveEvaluationAtt) {
		this.inveEvaluationAtt = inveEvaluationAtt;
	}

	public String getRemarkAtt() {
		return remarkAtt;
	}

	public void setRemarkAtt(String remarkAtt) {
		this.remarkAtt = remarkAtt;
	}

	public String getMajorMatterAtt() {
		return majorMatterAtt;
	}

	public void setMajorMatterAtt(String majorMatterAtt) {
		this.majorMatterAtt = majorMatterAtt;
	}

	public Long getScoreResult() {
		return scoreResult;
	}

	public void setScoreResult(Long scoreResult) {
		this.scoreResult = scoreResult;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getInveCreditCode() {
		return inveCreditCode;
	}

	public void setInveCreditCode(String inveCreditCode) {
		this.inveCreditCode = inveCreditCode;
	}

	public String getPefofName() {
		return pefofName;
	}

	public void setPefofName(String pefofName) {
		this.pefofName = pefofName;
	}

	public String getPefofCreditCode() {
		return pefofCreditCode;
	}

	public void setPefofCreditCode(String pefofCreditCode) {
		this.pefofCreditCode = pefofCreditCode;
	}

	public Long getGroupIdOld() {
		return groupIdOld;
	}

	public void setGroupIdOld(Long groupIdOld) {
		this.groupIdOld = groupIdOld;
	}

	public String getInvestTogether() {
		return investTogether;
	}

	public void setInvestTogether(String investTogether) {
		this.investTogether = investTogether;
	}

	public String getAdvisoryCommId() {
		return advisoryCommId;
	}

	public void setAdvisoryCommId(String advisoryCommId) {
		this.advisoryCommId = advisoryCommId;
	}

	public String getAdvisoryCommName() {
		return advisoryCommName;
	}

	public void setAdvisoryCommName(String advisoryCommName) {
		this.advisoryCommName = advisoryCommName;
	}

	public String getBizPlanAtt() {
		return bizPlanAtt;
	}

	public void setBizPlanAtt(String bizPlanAtt) {
		this.bizPlanAtt = bizPlanAtt;
	}

	public String getProjReportAtt() {
		return projReportAtt;
	}

	public ProjAppInfoModel getProjAppInfoModel() {
		return projAppInfoModel;
	}

	public void setProjAppInfoModel(ProjAppInfoModel projAppInfoModel) {
		this.projAppInfoModel = projAppInfoModel;
	}

	public void setProjReportAtt(String projReportAtt) {
		this.projReportAtt = projReportAtt;
	}

	public String getOtherAtt() {
		return otherAtt;
	}

	public void setOtherAtt(String otherAtt) {
		this.otherAtt = otherAtt;
	}

	public List<ProjMemberModel> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<ProjMemberModel> memberList) {
		this.memberList = memberList;
	}

	public ProjMemberModel getMangerModel() {
		return mangerModel;
	}

	public void setMangerModel(ProjMemberModel mangerModel) {
		this.mangerModel = mangerModel;
	}

	public List<EiTAttachmentModel> getBizPlanAttList() {
		return bizPlanAttList;
	}

	public void setBizPlanAttList(List<EiTAttachmentModel> bizPlanAttList) {
		this.bizPlanAttList = bizPlanAttList;
	}

	public List<EiTAttachmentModel> getProjReportAttList() {
		return projReportAttList;
	}

	public void setProjReportAttList(List<EiTAttachmentModel> projReportAttList) {
		this.projReportAttList = projReportAttList;
	}

	public List<EiTAttachmentModel> getOtherAttList() {
		return otherAttList;
	}

	public void setOtherAttList(List<EiTAttachmentModel> otherAttList) {
		this.otherAttList = otherAttList;
	}

	public List<EiTAttachmentModel> getRemarkAttList() {
		return remarkAttList;
	}

	public void setRemarkAttList(List<EiTAttachmentModel> remarkAttList) {
		this.remarkAttList = remarkAttList;
	}

	public List<EiTAttachmentModel> getDecisionAttList() {
		return decisionAttList;
	}

	public void setDecisionAttList(List<EiTAttachmentModel> decisionAttList) {
		this.decisionAttList = decisionAttList;
	}

	public String getInvestAmountDisplay() {
		return investAmountDisplay;
	}

	public void setInvestAmountDisplay(String investAmountDisplay) {
		this.investAmountDisplay = investAmountDisplay;
	}

	public String getProjMembers() {
		return projMembers;
	}

	public void setProjMembers(String projMembers) {
		this.projMembers = projMembers;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getSumInveAmount() {
		return sumInveAmount;
	}

	public void setSumInveAmount(String sumInveAmount) {
		this.sumInveAmount = sumInveAmount;
	}
}