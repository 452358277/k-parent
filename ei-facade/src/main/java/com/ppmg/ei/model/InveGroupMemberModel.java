package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-09-04 16:21 
 */ 
@Data 
public class InveGroupMemberModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 主键ID */
	private String groupId;

	/** 投资人ID */
	private String investorId;

	/** 投资人名称 */
	private String investorName;


	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
}