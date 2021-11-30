package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundRelateInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String relateId;

	/** 基金ID */
	private String fundId;

	/** 资料类型 */
	private String infoType;

	/** 资料附件 */
	private String attaFile;

	/** 归档年月 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date archDt;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 备注 */
	private String remark;

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public Date getArchDt() {
		return archDt;
	}

	public void setArchDt(Date archDt) {
		this.archDt = archDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}