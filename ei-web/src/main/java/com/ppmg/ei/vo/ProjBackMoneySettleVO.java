package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjBackMoneySettleVO extends BaseVO {
    /** 主键 */
    private String settleId;

    /** 项目ID */
    private String projId;

    /** 退出决策ID */
    private String appId;
    private String appIdName;

    public String getAppIdName() {
        return appIdName;
    }

    public void setAppIdName(String appIdName) {
        this.appIdName = appIdName;
    }

    //	/申请名称
    private String projName;

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    /** 回收类型（码值1018，1合同类，2合伙人会议） */
    private String settleType;
    private String settleTypeName;

    public String getSettleTypeName() {
        return settleTypeName;
    }

    public void setSettleTypeName(String settleTypeName) {
        this.settleTypeName = settleTypeName;
    }

    /** 合同ID（回收类型是合同类时，关联合同表） */
    private String fileId;

    /** 投后事项ID（回收类型是合伙人会议时，关联投后事项表） */
    private String meetingId;

    /** 本金 */
    private Double raiseAmount;

    /** 收益 */
    private Double currentAmount;

    /** 附件 */
    private String attaFile;

    /** 状态 */
    private String status;
    private String statusName;


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** 收款人名称 */
    private String accountName;

    /** 收款账号 */
    private String accounts;

    /** 开户行 */
    private String openBank;

    /** 实际收款金额 */
    private Double bizAmt;

    /** 收款日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date bizDate;

    //回收类型是合同类的时候，合同名称
    private String contractTitle;

    //流程实例id
    private  String processInstId;
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

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public Double getBizAmt() {
        return bizAmt;
    }

    public void setBizAmt(Double bizAmt) {
        this.bizAmt = bizAmt;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSettleId() {
        return settleId;
    }

    public void setSettleId(String settleId) {
        this.settleId = settleId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
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

    public String getAttaFile() {
        return attaFile;
    }

    public void setAttaFile(String attaFile) {
        this.attaFile = attaFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
