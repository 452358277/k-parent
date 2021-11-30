package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjAppInfoModel;
import com.ppmg.ei.model.ProjMemberModel;

public class BussinessInfoVO extends BaseVO {

    private ProjAppInfoModel projAppInfoModel;
    //企业名称存在这里
    private String remark;

    private String qkIndustry;

    private String sevenIndustry;

    public ProjAppInfoModel getProjAppInfoModel() {
        return projAppInfoModel;
    }

    public void setProjAppInfoModel(ProjAppInfoModel projAppInfoModel) {
        this.projAppInfoModel = projAppInfoModel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQkIndustry() {
        return qkIndustry;
    }

    public void setQkIndustry(String qkIndustry) {
        this.qkIndustry = qkIndustry;
    }

    public String getSevenIndustry() {
        return sevenIndustry;
    }

    public void setSevenIndustry(String sevenIndustry) {
        this.sevenIndustry = sevenIndustry;
    }
}
