package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

public class ConditionItemModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    //合作征集对象ID
    private String itemId;
    //征集信息ID
    private String infoId;
    //基金管理人名称
    private String fundAdminName;
    //基金管理人ID
    private String fundAdminId;
    //子基金名称
    private String fundName;
    //子基金ID
    private String fundId;
    //状态
    private String status;
    //是否符合
    private String isFit;
    private String isFitName;
    //文件类型
    private String fileType;
    //附件说明
    private String attaDetail;
    //附件
    private String attaFile;
    //基金规模
    private Double raiseAmount;
    //申请金额
    private Double fundAdminApplyMoney;

    public String getIsFitName() {
        return isFitName;
    }

    public void setIsFitName(String isFitName) {
        this.isFitName = isFitName;
    }

    public Double getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(Double raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    public Double getFundAdminApplyMoney() {
        return fundAdminApplyMoney;
    }

    public void setFundAdminApplyMoney(Double fundAdminApplyMoney) {
        this.fundAdminApplyMoney = fundAdminApplyMoney;
    }

    //征集材料
    private List<ConditionFileModel> conditionFileModels;

    public List<ConditionFileModel> getConditionFileModels() {
        return conditionFileModels;
    }

    public void setConditionFileModels(List<ConditionFileModel> conditionFileModels) {
        this.conditionFileModels = conditionFileModels;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getFundAdminName() {
        return fundAdminName;
    }

    public void setFundAdminName(String fundAdminName) {
        this.fundAdminName = fundAdminName;
    }

    public String getFundAdminId() {
        return fundAdminId;
    }

    public void setFundAdminId(String fundAdminId) {
        this.fundAdminId = fundAdminId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsFit() {
        return isFit;
    }

    public void setIsFit(String isFit) {
        this.isFit = isFit;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAttaDetail() {
        return attaDetail;
    }

    public void setAttaDetail(String attaDetail) {
        this.attaDetail = attaDetail;
    }

    public String getAttaFile() {
        return attaFile;
    }

    public void setAttaFile(String attaFile) {
        this.attaFile = attaFile;
    }
}
