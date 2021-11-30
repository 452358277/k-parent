package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class BdTFundAttaModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 附件ID */
	private String attaId;

	/** 基金ID */
	private String fundId;

	/** 附件类型 */
	private String attaType;

	/** 附件文件 */
	private String attaFile;

	/** 状态 */
	private String status;

	private String isFile;

	private AppUserModel userModel;

	private String attaFileName;
	
	private String attaFileUrl;
	
	private String fileType;
	
	private String createName;

	private String fileId;

	private String fileSource;

	/** 是否可编辑(1：不可编辑；0可编辑) */
	private String editable;

	private String itemId;

	/** 创建时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date uplodeDate;

	private String keys;

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getAttaId() {
		return attaId;
	}

	public void setAttaId(String attaId) {
		this.attaId = attaId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getAttaType() {
		return attaType;
	}

	public void setAttaType(String attaType) {
		this.attaType = attaType;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AppUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(AppUserModel userModel) {
		this.userModel = userModel;
	}

	public String getIsFile() {
		return isFile;
	}

	public void setIsFile(String isFile) {
		this.isFile = isFile;
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

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public Date getUplodeDate() {
		return uplodeDate;
	}

	public void setUplodeDate(Date uplodeDate) {
		this.uplodeDate = uplodeDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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
}