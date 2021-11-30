package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="项目信息列表对象",description="项目信息列表对象")
public class ProjInfoListVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 子基金简称 */
	@ApiModelProperty(value="子基金简称",name="projObjectName",example="启明二期")
	private String projObjectName;

	/** 项目来源 */
	@ApiModelProperty(value="项目来源",name="projSrc",example="项目经理自行开拓")
	private String projSrc;
	
	/** 分管合伙人 */
	@ApiModelProperty(value="分管合伙人",name="chargePartner",example="金玉玲")
	private String chargePartner;
	
	/** 保密等级 */
	@ApiModelProperty(value="保密等级",name="secrecyLevel",example="机密")
	private String secrecyLevel;

	/** 项目概况 */
	@ApiModelProperty(value="项目概况",name="projOverview",example="")
	private String projOverview;

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getProjSrc() {
		return projSrc;
	}

	public void setProjSrc(String projSrc) {
		this.projSrc = projSrc;
	}

	public String getChargePartner() {
		return chargePartner;
	}

	public void setChargePartner(String chargePartner) {
		this.chargePartner = chargePartner;
	}

	public String getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(String secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getProjOverview() {
		return projOverview;
	}

	public void setProjOverview(String projOverview) {
		this.projOverview = projOverview;
	}

	
	
	
}
