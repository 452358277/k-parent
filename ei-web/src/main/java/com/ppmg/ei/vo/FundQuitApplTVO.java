package com.ppmg.ei.vo;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

public class FundQuitApplTVO extends BaseVO {
    private String appId;
    private String projId;
    /*申请名称 */
    private String appName;
    /*退出日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date quitDt;
    /*退出方式 */
    private String quitWay;
    private String quitWayName;

    public String getQuitWayName() {
        return quitWayName;
    }

    public void setQuitWayName(String quitWayName) {
        this.quitWayName = quitWayName;
    }
    /*基金认缴总规模（万元）*/
    private Double fundSubscribed;
    /*退基金实缴规模（万元）*/
    private Double fundPaidIn;
    /*股比%*/
    private String shareRatio;

    /*收回金额（万元） **/
    private String backAmount;
    /*退出认缴出资额（万元*/
    private String exitAmount;

    /*退出部分所占股比%*/
    private String exitShareRatio;
    /*剩余部分所占股比%*/
    private String restShareRatio;
    /*退出理由 */
    private String exitReason;
    /*决策内容*/
    private String decisionDetail;
    /** 备注 */
    private String remark;
    /** 附件 */
    private String attaFile;
    private String status;
    //基金实缴规模
    private Double raiseAmount;

    /** 流程编号 */
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

    public Double getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(Double raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(Date quitDt) {
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

    public String getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(String shareRatio) {
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

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public String getDecisionDetail() {
        return decisionDetail;
    }

    public void setDecisionDetail(String decisionDetail) {
        this.decisionDetail = decisionDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttaFile() {
        return attaFile;
    }

    public void setAttaFile(String attaFile) {
        this.attaFile = attaFile;
    }
}
