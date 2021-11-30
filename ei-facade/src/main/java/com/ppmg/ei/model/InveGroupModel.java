package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-09-04 16:20 
 */ 
@Data 
public class InveGroupModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String groupId;

	/** 关系建立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private String beginDate;

	/** 关系截止日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private String endDate;

	/** 合作内容 */
	private String coopContent;

	/** 备注 */
	private String remark;

	/** 投资人名称 */
	private String investorName;

	private String pkId;

	/** 投资人ID */
	private String investorId;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCoopContent() {
		return coopContent;
	}

	public void setCoopContent(String coopContent) {
		this.coopContent = coopContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

}