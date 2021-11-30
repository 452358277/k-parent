package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class PlatformInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 平台名称 */
	private String platName;

	/** 平台简称 */
	private String platShortName;

	/** 平台代码（对应码表266父键的code_name值） */
	private String platCode;

	/** 平台性质 */
	private String platType;

	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date stepDate;

	/** 企业ID（与平台代码字段相同） */
	private String entId;

	/** 备注 */
	private String remark;

	/** 删除标识（0：正常，1：删除） */
	private String delFlag;

	/** 扩展字段1-（保存高管人员ID，逗号分隔） */
	private String ptpiExtendOne;

	/** 扩展字段2 */
	private String ptpiExtendTwo;

	/** 扩展字段3 */
	private String ptpiExtendThree;

	/** 扩展字段4 */
	private String ptpiExtendFour;

	/** 扩展字段5 */
	private String ptpiExtendFive;

	private String yhRatioInMc;

	private String rmbCurrentAmount;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public String getPlatShortName() {
		return platShortName;
	}

	public void setPlatShortName(String platShortName) {
		this.platShortName = platShortName;
	}

	public String getPlatCode() {
		return platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public Date getStepDate() {
		return stepDate;
	}

	public void setStepDate(Date stepDate) {
		this.stepDate = stepDate;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPtpiExtendOne() {
		return ptpiExtendOne;
	}

	public void setPtpiExtendOne(String ptpiExtendOne) {
		this.ptpiExtendOne = ptpiExtendOne;
	}

	public String getPtpiExtendTwo() {
		return ptpiExtendTwo;
	}

	public void setPtpiExtendTwo(String ptpiExtendTwo) {
		this.ptpiExtendTwo = ptpiExtendTwo;
	}

	public String getPtpiExtendThree() {
		return ptpiExtendThree;
	}

	public void setPtpiExtendThree(String ptpiExtendThree) {
		this.ptpiExtendThree = ptpiExtendThree;
	}

	public String getPtpiExtendFour() {
		return ptpiExtendFour;
	}

	public void setPtpiExtendFour(String ptpiExtendFour) {
		this.ptpiExtendFour = ptpiExtendFour;
	}

	public String getPtpiExtendFive() {
		return ptpiExtendFive;
	}

	public void setPtpiExtendFive(String ptpiExtendFive) {
		this.ptpiExtendFive = ptpiExtendFive;
	}

	public String getYhRatioInMc() {
		return yhRatioInMc;
	}

	public void setYhRatioInMc(String yhRatioInMc) {
		this.yhRatioInMc = yhRatioInMc;
	}

	public String getRmbCurrentAmount() {
		return rmbCurrentAmount;
	}

	public void setRmbCurrentAmount(String rmbCurrentAmount) {
		this.rmbCurrentAmount = rmbCurrentAmount;
	}
}