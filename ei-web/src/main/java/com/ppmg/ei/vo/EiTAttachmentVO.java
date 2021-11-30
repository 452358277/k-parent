package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class EiTAttachmentVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fileId;

	/** 原始文件名称 */
	private String fileOldName;

	/** 路径 */
	private String filePath;

	/** 附件大小（KB） */
	private String fileSize;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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


}