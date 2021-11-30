package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
/**
 * 评测分数表详情
 */
public class PerfScoreModelDetails extends BaseModel {
    private static final long serialVersionUID = 1L;
    //主键id
    private String psdId;
    //关联评分表id
    private String psId;
    //自评（保留两位小数）
    private Double psdOneselfScore;
    //审核（保留两位小数）
    private Double psdAuditScore;
    //三级指标关联id
    private String tNormId;

    //审核佐证材料
    private String clFile;

    //自评佐证材料
    private String clFileOneself;

    private Double psOneselfScore;

    public Double getPsOneselfScore() {
        return psOneselfScore;
    }

    public void setPsOneselfScore(Double psOneselfScore) {
        this.psOneselfScore = psOneselfScore;
    }

    public String getClFileOneself() {
        return clFileOneself;
    }

    public void setClFileOneself(String clFileOneself) {
        this.clFileOneself = clFileOneself;
    }

    public String getClFile() {
        return clFile;
    }

    public void setClFile(String clFile) {
        this.clFile = clFile;
    }

    public String gettNormId() {
        return tNormId;
    }

    public void settNormId(String tNormId) {
        this.tNormId = tNormId;
    }

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

    public Double getPsdOneselfScore() {
        return psdOneselfScore;
    }

    public void setPsdOneselfScore(Double psdOneselfScore) {
        this.psdOneselfScore = psdOneselfScore;
    }

    public Double getPsdAuditScore() {
        return psdAuditScore;
    }

    public void setPsdAuditScore(Double psdAuditScore) {
        this.psdAuditScore = psdAuditScore;
    }
}
