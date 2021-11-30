package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

/**
 *
 * 企业财务状况，投资前上年度
 * @return
 * @author zhaokuiyu
 * @date 2019/12/4 9:20
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentBeforYearModel extends BaseModel {
    //职工总人数
    private Double staffNum;
    //科技研发人数
    private Double rdStaffNum;
    //总资产
    private Double totalAssets;
    //总负债
    private Double totalLiabilities;
    //所有者权益
    private Double ownerEquity;
    //总收入
    private Double totalIncome;
    //利润总额
    private Double totalProfit;
    //净利润
    private Double netProfit;
    //研发费用
    private Double rdFee;
    //上缴税费
    private Double payFee;

    public Double getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Double staffNum) {
        this.staffNum = staffNum;
    }

    public Double getRdStaffNum() {
        return rdStaffNum;
    }

    public void setRdStaffNum(Double rdStaffNum) {
        this.rdStaffNum = rdStaffNum;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public Double getOwnerEquity() {
        return ownerEquity;
    }

    public void setOwnerEquity(Double ownerEquity) {
        this.ownerEquity = ownerEquity;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getRdFee() {
        return rdFee;
    }

    public void setRdFee(Double rdFee) {
        this.rdFee = rdFee;
    }

    public Double getPayFee() {
        return payFee;
    }

    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }
}
