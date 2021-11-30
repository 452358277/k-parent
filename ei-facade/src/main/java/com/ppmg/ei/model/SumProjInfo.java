package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class SumProjInfo extends BaseModel {

    private String projId;
   //项目名称
    private String projName;
   //本基金金额
    private String bjjAmount;
   //社会金额
    private String shAnount;
   //
    private String spv;
//项目类型
    private String type;
    //基金的类型
    private String jbType;
//行业
    private String hy;
//放大比例
    private String persent;

    public String getPersent() {
        return persent;
    }

    public void setPersent(String persent) {
        this.persent = persent;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getBjjAmount() {
        return bjjAmount;
    }

    public void setBjjAmount(String bjjAmount) {
        this.bjjAmount = bjjAmount;
    }

    public String getShAnount() {
        return shAnount;
    }

    public void setShAnount(String shAnount) {
        this.shAnount = shAnount;
    }

    public String getSpv() {
        return spv;
    }

    public void setSpv(String spv) {
        this.spv = spv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJbType() {
        return jbType;
    }

    public void setJbType(String jbType) {
        this.jbType = jbType;
    }
}
