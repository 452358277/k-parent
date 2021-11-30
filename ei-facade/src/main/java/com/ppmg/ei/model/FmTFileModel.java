package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FmTFileModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private long pkFile;

	/** 文件原始名称 */
	private String fileName;

	/** 文件存放路径，包含修改后的名称 */
	private String filePath;

	/** 文件上传时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date uploadTime;

	/** 附件数据类型(DATA_PK+DATA_TYPE确认唯一性) */
	private String dataType;

	/** 关联表关联字段的数据 */
	private String dataPk;

	/** 文件大小 */
	private String fileSize;

	/** 文件类型 */
	private String fileType;

	/** 消息开关(1:接收,0:不接收) */
	private String messageSwitch;

	public long getPkFile() {
		return pkFile;
	}

	public void setPkFile(long pkFile) {
		this.pkFile = pkFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataPk() {
		return dataPk;
	}

	public void setDataPk(String dataPk) {
		this.dataPk = dataPk;
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

	public String getMessageSwitch() {
		return messageSwitch;
	}

	public void setMessageSwitch(String messageSwitch) {
		this.messageSwitch = messageSwitch;
	}

}