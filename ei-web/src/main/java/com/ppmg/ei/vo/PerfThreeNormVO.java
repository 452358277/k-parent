package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "三级指标", description = "三级指标")
public class PerfThreeNormVO extends BaseVO {
    //主键id
    private String tNormId;
    //二级指标表关联id
    private String sNormId;
    //三级指标名称
    private String tThreeName;
    //三级指标分值（保留两位小数）
    private Double tThreeScore;
    //评分细则-建议
    private String tSuggest;
    //说明
    private String tExplain;
    //审核佐证材料
    private String clFile;
    //自评佐证材料
    private String clFileOneself;

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

    public String getPsdOneselfScore() {
        return psdOneselfScore;
    }

    public void setPsdOneselfScore(String psdOneselfScore) {
        this.psdOneselfScore = psdOneselfScore;
    }

    //审核得分
    private String psdAuditScore;
    //自评得分
    private String psdOneselfScore;
    private String psdTNormId;

    public String getPsdTNormId() {
        return psdTNormId;
    }

    public void setPsdTNormId(String psdTNormId) {
        this.psdTNormId = psdTNormId;
    }

    public String getPsdAuditScore() {
        return psdAuditScore;
    }

    public void setPsdAuditScore(String psdAuditScore) {
        this.psdAuditScore = psdAuditScore;
    }

    public String gettNormId() {
        return tNormId;
    }

    public void settNormId(String tNormId) {
        this.tNormId = tNormId;
    }

    public String getsNormId() {
        return sNormId;
    }

    public void setsNormId(String sNormId) {
        this.sNormId = sNormId;
    }

    public String gettThreeName() {
        return tThreeName;
    }

    public void settThreeName(String tThreeName) {
        this.tThreeName = tThreeName;
    }

    public Double gettThreeScore() {
        return tThreeScore;
    }

    public void settThreeScore(Double tThreeScore) {
        this.tThreeScore = tThreeScore;
    }

    public String gettSuggest() {
        return tSuggest;
    }

    public void settSuggest(String tSuggest) {
        this.tSuggest = tSuggest;
    }

    public String gettExplain() {
        return tExplain;
    }

    public void settExplain(String tExplain) {
        this.tExplain = tExplain;
    }
}
