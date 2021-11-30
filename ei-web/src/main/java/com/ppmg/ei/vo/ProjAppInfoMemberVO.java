package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjMemberModel;

import java.util.List;

public class ProjAppInfoMemberVO extends BaseVO {
    /** 主键 */
    private String projId;
    /** 项目编号 */
    private String projNo;
    /** 项目名称 */
    private String projName;
    /** 基金名称 */
    private String fundName;
    /** 出资主体 */
    private String fundingBody;

    /** 基金认缴总规模（万元）币种 BD_T_FUND_INFO*/
    private String currentCurrency;
    private String currentCurrencyName;

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
    private String inveId;
    private String status;

    //流程節點增加附件
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
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
}
