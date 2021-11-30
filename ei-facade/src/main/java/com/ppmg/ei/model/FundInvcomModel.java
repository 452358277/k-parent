package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

public class FundInvcomModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 委员会ID */
	private String comId;

	/** 基金ID */
	private String fundId;

	/** 委员会名称 */
	private String name;

	/** 状态 */
	private String status;

	private List<FundInvcommemModel> memberList;

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FundInvcommemModel> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<FundInvcommemModel> memberList) {
		this.memberList = memberList;
	}
}