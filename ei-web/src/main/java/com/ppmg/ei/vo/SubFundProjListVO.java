package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjMemberModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="项目信息列表对象",description="项目信息列表对象")
public class SubFundProjListVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 主键 */
	private String projId;

	/** 子基金简称 */
	@ApiModelProperty(value="子基金简称",name="projObjectName",example="启明二期")
	private String projObjectName;

	/** 出资主体编号 */
	private String inveId;

	/** 出资主体名称 */
	private String inveName;

	/** 当前子基金规模 */
	private String subFundAmontDisplay;

	/** 认缴金额 */
	private String currentAmountDisplay;

	/** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatusName;

	/** 项目经理 */
	private ProjMemberModel mangerModel;

	public String getProjStatusName() {
		return projStatusName;
	}

	public void setProjStatusName(String projStatusName) {
		this.projStatusName = projStatusName;
	}

	public ProjMemberModel getMangerModel() {
		return mangerModel;
	}

	public void setMangerModel(ProjMemberModel mangerModel) {
		this.mangerModel = mangerModel;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
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

	public String getSubFundAmontDisplay() {
		return subFundAmontDisplay;
	}

	public void setSubFundAmontDisplay(String subFundAmontDisplay) {
		this.subFundAmontDisplay = subFundAmontDisplay;
	}

	public String getCurrentAmountDisplay() {
		return currentAmountDisplay;
	}

	public void setCurrentAmountDisplay(String currentAmountDisplay) {
		this.currentAmountDisplay = currentAmountDisplay;
	}
}
