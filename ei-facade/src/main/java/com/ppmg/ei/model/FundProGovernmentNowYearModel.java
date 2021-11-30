package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

/**
 *
 * 企业财务状况，本年度
 * @return
 * @author zhaokuiyu
 * @date 2019/12/4 9:20
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentNowYearModel extends BaseModel {
    //职工总人数
    private Double staffNum7;
    //科技研发人数
    private Double rdStaffNum7;
    //总资产
    private Double totalAssets7;
    //总负债
    private Double totalLiabilities7;
    //所有者权益
    private Double ownerEquity7;
    //总收入
    private Double totalIncome7;
    //利润总额
    private Double totalProfit7;
    //净利润
    private Double netProfit7;
    //研发费用
    private Double rdFee7;
    //上缴税费
    private Double payFee7;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getStaffNum7() {
        return staffNum7;
    }

    public void setStaffNum7(Double staffNum7) {
        this.staffNum7 = staffNum7;
    }

    public Double getRdStaffNum7() {
        return rdStaffNum7;
    }

    public void setRdStaffNum7(Double rdStaffNum7) {
        this.rdStaffNum7 = rdStaffNum7;
    }

    public Double getTotalAssets7() {
        return totalAssets7;
    }

    public void setTotalAssets7(Double totalAssets7) {
        this.totalAssets7 = totalAssets7;
    }

    public Double getTotalLiabilities7() {
        return totalLiabilities7;
    }

    public void setTotalLiabilities7(Double totalLiabilities7) {
        this.totalLiabilities7 = totalLiabilities7;
    }

    public Double getOwnerEquity7() {
        return ownerEquity7;
    }

    public void setOwnerEquity7(Double ownerEquity7) {
        this.ownerEquity7 = ownerEquity7;
    }

    public Double getTotalIncome7() {
        return totalIncome7;
    }

    public void setTotalIncome7(Double totalIncome7) {
        this.totalIncome7 = totalIncome7;
    }

    public Double getTotalProfit7() {
        return totalProfit7;
    }

    public void setTotalProfit7(Double totalProfit7) {
        this.totalProfit7 = totalProfit7;
    }

    public Double getNetProfit7() {
        return netProfit7;
    }

    public void setNetProfit7(Double netProfit7) {
        this.netProfit7 = netProfit7;
    }

    public Double getRdFee7() {
        return rdFee7;
    }

    public void setRdFee7(Double rdFee7) {
        this.rdFee7 = rdFee7;
    }

    public Double getPayFee7() {
        return payFee7;
    }

    public void setPayFee7(Double payFee7) {
        this.payFee7 = payFee7;
    }
}
