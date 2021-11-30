package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FpAttachmentModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String fileId;

	/** null */
	private String bizTableName;

	/** null */
	private String bizTablePk;

	/** null */
	private String fieldName;

	/** null */
	private String fieldToken;

	/** null */
	private String fileName;

	/** null */
	private String fileOldName;

	/** null */
	private String filePath;

	/** null */
	private String fileSize;

	/** null */
	private String fileType;

	/** null */
	private String fileDesc;

	/** null */
	private Long serialNo;

	/** null */
	private String remark;

	/** 是否删除 码值9013 1：是，0：否 */
	private String deleteFlag;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getBizTableName() {
		return bizTableName;
	}

	public void setBizTableName(String bizTableName) {
		this.bizTableName = bizTableName;
	}

	public String getBizTablePk() {
		return bizTablePk;
	}

	public void setBizTablePk(String bizTablePk) {
		this.bizTablePk = bizTablePk;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldToken() {
		return fieldToken;
	}

	public void setFieldToken(String fieldToken) {
		this.fieldToken = fieldToken;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOldName() {
		return fileOldName;
	}

	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}