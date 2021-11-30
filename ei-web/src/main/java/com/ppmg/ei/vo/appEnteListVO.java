package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="APP企业维度列表",description="APP企业维度列表")
public class appEnteListVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;


	/** 图片 */
	@ApiModelProperty(value="图片",name="entLogo",example="项目经理自行开拓")
	private byte[] entLogo;

	/** 企业名称 */
	private String projObject;

	/** 企业名称 */
	private String projObjectName;

	/** 七大行业ID */
	private String sevenIndustry;

	/** 七大行业NAME */
	private String sevenIndustryName;
	
	/** 出资主体 */
	private String inveId;

	/** 出资主体 */
	private String inveName;

    /** 出资主体 */
    private String groupId;

    /** 出资主体 */
    private String groupName;

    /** 累计出资 */
	private String investAmount;

    /** 最新占比 */
	private String holdShare;

	/** 最新占比估值 */
	private String inveValuation;

	/** 累计回收金额 */
	private String recoveryAmount;

	/** 主营业务 */
	private String scope;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }




    public String getInveValuation() {
		return inveValuation;
	}

	public void setInveValuation(String inveValuation) {
		this.inveValuation = inveValuation;
	}

	public String getRecoveryAmount() {
		return recoveryAmount;
	}

	public void setRecoveryAmount(String recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public byte[] getEntLogo() {
		return entLogo;
	}

	public void setEntLogo(byte[] entLogo) {
		this.entLogo = entLogo;
	}

	public String getProjObject() {
		return projObject;
	}

	public void setProjObject(String projObject) {
		this.projObject = projObject;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getSevenIndustry() {
		return sevenIndustry;
	}

	public void setSevenIndustry(String sevenIndustry) {
		this.sevenIndustry = sevenIndustry;
	}

	public String getSevenIndustryName() {
		return sevenIndustryName;
	}

	public void setSevenIndustryName(String sevenIndustryName) {
		this.sevenIndustryName = sevenIndustryName;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	public String getHoldShare() {
		return holdShare;
	}

	public void setHoldShare(String holdShare) {
		this.holdShare = holdShare;
	}
}
