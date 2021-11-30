package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

public class ProjContractInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    private String id;

    /**
     * null
     */
    private String projId;

    /**
     * null
     */
    private String remark;

    /**
     * null
     */
    private String projName;

    /**
     * null
     */
    private String inveName;

    /**
     * null
     */
    private String inveId;

    /**
     * null
     */
    private String projFile;

    /**
     * null
     */
    private String founceInfo;

    private String status;

    private String processInstId;

    private List<ProjContractFileModel> projContractFileList;


    private Double signAmount;

    private String signAmountCurr;

    private String fileInfoId;


    public Double getSignAmount() {
        return signAmount;
    }

    public String getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(String fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public void setSignAmount(Double signAmount) {
        this.signAmount = signAmount;
    }

    public String getSignAmountCurr() {
        return signAmountCurr;
    }

    public void setSignAmountCurr(String signAmountCurr) {
        this.signAmountCurr = signAmountCurr;
    }

    public List<ProjContractFileModel> getProjContractFileList() {
        return projContractFileList;
    }

    public void setProjContractFileList(List<ProjContractFileModel> projContractFileList) {
        this.projContractFileList = projContractFileList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    public String getProjFile() {
        return projFile;
    }

    public void setProjFile(String projFile) {
        this.projFile = projFile;
    }

    public String getFounceInfo() {
        return founceInfo;
    }

    public void setFounceInfo(String founceInfo) {
        this.founceInfo = founceInfo;
    }

}