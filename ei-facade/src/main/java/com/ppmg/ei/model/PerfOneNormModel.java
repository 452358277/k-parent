package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

/**
 * 一级指标
 */
public class PerfOneNormModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    //主键id
    private String oNormId;
    //主键关联id
    private String pPerId;
    //一级指标名称
    private String oOneName;
    //一级指标分值（保留两位小数）
    private Double oOneScore;

    private String titleOne;

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    private List<PerfSecondNormModel> perfSecondNormModel;

    public List<PerfSecondNormModel> getPerfSecondNormModel() {
        return perfSecondNormModel;
    }

    public void setPerfSecondNormModel(List<PerfSecondNormModel> perfSecondNormModel) {
        this.perfSecondNormModel = perfSecondNormModel;
    }


    public String getoNormId() {
        return oNormId;
    }

    public void setoNormId(String oNormId) {
        this.oNormId = oNormId;
    }

    public String getpPerId() {
        return pPerId;
    }

    public void setpPerId(String pPerId) {
        this.pPerId = pPerId;
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

    public void setoOneScore(Double oOoneScore) {
        this.oOneScore = oOoneScore;
    }
}
