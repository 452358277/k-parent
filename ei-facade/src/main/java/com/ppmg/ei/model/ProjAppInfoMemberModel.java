package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjAppInfoMemberModel extends BaseModel {
    /** 主键 */
    private String projId;
    /** 项目编号 */
    private String projNo;
    /** 项目名称 */
    private String projName;
    /** 基金名称 */
    private String fundName;
    /** 出资主体id */
    private String inveId;
    private String projObject;

    /** 出资主体 */
    private String fundingBody;

    /** 基金认缴总规模（万元）币种 BD_T_FUND_INFO*/
    private String currentCurrency;
    private String currentCurrencyName;

    private String tag;


    public String getProjObject() {
        return projObject;
    }

    public void setProjObject(String projObject) {
        this.projObject = projObject;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCurrentCurrencyName() {
        return currentCurrencyName;
    }

    public void setCurrentCurrencyName(String currentCurrencyName) {
        this.currentCurrencyName = currentCurrencyName;
    }

    /** 基金认缴总规模（万元） BD_T_FUND_INFO*/
    private Double currentAmount;
    /** 其中：本基金认缴规模（万元） */
    private Double inveCurrentAmount;
    /** 申请母基金额度（万元）*/
    private Double applyAmount;

    /** 项目来源 */
    private String projSrc;
    private String projSrcName;

    public String getProjSrcName() {
        return projSrcName;
    }

    public void setProjSrcName(String projSrcName) {
        this.projSrcName = projSrcName;
    }
    /** 来源说明 */
    private String srcDesc;

    public String getSrcDesc() {
        return srcDesc;
    }

    public void setSrcDesc(String srcDesc) {
        this.srcDesc = srcDesc;
    }

    /** 项目组成员 */
    private List<ProjMemberModel> projMemberModel;
    /** 项目概况 */
    private String projOverview;
    //----------------
    /**决策事项**/
    private String majorMatter;
    /**投资理由**/
    private String inveReason;
    /**尽职调查重点**/
    private String focusInfo;
    /**附件说明,TZ_T_PROJ_INFO**/
    private String fileDesc;
    /**附件，TZ_T_PROJ_INFO**/
    private String inveFile;

    private String fundId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundingBody() {
        return fundingBody;
    }

    public void setFundingBody(String fundingBody) {
        this.fundingBody = fundingBody;
    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Double getInveCurrentAmount() {
        return inveCurrentAmount;
    }

    public void setInveCurrentAmount(Double inveCurrentAmount) {
        this.inveCurrentAmount = inveCurrentAmount;
    }

    public Double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(Double applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getProjSrc() {
        return projSrc;
    }

    public void setProjSrc(String projSrc) {
        this.projSrc = projSrc;
    }

    public List<ProjMemberModel> getProjMemberModel() {
        return projMemberModel;
    }

    public void setProjMemberModel(List<ProjMemberModel> projMemberModel) {
        this.projMemberModel = projMemberModel;
    }

    public String getProjOverview() {
        return projOverview;
    }

    public void setProjOverview(String projOverview) {
        this.projOverview = projOverview;
    }

    public String getMajorMatter() {
        return majorMatter;
    }

    public void setMajorMatter(String majorMatter) {
        this.majorMatter = majorMatter;
    }

    public String getInveReason() {
        return inveReason;
    }

    public void setInveReason(String inveReason) {
        this.inveReason = inveReason;
    }

    public String getFocusInfo() {
        return focusInfo;
    }

    public void setFocusInfo(String focusInfo) {
        this.focusInfo = focusInfo;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getInveFile() {
        return inveFile;
    }

    public void setInveFile(String inveFile) {
        this.inveFile = inveFile;
    }


    /** 流程实例号 */
    private String processInstId;

    /** 被投对象融资轮数 */
    private String finaRounds;

    /** 被投对象融资次数 */
    private String finaTimes;

    /** 被投对象融资金额 */
    private Double finaAmount;

    /** FINA_CURRENCY */
    private String finaCurrency;

    /** 投资角色 */
    private String inveRole;

    /** 投资类型 */
    private String inveType;

    /** 投资轮次 */
    private String inveRounds;

    /** 投前估值 */
    private Double preMoney;

    /** PRE_CURRENCY */
    private String preCurrency;

    /** 投后估值 */
    private Double postMoney;

    /** POST_CURRENCY */
    private String postCurrency;

    /** 拟投资金额（本轮投资总额） */
    private Double intendedAmount;

    /** 拟投资币种 */
    private String intendedCurrency;

    /** 购买股数 */
    private Long inveShare;

    /** 占股比（%） */
    private Double perShare;

    /** null */
    private String projGuid;

    /** 折算为人民币 */
    private Double rmbIntendedAmount;


    /** null */
    private String remark;

    /** 尽职调查重点/其它重点关注信息附件 */
    private String focusInfoAtt;

    /** 投资理由附件 */
    private String inveReasonAtt;

    /** 决策事项/董监事安排附件 */
    private String majorMatterAtt;

    /** 备注附件 */
    private String remarkAtt;

    /** 立项问题反馈 */
    private String feedBack;

    /** 是否有园区会议纪要(0否1是) */
    private String ismeetingrecord;

    /** 园区会议纪要附件 */
    private String meetingrecordAtta;

    /** 决策会议纪要 */
    private String decisionAtta;

    /** 商业计划书附件 */
    private String businessPlanFile;

    /** 立项报告附件 */
    private String projectReportFile;

    /** 访谈纪要附件 */
    private String summaryInterviewsFile;

    /** 立项附件-其他 */
    private String anotherFile;

    /** 决策附件 */
    private String decisionFile;

    /** 实际投资金额(本基金投资额) */
    private Double actualAmount;

    /** 投决是否通过 */
    private String isDecisionPass;

    /** 投决会召开时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date decisionDate;

    /** 出资主体实缴规模(其中：本基金实缴规模) */
    private Double inveRaiseAmount;

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
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

    public String getFinaCurrency() {
        return finaCurrency;
    }

    public void setFinaCurrency(String finaCurrency) {
        this.finaCurrency = finaCurrency;
    }

    public String getInveRole() {
        return inveRole;
    }

    public void setInveRole(String inveRole) {
        this.inveRole = inveRole;
    }

    public String getInveType() {
        return inveType;
    }

    public void setInveType(String inveType) {
        this.inveType = inveType;
    }

    public String getInveRounds() {
        return inveRounds;
    }

    public void setInveRounds(String inveRounds) {
        this.inveRounds = inveRounds;
    }

    public Double getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Double preMoney) {
        this.preMoney = preMoney;
    }

    public String getPreCurrency() {
        return preCurrency;
    }

    public void setPreCurrency(String preCurrency) {
        this.preCurrency = preCurrency;
    }

    public Double getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(Double postMoney) {
        this.postMoney = postMoney;
    }

    public String getPostCurrency() {
        return postCurrency;
    }

    public void setPostCurrency(String postCurrency) {
        this.postCurrency = postCurrency;
    }

    public Double getIntendedAmount() {
        return intendedAmount;
    }

    public void setIntendedAmount(Double intendedAmount) {
        this.intendedAmount = intendedAmount;
    }

    public String getIntendedCurrency() {
        return intendedCurrency;
    }

    public void setIntendedCurrency(String intendedCurrency) {
        this.intendedCurrency = intendedCurrency;
    }

    public Long getInveShare() {
        return inveShare;
    }

    public void setInveShare(Long inveShare) {
        this.inveShare = inveShare;
    }

    public Double getPerShare() {
        return perShare;
    }

    public void setPerShare(Double perShare) {
        this.perShare = perShare;
    }

    public String getProjGuid() {
        return projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public Double getRmbIntendedAmount() {
        return rmbIntendedAmount;
    }

    public void setRmbIntendedAmount(Double rmbIntendedAmount) {
        this.rmbIntendedAmount = rmbIntendedAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFocusInfoAtt() {
        return focusInfoAtt;
    }

    public void setFocusInfoAtt(String focusInfoAtt) {
        this.focusInfoAtt = focusInfoAtt;
    }

    public String getInveReasonAtt() {
        return inveReasonAtt;
    }

    public void setInveReasonAtt(String inveReasonAtt) {
        this.inveReasonAtt = inveReasonAtt;
    }

    public String getMajorMatterAtt() {
        return majorMatterAtt;
    }

    public void setMajorMatterAtt(String majorMatterAtt) {
        this.majorMatterAtt = majorMatterAtt;
    }

    public String getRemarkAtt() {
        return remarkAtt;
    }

    public void setRemarkAtt(String remarkAtt) {
        this.remarkAtt = remarkAtt;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getIsmeetingrecord() {
        return ismeetingrecord;
    }

    public void setIsmeetingrecord(String ismeetingrecord) {
        this.ismeetingrecord = ismeetingrecord;
    }

    public String getMeetingrecordAtta() {
        return meetingrecordAtta;
    }

    public void setMeetingrecordAtta(String meetingrecordAtta) {
        this.meetingrecordAtta = meetingrecordAtta;
    }

    public String getDecisionAtta() {
        return decisionAtta;
    }

    public void setDecisionAtta(String decisionAtta) {
        this.decisionAtta = decisionAtta;
    }

    public String getBusinessPlanFile() {
        return businessPlanFile;
    }

    public void setBusinessPlanFile(String businessPlanFile) {
        this.businessPlanFile = businessPlanFile;
    }

    public String getProjectReportFile() {
        return projectReportFile;
    }

    public void setProjectReportFile(String projectReportFile) {
        this.projectReportFile = projectReportFile;
    }

    public String getSummaryInterviewsFile() {
        return summaryInterviewsFile;
    }

    public void setSummaryInterviewsFile(String summaryInterviewsFile) {
        this.summaryInterviewsFile = summaryInterviewsFile;
    }

    public String getAnotherFile() {
        return anotherFile;
    }

    public void setAnotherFile(String anotherFile) {
        this.anotherFile = anotherFile;
    }

    public String getDecisionFile() {
        return decisionFile;
    }

    public void setDecisionFile(String decisionFile) {
        this.decisionFile = decisionFile;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getIsDecisionPass() {
        return isDecisionPass;
    }

    public void setIsDecisionPass(String isDecisionPass) {
        this.isDecisionPass = isDecisionPass;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Double getInveRaiseAmount() {
        return inveRaiseAmount;
    }

    public void setInveRaiseAmount(Double inveRaiseAmount) {
        this.inveRaiseAmount = inveRaiseAmount;
    }
}
