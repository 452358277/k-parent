package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class BussniessResultModel extends BaseModel {
    //基金名称
    private String name ;

    //省内金额
    private String sumjs;
    //省内项目个数
    private String countJs;
    //省外金额
    private String sumNoJs;
    //省外项目个数
    private String countNoJs;
    //农业金额
    private String sumNy;
    //农业项目个数
    private String countNy;
    //新材料金额
    private String sumCl;
    //新材项目个数
    private String countCl;
   //智能制造金额
    private String sumZz;
    //智能制造目个数
    private String countZz;
    //基金类型
    private String platProp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSumjs() {
        return sumjs;
    }

    public void setSumjs(String sumjs) {
        this.sumjs = sumjs;
    }

    public String getCountJs() {
        return countJs;
    }

    public void setCountJs(String countJs) {
        this.countJs = countJs;
    }

    public String getSumNoJs() {
        return sumNoJs;
    }

    public void setSumNoJs(String sumNoJs) {
        this.sumNoJs = sumNoJs;
    }

    public String getCountNoJs() {
        return countNoJs;
    }

    public void setCountNoJs(String countNoJs) {
        this.countNoJs = countNoJs;
    }

    public String getSumNy() {
        return sumNy;
    }

    public void setSumNy(String sumNy) {
        this.sumNy = sumNy;
    }

    public String getCountNy() {
        return countNy;
    }

    public void setCountNy(String countNy) {
        this.countNy = countNy;
    }

    public String getSumCl() {
        return sumCl;
    }

    public void setSumCl(String sumCl) {
        this.sumCl = sumCl;
    }

    public String getCountCl() {
        return countCl;
    }

    public void setCountCl(String countCl) {
        this.countCl = countCl;
    }

    public String getSumZz() {
        return sumZz;
    }

    public void setSumZz(String sumZz) {
        this.sumZz = sumZz;
    }

    public String getCountZz() {
        return countZz;
    }

    public void setCountZz(String countZz) {
        this.countZz = countZz;
    }

    public String getPlatProp() {
        return platProp;
    }

    public void setPlatProp(String platProp) {
        this.platProp = platProp;
    }
}
