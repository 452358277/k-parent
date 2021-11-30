package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class FundProGovernmentVO extends BaseVO {
    //区域
    private String isJs;
    private String isJsName;
    //基金类型
    private String platProp;
    private String platPropName;
    //基金形式
    private String fundStruct;
    private String fundStructName;
    //企业标签
    private String labelId;
    private String labelIdName;
    //基金属性,专项子基金，市场化子基金
    private String fundGenre;
    private String fundGenreName;
    //项目个数
    private Double leftCount;
    //投资金额
    private Double leftAmount;
    //投资金额(备用)
    private String leftAmountStr;
    //退出个数
    private Double quitCount;
    //回收金额
    private Double quitAmount;
    //回收金额（备用）
    private String quitAmountStr;

    public String getIsJs() {
        return isJs;
    }

    public void setIsJs(String isJs) {
        this.isJs = isJs;
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

    public String getLeftAmountStr() {
        return leftAmountStr;
    }

    public void setLeftAmountStr(String leftAmountStr) {
        this.leftAmountStr = leftAmountStr;
    }

    public String getQuitAmountStr() {
        return quitAmountStr;
    }

    public void setQuitAmountStr(String quitAmountStr) {
        this.quitAmountStr = quitAmountStr;
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

    public String getIsJsName() {
        return isJsName;
    }

    public void setIsJsName(String isJsName) {
        this.isJsName = isJsName;
    }

    public String getPlatProp() {
        return platProp;
    }

    public void setPlatProp(String platProp) {
        this.platProp = platProp;
    }

    public String getPlatPropName() {
        return platPropName;
    }

    public void setPlatPropName(String platPropName) {
        this.platPropName = platPropName;
    }

    public String getFundStruct() {
        return fundStruct;
    }

    public void setFundStruct(String fundStruct) {
        this.fundStruct = fundStruct;
    }

    public String getFundStructName() {
        return fundStructName;
    }

    public void setFundStructName(String fundStructName) {
        this.fundStructName = fundStructName;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelIdName() {
        return labelIdName;
    }

    public void setLabelIdName(String labelIdName) {
        this.labelIdName = labelIdName;
    }

    public String getFundGenre() {
        return fundGenre;
    }

    public void setFundGenre(String fundGenre) {
        this.fundGenre = fundGenre;
    }

    public String getFundGenreName() {
        return fundGenreName;
    }

    public void setFundGenreName(String fundGenreName) {
        this.fundGenreName = fundGenreName;
    }
}
