package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "二级指标", description = "二级指标")
public class PerfSecondNormVO extends BaseVO {
    //主键id
    private String sNormId;
    //一级指标关联id
    private String oNormId;
    //二级指标名称
    private String sTwoName;
    //二级指标分值（保留两位小数）
    private Double sTwoScore;
    //三级指标数
    private String threeCount;

    public String getThreeCount() {
        return threeCount;
    }

    public void setThreeCount(String threeCount) {
        this.threeCount = threeCount;
    }

    public String getsNormId() {
        return sNormId;
    }

    public void setsNormId(String sNormId) {
        this.sNormId = sNormId;
    }

    public String getoNormId() {
        return oNormId;
    }

    public void setoNormId(String oNormId) {
        this.oNormId = oNormId;
    }

    public String getsTwoName() {
        return sTwoName;
    }

    public void setsTwoName(String sTwoName) {
        this.sTwoName = sTwoName;
    }

    public Double getsTwoScore() {
        return sTwoScore;
    }

    public void setsTwoScore(Double sTwoScore) {
        this.sTwoScore = sTwoScore;
    }
}
