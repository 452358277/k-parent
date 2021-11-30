package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FundRecordInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 备案地 */
	private String rcdAdd;

	/** 备案号 */
	private String rcdNo;

	/** 备案状态（0：正常，1：准备中，2：已取消） */
	private String rcdSts;

	/** 备案日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date rcdDt;

	/** 备注 */
	private String remark;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getRcdAdd() {
		return rcdAdd;
	}

	public void setRcdAdd(String rcdAdd) {
		this.rcdAdd = rcdAdd;
	}

	public String getRcdNo() {
		return rcdNo;
	}

	public void setRcdNo(String rcdNo) {
		this.rcdNo = rcdNo;
	}

	public String getRcdSts() {
		return rcdSts;
	}

	public void setRcdSts(String rcdSts) {
		this.rcdSts = rcdSts;
	}

	public Date getRcdDt() {
		return rcdDt;
	}

	public void setRcdDt(Date rcdDt) {
		this.rcdDt = rcdDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}