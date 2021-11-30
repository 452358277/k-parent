package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "一级指标", description = "一级指标")
public class PerfOneNormVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    //主键id
    private String oNormId;
    //主键关联id
    private String pPerId;
    //一级指标名称
    private String oOneName;
    //一级指标分值（保留两位小数）
    private Double oOneScore;

    public String getoNormId() {
        return oNormId;
    }

    public void setoNormId(String oNormId) {
        this.oNormId = oNormId;
    }

    public String getpPerId() {
        return pPerId;
    }

    public void setpPerId(String pPerId) {
        this.pPerId = pPerId;
    }

    public String getoOneName() {
        return oOneName;
    }

    public void setoOneName(String oOneName) {
        this.oOneName = oOneName;
    }

    public Double getoOneScore() {
        return oOneScore;
    }

    public void setoOneScore(Double oOoneScore) {
        this.oOneScore = oOoneScore;
    }
}
