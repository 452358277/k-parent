package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EiTAttachmentModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fileId;

	/** 功能表名 */
	private String bizTableName;

	/** 功能号 */
	private String bizTablePk;

	/** 业务表字段名 */
	private String fieldName;

	/** 业务表字段值 */
	private String fieldToken;

	/** 附件名 */
	private String fileName;

	/** 原始文件名称 */
	private String fileOldName;

	/** 路径 */
	private String filePath;

	/** 附件大小（KB） */
	private String fileSize;

	/** 附件类型 */
	private String fileType;

	/** 附件描述 */
	private String fileDesc;

	/** 排序号 */
	private Long serialNo;

	/** 备注 */
	private String remark;

	/** 文件来源 */
	private String fileSource;

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

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

}