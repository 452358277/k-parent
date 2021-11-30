package com.ppmg.ei.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class BdTFundAttaVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 附件ID */
	private String attaId;

	/** 附件类型名称 */
	private String attaType;

	/** 文件名 */
	private String attaFileName;

	/** 文件下载地址 */
	private String attaFileUrl;

	/** 文件类别 */
	private String fileType;

	/** 上传人 */
	private String createName;

	private String createBy;

	/** 上传（创建）时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date uplodeDate;

	/** 是否可编辑(1：不可编辑；0可编辑) */
	private String editable;

	private String isFile;

	/** 文件ID */
	private String fileId;

	/** 文件来源 */
	private String fileSource;

	private String itemId;

	private String keys;

	private String fundId;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

	public String getAttaFileName() {
		return attaFileName;
	}

	public void setAttaFileName(String attaFileName) {
		this.attaFileName = attaFileName;
	}

	public String getAttaFileUrl() {
		return attaFileUrl;
	}

	public void setAttaFileUrl(String attaFileUrl) {
		this.attaFileUrl = attaFileUrl;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getIsFile() {
		return isFile;
	}

	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}

	public Date getUplodeDate() {
		return uplodeDate;
	}

	public void setUplodeDate(Date uplodeDate) {
		this.uplodeDate = uplodeDate;
	}

	public String getAttaId() {
		return attaId;
	}

	public void setAttaId(String attaId) {
		this.attaId = attaId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getAttaType() {
		return attaType;
	}

	public void setAttaType(String attaType) {
		this.attaType = attaType;
	}


}