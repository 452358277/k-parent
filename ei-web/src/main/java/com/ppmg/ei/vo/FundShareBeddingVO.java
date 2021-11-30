package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class FundShareBeddingVO extends BaseVO {

    private String regName;

    private Double inveAmount;

    private Double paymentAmount;

    private Double bjjAnount;

    private Double shAnount;

    private String percent;

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public Double getInveAmount() {
        return inveAmount;
    }

    public void setInveAmount(Double inveAmount) {
        this.inveAmount = inveAmount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getBjjAnount() {
        return bjjAnount;
    }

    public void setBjjAnount(Double bjjAnount) {
        this.bjjAnount = bjjAnount;
    }

    public Double getShAnount() {
        return shAnount;
    }

    public void setShAnount(Double shAnount) {
        this.shAnount = shAnount;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
