package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class ProjReceiptQuitVO extends BaseVO {
    /** 主键ID */
    private String receiptId;

    /** 关联id */
    private String projId;

    /** 关联退出合同id */
    private String fileId;

    /** 收回金额  */
    private Double backMoney;

    /** 收款人名称 */
    private String receiptName;

    /** 收款账号 */
    private Double receiptNumber;

    /** 收款开户行 */
    private String receiptBank;

    /** 付款人名称 */
    private String payName;

    /** 付款账号 */
    private Double payNumber;

    /** 付款开户行 */
    private String payBank;

    /** 附件 */
    private String quitFile;

    /** 状态 */
    private String status;
    private String statusName;
    //退出立项id
    private String quitProjId;
    //退出立项名称
    private String quitName;
    /** 项目名称 */
    private String projName;
    /** 出资主体名称 */
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

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Double backMoney) {
        this.backMoney = backMoney;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public Double getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Double receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptBank() {
        return receiptBank;
    }

    public void setReceiptBank(String receiptBank) {
        this.receiptBank = receiptBank;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Double getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Double payNumber) {
        this.payNumber = payNumber;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getQuitFile() {
        return quitFile;
    }

    public void setQuitFile(String quitFile) {
        this.quitFile = quitFile;
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
}
