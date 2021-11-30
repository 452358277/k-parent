package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ImportViewModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** PK-导入ID */
	private String importId;

	/** 导入名称 */
	private String importName;

	/** 导入时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date impotTime;

	/** 导入季度 */
	private String importQuarter;

	/** 导入附件，附件token */
	private String descFile;

	/** 创建时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date createTime;

	/** 附件存入名 */
	private String fileName;

	/** sessionKey */
	private String sessionkey;

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getImportName() {
		return importName;
	}

	public void setImportName(String importName) {
		this.importName = importName;
	}

	public Date getImpotTime() {
		return impotTime;
	}

	public void setImpotTime(Date impotTime) {
		this.impotTime = impotTime;
	}

	public String getImportQuarter() {
		return importQuarter;
	}

	public void setImportQuarter(String importQuarter) {
		this.importQuarter = importQuarter;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

}