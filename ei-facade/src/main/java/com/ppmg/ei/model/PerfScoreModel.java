package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 评测分数表
 */
public class PerfScoreModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    //主键id
    private String psId;
    //关联主表id
    private String pPerId;
    //关联基金表:BD_T_FUND_INFO
    private String fundId;
    //自评总得分
    private Double psOneselfScore;
    //考核总得分
    private Double psAuditScore;
    //是否自评（0草稿，1自评）
    private String psOneselfYesno;
    //是否考核（0草稿，1考核）
    private String psAssessYesno;
    //附件
    private String psAttachment;
    //基金名字
    private String fundName;
    //考核年份
    private String pYear;

    private String fundSrc;

    /**本次考核开始时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date thisStartTime;
    /**本次考核结束时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date thisEndTime;

    /**基金考核开始时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date fundStartTime;
    /**基金考核结束时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date fundEndTime;

    public Date getFundStartTime() {
        return fundStartTime;
    }

    public void setFundStartTime(Date fundStartTime) {
        this.fundStartTime = fundStartTime;
    }

    public Date getFundEndTime() {
        return fundEndTime;
    }

    public void setFundEndTime(Date fundEndTime) {
        this.fundEndTime = fundEndTime;
    }

    public Date getThisStartTime() {
        return thisStartTime;
    }

    public void setThisStartTime(Date thisStartTime) {
        this.thisStartTime = thisStartTime;
    }

    public Date getThisEndTime() {
        return thisEndTime;
    }

    public void setThisEndTime(Date thisEndTime) {
        this.thisEndTime = thisEndTime;
    }

    public String getFundSrc() {
        return fundSrc;
    }

    public void setFundSrc(String fundSrc) {
        this.fundSrc = fundSrc;
    }

    private List<PerfScoreModelDetails> perfScoreModelDetails;

    public List<PerfScoreModelDetails> getPerfScoreModelDetails() {
        return perfScoreModelDetails;
    }

    public void setPerfScoreModelDetails(List<PerfScoreModelDetails> perfScoreModelDetails) {
        this.perfScoreModelDetails = perfScoreModelDetails;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getpPerId() {
        return pPerId;
    }

    public void setpPerId(String pPerId) {
        this.pPerId = pPerId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public Double getPsOneselfScore() {
        return psOneselfScore;
    }

    public void setPsOneselfScore(Double psOneselfScore) {
        this.psOneselfScore = psOneselfScore;
    }

    public Double getPsAuditScore() {
        return psAuditScore;
    }

    public void setPsAuditScore(Double psAuditScore) {
        this.psAuditScore = psAuditScore;
    }

    public String getPsOneselfYesno() {
        return psOneselfYesno;
    }

    public void setPsOneselfYesno(String psOneselfYesno) {
        this.psOneselfYesno = psOneselfYesno;
    }

    public String getPsAssessYesno() {
        return psAssessYesno;
    }

    public void setPsAssessYesno(String psAssessYesno) {
        this.psAssessYesno = psAssessYesno;
    }

    public String getPsAttachment() {
        return psAttachment;
    }

    public void setPsAttachment(String psAttachment) {
        this.psAttachment = psAttachment;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getpYear() {
        return pYear;
    }

    public void setpYear(String pYear) {
        this.pYear = pYear;
    }
}
