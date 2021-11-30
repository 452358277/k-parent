package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 投资人
 * @author root
 * @date 2019-09-04 15:42 
 */ 
@Data 
public class InveRelModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String relId;

	/** 投资人 */
	private String investor1Id;

	/** 投资人名称 */
	private String investor1Name;

	/** 机构 */
	private String investor2Id;

	/** 机构名称 */
	private String investor2Name;

	/** 投资关系类别 */
	private String relType;

	/** 关系建立时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date beginDate;

	/** 关系截止时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 股权比例 */
	private Double stakePer;

	/** 货币类型 */
	private String currencyType;
	private String currencyTypeName;

	public String getCurrencyTypeName() {
		return currencyTypeName;
	}

	public void setCurrencyTypeName(String currencyTypeName) {
		this.currencyTypeName = currencyTypeName;
	}

	/** 投资金额 */
	private Double inveAmount;

	/** 职位 */
	private String position;

	/** 备注 */
	private String remark;

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getInvestor1Id() {
		return investor1Id;
	}

	public void setInvestor1Id(String investor1Id) {
		this.investor1Id = investor1Id;
	}

	public String getInvestor1Name() {
		return investor1Name;
	}

	public void setInvestor1Name(String investor1Name) {
		this.investor1Name = investor1Name;
	}

	public String getInvestor2Id() {
		return investor2Id;
	}

	public void setInvestor2Id(String investor2Id) {
		this.investor2Id = investor2Id;
	}

	public String getInvestor2Name() {
		return investor2Name;
	}

	public void setInvestor2Name(String investor2Name) {
		this.investor2Name = investor2Name;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getStakePer() {
		return stakePer;
	}

	public void setStakePer(Double stakePer) {
		this.stakePer = stakePer;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}