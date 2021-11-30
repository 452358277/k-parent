package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FounderFileInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 文件ID */
	private long fileId;

	/** 文件名称 */
	private String fileName;

	/** 原始文件名称 */
	private String fileOldName;

	/** 文件存放路径 */
	private String filePath;

	/** 文件大小 */
	private String fileSize;

	/** 文件类型 */
	private String fileType;

	/** 文件描述 */
	private String fileDesc;

	/** 业务表名 */
	private String bizTableName;

	/** 业务表主键 */
	private String bizTablePk;

	/** 文件所对应业务表中的字段名 */
	private String fieldName;

	/** 文件所对应业务表中的字段值 */
	private String fieldToken;

	/** 排序号 */
	private Long serialNo;

	/** 备注 */
	private String remark;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
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

}