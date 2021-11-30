package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ConditionModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    //合作条件ID
    private String conditionId;
    //母基金名称
    private String fundName;
    //母基金规模
    private Double fundSize;
    //管理部门
    private String manDep;
    //登记人
    private String registrant;
    //登记日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date regDate;
    //概要说明
    private String remark;
    //基金管理办法附件
    private String methodFile;
    //合作条件附件
    private String conditionFile;
    //载体(基金号、项目号)
    private String carrier;
    //载体名称
    private String carrierName;
    //载体类别(1：融资，2：投资，3：企业投后)
    private String carrierType;
    //流程ID
    private String processInstId;
    //状态
    private String status;
    //状态名称
    private String statusName;
    //秘书提交审批会议结果
    private String meetingFile;
    //母基金ID
    private String fundId;
    //拟征集规模
    private Double planSize;
    //拟征集数量（家）
    private Double planNum;
    //征集开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    //征集截止日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    //特殊约定
    private String specialFile;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private ConditionInfoModel conditionInfoModel;
    public ConditionInfoModel getConditionInfoModel() {
        return conditionInfoModel;
    }

    public void setConditionInfoModel(ConditionInfoModel conditionInfoModel) {
        this.conditionInfoModel = conditionInfoModel;
    }
    /*private List<ConditionFileModel> conditionFileModel;

    public List<ConditionFileModel> getConditionFileModel() {
        return conditionFileModel;
    }

    public void setConditionFileModel(List<ConditionFileModel> conditionFileModel) {
        this.conditionFileModel = conditionFileModel;
    }*/
    private List<ConditionItemModel> conditionItemModel;
    public List<ConditionItemModel> getConditionItemModel() {
        return conditionItemModel;
    }

    public void setConditionItemModel(List<ConditionItemModel> conditionItemModel) {
        this.conditionItemModel = conditionItemModel;
    }



    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Double getFundSize() {
        return fundSize;
    }

    public void setFundSize(Double fundSize) {
        this.fundSize = fundSize;
    }

    public String getManDep() {
        return manDep;
    }

    public void setManDep(String manDep) {
        this.manDep = manDep;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethodFile() {
        return methodFile;
    }

    public void setMethodFile(String methodFile) {
        this.methodFile = methodFile;
    }

    public String getConditionFile() {
        return conditionFile;
    }

    public void setConditionFile(String conditionFile) {
        this.conditionFile = conditionFile;
    }


    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
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

    public String getMeetingFile() {
        return meetingFile;
    }

    public void setMeetingFile(String meetingFile) {
        this.meetingFile = meetingFile;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSpecialFile() {
        return specialFile;
    }

    public void setSpecialFile(String specialFile) {
        this.specialFile = specialFile;
    }


}
