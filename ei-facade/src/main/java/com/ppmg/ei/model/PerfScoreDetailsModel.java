package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class PerfScoreDetailsModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    //主键id
    private String psdId;
    //关联评分表id
    private String psId;
    //自评（保留两位小数）
    private String psdOneselfScore;
    //审核（保留两位小数）
    private String psdAuditScore;

    public String getPsdId() {
        return psdId;
    }

    public void setPsdId(String psdId) {
        this.psdId = psdId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getPsdOneselfScore() {
        return psdOneselfScore;
    }

    public void setPsdOneselfScore(String psdOneselfScore) {
        this.psdOneselfScore = psdOneselfScore;
    }

    public String getPsdAuditScore() {
        return psdAuditScore;
    }

    public void setPsdAuditScore(String psdAuditScore) {
        this.psdAuditScore = psdAuditScore;
    }
}
