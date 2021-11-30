package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

import java.util.List;

public class BdTFitRegulationSumVO extends BaseVO {

    private  double sumSignAmount;

    private double signAmount;

    private double sumIntendedAmount;

    private  double noJssumIntendedAmount;

    private double noIsTwelve;

    private double noIsMfield;

    private List<BdTFitRegulationSelfVO> listvo;

    public double getSumSignAmount() {
        return sumSignAmount;
    }

    public void setSumSignAmount(double sumSignAmount) {
        this.sumSignAmount = sumSignAmount;
    }

    public double getSignAmount() {
        return signAmount;
    }

    public void setSignAmount(double signAmount) {
        this.signAmount = signAmount;
    }

    public double getSumIntendedAmount() {
        return sumIntendedAmount;
    }

    public void setSumIntendedAmount(double sumIntendedAmount) {
        this.sumIntendedAmount = sumIntendedAmount;
    }

    public double getNoJssumIntendedAmount() {
        return noJssumIntendedAmount;
    }

    public void setNoJssumIntendedAmount(double noJssumIntendedAmount) {
        this.noJssumIntendedAmount = noJssumIntendedAmount;
    }

    public double getNoIsTwelve() {
        return noIsTwelve;
    }

    public void setNoIsTwelve(double noIsTwelve) {
        this.noIsTwelve = noIsTwelve;
    }

    public double getNoIsMfield() {
        return noIsMfield;
    }

    public void setNoIsMfield(double noIsMfield) {
        this.noIsMfield = noIsMfield;
    }

    public List<BdTFitRegulationSelfVO> getListvo() {
        return listvo;
    }

    public void setListvo(List<BdTFitRegulationSelfVO> listvo) {
        this.listvo = listvo;
    }
}
