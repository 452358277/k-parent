package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ProjFilesModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private String fileId;

	/** 项目 */
	private String projId;

	/** 材料名 */
	private String fileName;

	/** 附件 */
	private String fileAtta;

	/** 说明 */
	private String fileDesc;

	/** 状态 */
	private String status;

	/** 文件类型:1:立项;2:决策 */
	private String fileType;

	/** 发生时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date occurDate;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileAtta() {
		return fileAtta;
	}

	public void setFileAtta(String fileAtta) {
		this.fileAtta = fileAtta;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

}