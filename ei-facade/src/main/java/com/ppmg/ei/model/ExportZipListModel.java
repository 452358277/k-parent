package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

/**
 * zip导出结果
 * @author null
 * @date 2019-08-13 10:53
 */
public class ExportZipListModel extends BaseModel {

	private static final long serialVersionUID = 1L;
	//主键id
	private String fundId;
	private String fundName;
	private String clFileOneself;
	private String fileName;
	private String fileOldName;
	private String filePath;

	public String getClFileOneself() {
		return clFileOneself;
	}

	public void setClFileOneself(String clFileOneself) {
		this.clFileOneself = clFileOneself;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
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
}
