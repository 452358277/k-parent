package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ConditionFileModel;
import com.ppmg.ei.model.ConditionInfoModel;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;
import com.ppmg.ei.model.ConditionItemModel;
import java.util.Date;
import java.util.List;

@ApiModel(value = "征集信息详情", description = "征集信息详情")
public class ConditionInfoItemVo extends BaseVO {
    private static final long serialVersionUID = 1L;
    private String infoId;
    //合作条件ID
    private String conditionId;
    //母基金名称
    private String fundName;

    //母基金规模
    private Double fundSize;

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
    //已征集规模
    private Double cooSize;
    //已征集数量（家）
    private Double totalCooNum;
    //符合征集条件（家）
    private Double fitCooNum;
    //状态
    private String status;
    private String statusName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date updateDt;

    //流程实例id
    private String processInstId;

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    private List<ConditionItemModel> conditionItemModel;
    public List<ConditionItemModel> getConditionItemModel() {
        return conditionItemModel;
    }

    public void setConditionItemModel(List<ConditionItemModel> conditionItemModel) {
        this.conditionItemModel = conditionItemModel;
    }
    /*//file
    private List<ConditionFileModel> conditionFileModel;

    public List<ConditionFileModel> getConditionFileModel() {
        return conditionFileModel;
    }

    public void setConditionFileModel(List<ConditionFileModel> conditionFileModel) {
        this.conditionFileModel = conditionFileModel;
    }*/

    private ConditionInfoModel conditionInfoModel;
    public ConditionInfoModel getConditionInfoModel() {
        return conditionInfoModel;
    }

    public void setConditionInfoModel(ConditionInfoModel conditionInfoModel) {
        this.conditionInfoModel = conditionInfoModel;
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






}
