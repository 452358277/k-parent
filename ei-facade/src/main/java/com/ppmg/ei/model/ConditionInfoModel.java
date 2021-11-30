package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.Date;
import java.util.List;

public class ConditionInfoModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    //合作条件ID
    private String infoId;
    //公开征集ID
    private String conditionId;
    //已征集规模
    private Double cooSize;
    //已征集数量（家）
    private Double totalCooNum;
    //符合征集条件（家）
    private Double fitCooNum;
    //会议纪要（决策文件）
    private String meetingFile;
    //相关附件
    private String attaFile;
    //流程ID
    private String processInstId;
    //状态
    private String status;
    private String statusName;
    //母基金ID
    private String fundId;
    //附件说明
    private String attaDetail;

    //母基金名称
    private String fundName;
    //拟征集规模
    private Double planSize;
    //拟征集数量（家）
    private Double planNum;
    /*基金管理人id*/
    private String itemIdArr;
    private String fundSize;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFundSize() {
        return fundSize;
    }

    public void setFundSize(String fundSize) {
        this.fundSize = fundSize;
    }
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 基金管理人
     * @return
     */
    private List<ConditionItemModel> conditionItemModel;

    public List<ConditionItemModel> getConditionItemModel() {
        return conditionItemModel;
    }

    public void setConditionItemModel(List<ConditionItemModel> conditionItemModel) {
        this.conditionItemModel = conditionItemModel;
    }

    public String getItemIdArr() {
        return itemIdArr;
    }

    public void setItemIdArr(String itemIdArr) {
        this.itemIdArr = itemIdArr;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Double getPlanSize() {
        return planSize;
    }

    public void setPlanSize(Double planSize) {
        this.planSize = planSize;
    }

    public Double getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Double planNum) {
        this.planNum = planNum;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Double getCooSize() {
        return cooSize;
    }

    public void setCooSize(Double cooSize) {
        this.cooSize = cooSize;
    }

    public Double getTotalCooNum() {
        return totalCooNum;
    }

    public void setTotalCooNum(Double totalCooNum) {
        this.totalCooNum = totalCooNum;
    }

    public Double getFitCooNum() {
        return fitCooNum;
    }

    public void setFitCooNum(Double fitCooNum) {
        this.fitCooNum = fitCooNum;
    }

    public String getMeetingFile() {
        return meetingFile;
    }

    public void setMeetingFile(String meetingFile) {
        this.meetingFile = meetingFile;
    }

    public String getAttaFile() {
        return attaFile;
    }

    public void setAttaFile(String attaFile) {
        this.attaFile = attaFile;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

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

    public String getAttaDetail() {
        return attaDetail;
    }

    public void setAttaDetail(String attaDetail) {
        this.attaDetail = attaDetail;
    }
}
