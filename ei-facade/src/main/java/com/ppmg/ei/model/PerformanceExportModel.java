package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

/**
 * 基金绩效考核导出
 */
public class PerformanceExportModel extends BaseModel{
    private static final long serialVersionUID = 1L;

    //基金主键id
    private String fundId;

    /** 基金名称 */
    private String fundName;

    /** 基金绩效id**/
    private String pPerID;

    //基金绩效名称
    private String pName;

    //基金绩效名称
    private String pYear;

    //一级指标名称
    private String oOneName;

    //一级指标分值（保留两位小数）
    private Double oOneScore;

    //二级指标名称
    private String sTwoName;

    //二级指标分值（保留两位小数）
    private Double sTwoScore;

    //三级指标名称
    private String tThreeName;

    //三级指标分值（保留两位小数）
    private Double tThreeScore;

    //评分细则-建议
    private String tSuggest;

    //说明
    private String tExplain;

    //自评（保留两位小数）
    private String psdOneselfScore;

    //审核（保留两位小数）
    private String psdAuditScore;

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

    public String getoOneName() {
        return oOneName;
    }

    public void setoOneName(String oOneName) {
        this.oOneName = oOneName;
    }

    public Double getoOneScore() {
        return oOneScore;
    }

    public void setoOneScore(Double oOneScore) {
        this.oOneScore = oOneScore;
    }

    public String getsTwoName() {
        return sTwoName;
    }

    public void setsTwoName(String sTwoName) {
        this.sTwoName = sTwoName;
    }

    public Double getsTwoScore() {
        return sTwoScore;
    }

    public void setsTwoScore(Double sTwoScore) {
        this.sTwoScore = sTwoScore;
    }

    public String gettThreeName() {
        return tThreeName;
    }

    public void settThreeName(String tThreeName) {
        this.tThreeName = tThreeName;
    }

    public Double gettThreeScore() {
        return tThreeScore;
    }

    public void settThreeScore(Double tThreeScore) {
        this.tThreeScore = tThreeScore;
    }

    public String gettSuggest() {
        return tSuggest;
    }

    public void settSuggest(String tSuggest) {
        this.tSuggest = tSuggest;
    }

    public String gettExplain() {
        return tExplain;
    }

    public void settExplain(String tExplain) {
        this.tExplain = tExplain;
    }

    public String getPsdOneselfScore() {
        return psdOneselfScore;
    }

    public void setPsdOneselfScore(String psdOneselfScore) {
        this.psdOneselfScore = psdOneselfScore;
    }

    public String getPsdAuditScore() {
        return psdAuditScore;
    }

    public void setPsdAuditScore(String psdAuditScore) {
        this.psdAuditScore = psdAuditScore;
    }

    public String getpYear() {
        return pYear;
    }

    public void setpYear(String pYear) {
        this.pYear = pYear;
    }
}
