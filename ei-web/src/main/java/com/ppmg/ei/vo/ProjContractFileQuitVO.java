package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjContractFileQuitUtilModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjContractFileQuitVO extends BaseVO {
    /** 主键 */
    private String fileId;
    /** 项目ID */
    private String projId;
    /** 文件类别 */
    private String fileType;
    private String fileTypeName;
    /** 备注 */
    private String remark;
    /** 合同文件 */
    private String contractFile;
    /** 重点关注条款 */
    private String majorTerm;

    /** 文件名称 */
    private String fileTitle;
    private String status;
    private String statusName;


    //退出立项名称id(退出决策id)
    private String appId;
    //退出立项名称(退出决策名称)
    private String appName;
    //退出日期
    private String quitDt;
    //退出方式
    private String quitWay;
    private String quitWayName;
    /*基金认缴总规模（万元）--认缴出资额（万元）*/
    private Double fundSubscribed;
    /*退基金实缴规模（万元）--实缴出资额（万元）*/
    private Double fundPaidIn;
    /** 占股比 */
    private Double shareRatio;
    //收回金额
    private String backAmount;
    //退出金额(退出认缴出资额（万元)
    private String exitAmount;
    //退出部分所占股比
    private String exitShareRatio;
    //剩余部分所占股比
    private String restShareRatio;
    //决策事项(决策内容)
    private String decisionDetail;
    /** 本协议金额 */
    private Double thisAgreeAmont;
    //退出立项id
    private String quitProjId;
    //退出立项名称
    private String quitName;
    //最终合同附件
    private String finalFile;
    //合同签订日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date signDt;
    //項目名稱
    private String projName;
    //出資主體
    private String inveName;
    //流程实例id
    private String processInstId;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }

    public String getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(String finalFile) {
        this.finalFile = finalFile;
    }

    public Date getSignDt() {
        return signDt;
    }

    public void setSignDt(Date signDt) {
        this.signDt = signDt;
    }

    public String getQuitProjId() {
        return quitProjId;
    }

    public void setQuitProjId(String quitProjId) {
        this.quitProjId = quitProjId;
    }

    public String getQuitName() {
        return quitName;
    }

    public void setQuitName(String quitName) {
        this.quitName = quitName;
    }

    /** 本协议金额币种 */
    private String thisAgreeAmontCurr;
    private String thisAgreeAmontCurrName;

    public String getThisAgreeAmontCurrName() {
        return thisAgreeAmontCurrName;
    }

    public void setThisAgreeAmontCurrName(String thisAgreeAmontCurrName) {
        this.thisAgreeAmontCurrName = thisAgreeAmontCurrName;
    }

    private List<ProjContractFileQuitUtilModel> projContractFileQuitUtilModel;

    public List<ProjContractFileQuitUtilModel> getProjContractFileQuitUtilModel() {
        return projContractFileQuitUtilModel;
    }

    public void setProjContractFileQuitUtilModel(List<ProjContractFileQuitUtilModel> projContractFileQuitUtilModel) {
        this.projContractFileQuitUtilModel = projContractFileQuitUtilModel;
    }


    public Double getThisAgreeAmont() {
        return thisAgreeAmont;
    }

    public void setThisAgreeAmont(Double thisAgreeAmont) {
        this.thisAgreeAmont = thisAgreeAmont;
    }

    public String getThisAgreeAmontCurr() {
        return thisAgreeAmontCurr;
    }

    public void setThisAgreeAmontCurr(String thisAgreeAmontCurr) {
        this.thisAgreeAmontCurr = thisAgreeAmontCurr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile;
    }

    public String getMajorTerm() {
        return majorTerm;
    }

    public void setMajorTerm(String majorTerm) {
        this.majorTerm = majorTerm;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getQuitWayName() {
        return quitWayName;
    }

    public void setQuitWayName(String quitWayName) {
        this.quitWayName = quitWayName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(String quitDt) {
        this.quitDt = quitDt;
    }

    public String getQuitWay() {
        return quitWay;
    }

    public void setQuitWay(String quitWay) {
        this.quitWay = quitWay;
    }

    public Double getFundSubscribed() {
        return fundSubscribed;
    }

    public void setFundSubscribed(Double fundSubscribed) {
        this.fundSubscribed = fundSubscribed;
    }

    public Double getFundPaidIn() {
        return fundPaidIn;
    }

    public void setFundPaidIn(Double fundPaidIn) {
        this.fundPaidIn = fundPaidIn;
    }

    public Double getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(Double shareRatio) {
        this.shareRatio = shareRatio;
    }

    public String getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(String backAmount) {
        this.backAmount = backAmount;
    }

    public String getExitAmount() {
        return exitAmount;
    }

    public void setExitAmount(String exitAmount) {
        this.exitAmount = exitAmount;
    }

    public String getExitShareRatio() {
        return exitShareRatio;
    }

    public void setExitShareRatio(String exitShareRatio) {
        this.exitShareRatio = exitShareRatio;
    }

    public String getRestShareRatio() {
        return restShareRatio;
    }

    public void setRestShareRatio(String restShareRatio) {
        this.restShareRatio = restShareRatio;
    }

    public String getDecisionDetail() {
        return decisionDetail;
    }

    public void setDecisionDetail(String decisionDetail) {
        this.decisionDetail = decisionDetail;
    }
}
