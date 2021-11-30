package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundConsModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String consId;

	/** 基金编号 */
	private String fundId;

	/** 归档年月 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date archDt;

	/** 章程及修正内容  */
	private String consContent;

	/** 附件 */
	private String consFiles;

	/** 状态（0：正常，1：删除） */
	private String status;

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Date getArchDt() {
		return archDt;
	}

	public void setArchDt(Date archDt) {
		this.archDt = archDt;
	}

	public String getConsContent() {
		return consContent;
	}

	public void setConsContent(String consContent) {
		this.consContent = consContent;
	}

	public String getConsFiles() {
		return consFiles;
	}

	public void setConsFiles(String consFiles) {
		this.consFiles = consFiles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}