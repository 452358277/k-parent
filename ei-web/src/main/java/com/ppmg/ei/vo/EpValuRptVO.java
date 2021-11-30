package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class EpValuRptVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 报告人 */
	private String rptBy;

	/** 报告日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rptDt;

	/** 报告标题 */
	private String rptTitle;

	/** 分析报告附件 */
	private String rptFile;

	/** 出资主体ID */
	private String afterFunderId;

	/** 企业ID */
	private String objectId;

	/** 合同附件List */
	private List fileLists;

	/** 报告总结 */
	private String rptDesc;

	public String getRptDesc() {
		return rptDesc;
	}

	public void setRptDesc(String rptDesc) {
		this.rptDesc = rptDesc;
	}

	public List getFileLists() {
		return fileLists;
	}

	public void setFileLists(List fileLists) {
		this.fileLists = fileLists;
	}


	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getRptBy() {
		return rptBy;
	}

	public void setRptBy(String rptBy) {
		this.rptBy = rptBy;
	}

	public Date getRptDt() {
		return rptDt;
	}

	public void setRptDt(Date rptDt) {
		this.rptDt = rptDt;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}


	public String getRptFile() {
		return rptFile;
	}

	public void setRptFile(String rptFile) {
		this.rptFile = rptFile;
	}

	public String getAfterFunderId() {
		return afterFunderId;
	}

	public void setAfterFunderId(String afterFunderId) {
		this.afterFunderId = afterFunderId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}


}