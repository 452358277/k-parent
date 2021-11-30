package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class ProjContractFileQuitUtilModel extends BaseModel {
    /** 文件类别 */
    private String fileType;
    private String fileTypeName;

    /** 文件名称 */
    private String fileTitle;

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
}
