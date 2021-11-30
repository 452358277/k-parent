package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundMemberModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 基金ID */
	private String fundId;

	/** 成员编号 */
	private String memberId;

	/** 成员名称 */
	private String memberName;

	/** 是否筹委会成员（0：否，1：是） */
	private String isPcm;

	/** 是否基金负责人（0：否，1：是） */
	private String isFm;

	/** 董监高成员标识（0：否，1：是，三位组合） */
	private String memberFlag;

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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getIsPcm() {
		return isPcm;
	}

	public void setIsPcm(String isPcm) {
		this.isPcm = isPcm;
	}

	public String getIsFm() {
		return isFm;
	}

	public void setIsFm(String isFm) {
		this.isFm = isFm;
	}

	public String getMemberFlag() {
		return memberFlag;
	}

	public void setMemberFlag(String memberFlag) {
		this.memberFlag = memberFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}