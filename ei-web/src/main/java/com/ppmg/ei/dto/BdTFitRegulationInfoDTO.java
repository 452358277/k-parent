package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BdTFitRegulationInfoDTO extends BaseDTO {

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


    /** 延长期管理费 */
    private Double mgtFeeRate;

    /** 管理费说明 */
    private String mgtFeeDesc;

    /** 投资期(年) */
    private String invePeriod;

    /** 回收期(年) */
    private String paybackPeriod;

    /** 投资期延长 */
    private String rollOver;

    /** 存续期说明 */
    private String durationDesc;

    /** 门槛收益率 */
    private Double hurdleRate;

    /** 超额分配比例-有限合伙人比例 */
    private Double performanceFee;

    /** 收益分配说明 */
    private String incomeDistDesc;

    /** 投资方向及目标 */
    private String inveStrategy;

    /** 投资标准 */
    private String inveStandard;

    /** 业绩报酬提取方式 */
    private String payMethod;

    /** 备注 */
    private String remark;

    /** 投资期起算日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveStartDate;

    /** 投资期截止日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveEndDate;

    /** 退出期延长 */
    private String paybackOver;

    /** 存续期 */
    private String durationPeriod;

    /** 管理费方式(码值1010：1按认缴，2按实缴) */
    private String mgtOrPaid;

    /** 超额分配比例-管理人比例 */
    private Double mcFee;
    /** 投资期管理费 */
    private String inveFeeRate;
    /** 退出期管理费 */
    private String paybackFeeRate;

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

    private Double currentAmount;

    /** 基金实缴规模 */
    private Double raiseAmount;
    /** 合规自查附件 */
    private String regulationFile;

    private Double secTmoneyPer;

    private Double thrTmoneyPer;

    private String  flowStatus;


    private String confirmLetterFile;

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

    public Double getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(Double raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMgtFeeRate() {
        return mgtFeeRate;
    }

    public void setMgtFeeRate(Double mgtFeeRate) {
        this.mgtFeeRate = mgtFeeRate;
    }

    public String getMgtFeeDesc() {
        return mgtFeeDesc;
    }

    public void setMgtFeeDesc(String mgtFeeDesc) {
        this.mgtFeeDesc = mgtFeeDesc;
    }

    public String getInvePeriod() {
        return invePeriod;
    }

    public void setInvePeriod(String invePeriod) {
        this.invePeriod = invePeriod;
    }

    public String getPaybackPeriod() {
        return paybackPeriod;
    }

    public void setPaybackPeriod(String paybackPeriod) {
        this.paybackPeriod = paybackPeriod;
    }

    public String getRollOver() {
        return rollOver;
    }

    public void setRollOver(String rollOver) {
        this.rollOver = rollOver;
    }

    public String getDurationDesc() {
        return durationDesc;
    }

    public void setDurationDesc(String durationDesc) {
        this.durationDesc = durationDesc;
    }

    public Double getHurdleRate() {
        return hurdleRate;
    }

    public void setHurdleRate(Double hurdleRate) {
        this.hurdleRate = hurdleRate;
    }

    public Double getPerformanceFee() {
        return performanceFee;
    }

    public void setPerformanceFee(Double performanceFee) {
        this.performanceFee = performanceFee;
    }

    public String getIncomeDistDesc() {
        return incomeDistDesc;
    }

    public void setIncomeDistDesc(String incomeDistDesc) {
        this.incomeDistDesc = incomeDistDesc;
    }

    public String getInveStrategy() {
        return inveStrategy;
    }

    public void setInveStrategy(String inveStrategy) {
        this.inveStrategy = inveStrategy;
    }

    public String getInveStandard() {
        return inveStandard;
    }

    public void setInveStandard(String inveStandard) {
        this.inveStandard = inveStandard;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getInveStartDate() {
        return inveStartDate;
    }

    public void setInveStartDate(Date inveStartDate) {
        this.inveStartDate = inveStartDate;
    }

    public Date getInveEndDate() {
        return inveEndDate;
    }

    public void setInveEndDate(Date inveEndDate) {
        this.inveEndDate = inveEndDate;
    }

    public String getPaybackOver() {
        return paybackOver;
    }

    public void setPaybackOver(String paybackOver) {
        this.paybackOver = paybackOver;
    }

    public String getDurationPeriod() {
        return durationPeriod;
    }

    public void setDurationPeriod(String durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

    public String getMgtOrPaid() {
        return mgtOrPaid;
    }

    public void setMgtOrPaid(String mgtOrPaid) {
        this.mgtOrPaid = mgtOrPaid;
    }

    public Double getMcFee() {
        return mcFee;
    }

    public void setMcFee(Double mcFee) {
        this.mcFee = mcFee;
    }

    public String getInveFeeRate() {
        return inveFeeRate;
    }

    public void setInveFeeRate(String inveFeeRate) {
        this.inveFeeRate = inveFeeRate;
    }

    public String getPaybackFeeRate() {
        return paybackFeeRate;
    }

    public void setPaybackFeeRate(String paybackFeeRate) {
        this.paybackFeeRate = paybackFeeRate;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public void setProjReportAtt(String projReportAtt) {
        this.projReportAtt = projReportAtt;
    }

    public String getOtherAtt() {
        return otherAtt;
    }

    public void setOtherAtt(String otherAtt) {
        this.otherAtt = otherAtt;
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

    public String getSignAmountDisplay() {
        return signAmountDisplay;
    }

    public void setSignAmountDisplay(String signAmountDisplay) {
        this.signAmountDisplay = signAmountDisplay;
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

    public String getProjMc() {
        return projMc;
    }

    public void setProjMc(String projMc) {
        this.projMc = projMc;
    }
}
