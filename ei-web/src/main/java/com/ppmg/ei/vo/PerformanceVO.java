package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.*;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value = "绩效管理", description = "绩效管理")
public class PerformanceVO extends BaseVO {
    //主键id
    private String pPerID;
    //基金绩效名称
    private String pName;
    //绩效年度
    private String pYear;
    //考核开始时间
    @DateTimeFormat(pattern = "yyyy-MM")
    @JSONField(format = "yyyy-MM")
    private Date pAssessStartTime;
    //考核结束时间
    @DateTimeFormat(pattern = "yyyy-MM")
    @JSONField(format = "yyyy-MM")
    private Date pAssessEndTime;
    /**投资开始时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date touStartTime;
    /**投资结束时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date touEndTime;
    //考核基金数
    //private Integer pAssessFunds;
    //考核状态(0未发布，1：已发布，9删除)
    private String pStatus;
    //考核基金数
    private String fundCount;
    //已经自评数量
    private String psStatusCount;
    //考核基金数
    private String psFundCount;
    private String pStatusName;
    private String fundSrc;
    //一级指标表
    private List<PerfOneNormModel> perfOneNormModel;
    //二级指标表
    private List<PerfSecondNormModel> perfSecondNormModel;
    //三级指标表
    private List<PerfThreeNormModel> perfThreeNormModel;
    //自评审核分数详情表
    private List<PerfScoreModelDetails> perfScoreModelDetails;

    public Date getTouStartTime() {
        return touStartTime;
    }

    public void setTouStartTime(Date touStartTime) {
        this.touStartTime = touStartTime;
    }

    public Date getTouEndTime() {
        return touEndTime;
    }

    public void setTouEndTime(Date touEndTime) {
        this.touEndTime = touEndTime;
    }

    public String getpPerID() {
        return pPerID;
    }

    public void setpPerID(String pPerID) {
        this.pPerID = pPerID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpYear() {
        return pYear;
    }

    public void setpYear(String pYear) {
        this.pYear = pYear;
    }

    public Date getpAssessStartTime() {
        return pAssessStartTime;
    }

    public void setpAssessStartTime(Date pAssessStartTime) {
        this.pAssessStartTime = pAssessStartTime;
    }

    public Date getpAssessEndTime() {
        return pAssessEndTime;
    }

    public void setpAssessEndTime(Date pAssessEndTime) {
        this.pAssessEndTime = pAssessEndTime;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getFundCount() {
        return fundCount;
    }

    public void setFundCount(String fundCount) {
        this.fundCount = fundCount;
    }

    public String getPsStatusCount() {
        return psStatusCount;
    }

    public void setPsStatusCount(String psStatusCount) {
        this.psStatusCount = psStatusCount;
    }

    public String getPsFundCount() {
        return psFundCount;
    }

    public void setPsFundCount(String psFundCount) {
        this.psFundCount = psFundCount;
    }

    public String getpStatusName() {
        return pStatusName;
    }

    public void setpStatusName(String pStatusName) {
        this.pStatusName = pStatusName;
    }

    public String getFundSrc() {
        return fundSrc;
    }

    public void setFundSrc(String fundSrc) {
        this.fundSrc = fundSrc;
    }

    public List<PerfOneNormModel> getPerfOneNormModel() {
        return perfOneNormModel;
    }

    public void setPerfOneNormModel(List<PerfOneNormModel> perfOneNormModel) {
        this.perfOneNormModel = perfOneNormModel;
    }

    public List<PerfSecondNormModel> getPerfSecondNormModel() {
        return perfSecondNormModel;
    }

    public void setPerfSecondNormModel(List<PerfSecondNormModel> perfSecondNormModel) {
        this.perfSecondNormModel = perfSecondNormModel;
    }

    public List<PerfThreeNormModel> getPerfThreeNormModel() {
        return perfThreeNormModel;
    }

    public void setPerfThreeNormModel(List<PerfThreeNormModel> perfThreeNormModel) {
        this.perfThreeNormModel = perfThreeNormModel;
    }

    public List<PerfScoreModelDetails> getPerfScoreModelDetails() {
        return perfScoreModelDetails;
    }

    public void setPerfScoreModelDetails(List<PerfScoreModelDetails> perfScoreModelDetails) {
        this.perfScoreModelDetails = perfScoreModelDetails;
    }
}
