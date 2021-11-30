package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PerfManageFundVO extends BaseVO {
    /** 主键 */
    private String id;

    /** 主表id */
    private String pPerId;

    /** 基金id */
    private String fundId;

    /** 基金名称 */
    private String fundName;

    /** 考核状态 */
    private String status;
    private String statusName;

    /** 考核年度*/
    private String yearCount;

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYearCount() {
        return yearCount;
    }

    public void setYearCount(String yearCount) {
        this.yearCount = yearCount;
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
}
