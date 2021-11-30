package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

/**
 * 项目投管-基金立项
 */
public class ProjAppInfoProjectMemberVO extends BaseVO {
    /** 主键 */
    private String projId;

    /** 被投企业融资轮次 */
    private String finaRounds;
    private String finaRoundsName;

    /** 被投企业融资次数 */
    private String finaTimes;

    /**被投企业融资金额（万元） */
    private Double finaAmount;

    /**被投企业融资金额（万元）币种 */
    private String finaCurrency;
    private String finaCurrencyName;

    /** 投前估值 */
    private Double preMoney;

    /** 投前估值币种 */
    private String preCurrency;
    private String preCurrencyName;

    /** 投后估值 */
    private Double postMoney;

    /** 投后估值币种 */
    private String postCurrency;
    private String postCurrencyName;

    //出资主体
    private String fundingBody;
    private String inveId;

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    /** 投资角色 */
    private String inveRole;
    private String inveRoleName;

    /** 投资类型 */
    private String inveType;
    private String inveTypeName;

    /** 投资轮次 */
    private String inveRounds;

    /** 拟投资金额（万元） */
    private Double intendedAmount;

    /** 拟投资金额（万元）币种 */
    private String intendedCurrency;
    private String intendedCurrencyName;

    /** 购买股数 */
    private Long inveShare;

    /** 占股比（%） */
    private Double perShare;

    /** 重点关注信息附件 */
    private String focusInfoAtt;

    /**重点关注信息**/
    private String focusInfo;

    /** 立项附件-其他 */
    private String anotherFile;

    private String status;
    private String statusName;

    private String projName;

    private String meetingrecordAtta;

    private String taskId;

    /** 流程实例 */
    private String processInstId;

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMeetingrecordAtta() {
        return meetingrecordAtta;
    }

    public void setMeetingrecordAtta(String meetingrecordAtta) {
        this.meetingrecordAtta = meetingrecordAtta;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getFinaRounds() {
        return finaRounds;
    }

    public void setFinaRounds(String finaRounds) {
        this.finaRounds = finaRounds;
    }

    public String getFinaRoundsName() {
        return finaRoundsName;
    }

    public void setFinaRoundsName(String finaRoundsName) {
        this.finaRoundsName = finaRoundsName;
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

    public String getFinaCurrencyName() {
        return finaCurrencyName;
    }

    public void setFinaCurrencyName(String finaCurrencyName) {
        this.finaCurrencyName = finaCurrencyName;
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

    public String getPreCurrencyName() {
        return preCurrencyName;
    }

    public void setPreCurrencyName(String preCurrencyName) {
        this.preCurrencyName = preCurrencyName;
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

    public String getPostCurrencyName() {
        return postCurrencyName;
    }

    public void setPostCurrencyName(String postCurrencyName) {
        this.postCurrencyName = postCurrencyName;
    }

    public String getFundingBody() {
        return fundingBody;
    }

    public void setFundingBody(String fundingBody) {
        this.fundingBody = fundingBody;
    }

    public String getInveRole() {
        return inveRole;
    }

    public void setInveRole(String inveRole) {
        this.inveRole = inveRole;
    }

    public String getInveRoleName() {
        return inveRoleName;
    }

    public void setInveRoleName(String inveRoleName) {
        this.inveRoleName = inveRoleName;
    }

    public String getInveType() {
        return inveType;
    }

    public void setInveType(String inveType) {
        this.inveType = inveType;
    }

    public String getInveTypeName() {
        return inveTypeName;
    }

    public void setInveTypeName(String inveTypeName) {
        this.inveTypeName = inveTypeName;
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

    public String getIntendedCurrency() {
        return intendedCurrency;
    }

    public void setIntendedCurrency(String intendedCurrency) {
        this.intendedCurrency = intendedCurrency;
    }

    public String getIntendedCurrencyName() {
        return intendedCurrencyName;
    }

    public void setIntendedCurrencyName(String intendedCurrencyName) {
        this.intendedCurrencyName = intendedCurrencyName;
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

    public String getFocusInfoAtt() {
        return focusInfoAtt;
    }

    public void setFocusInfoAtt(String focusInfoAtt) {
        this.focusInfoAtt = focusInfoAtt;
    }

    public String getFocusInfo() {
        return focusInfo;
    }

    public void setFocusInfo(String focusInfo) {
        this.focusInfo = focusInfo;
    }

    public String getAnotherFile() {
        return anotherFile;
    }

    public void setAnotherFile(String anotherFile) {
        this.anotherFile = anotherFile;
    }
}
