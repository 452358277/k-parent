package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundProGovernmentModel extends BaseModel {
    //区域
    private String isJs;
    //基金类型
    private String platProp;
    //基金形式
    private String fundStruct;
    //企业标签
    private String labelId;
    //基金属性,专项子基金，市场化子基金
    private String fundGenre;
    //项目个数
    private Double leftCount;
    //投资金额
    private Double leftAmount;
    //退出个数
    private Double quitCount;
    //回收金额
    private Double quitAmount;

    public String getIsJs() {
        return isJs;
    }

    public void setIsJs(String isJs) {
        this.isJs = isJs;
    }

    public String getPlatProp() {
        return platProp;
    }

    public void setPlatProp(String platProp) {
        this.platProp = platProp;
    }

    public String getFundStruct() {
        return fundStruct;
    }

    public void setFundStruct(String fundStruct) {
        this.fundStruct = fundStruct;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public Double getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Double leftCount) {
        this.leftCount = leftCount;
    }

    public Double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(Double leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Double getQuitCount() {
        return quitCount;
    }

    public void setQuitCount(Double quitCount) {
        this.quitCount = quitCount;
    }

    public Double getQuitAmount() {
        return quitAmount;
    }

    public void setQuitAmount(Double quitAmount) {
        this.quitAmount = quitAmount;
    }

    public String getFundGenre() {
        return fundGenre;
    }

    public void setFundGenre(String fundGenre) {
        this.fundGenre = fundGenre;
    }
}
