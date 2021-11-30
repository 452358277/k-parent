package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.EiTAttachmentModel;
import com.ppmg.ei.model.ProjAppInfoModel;
import com.ppmg.ei.model.ProjMemberModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjListByFundVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String projId;

	/** 基金ID */
	private String fundId;

	/** 项目名称 */
	private String projName;

	/** 项目类型（1：投企业，2：投基金，3:子基金项目） */
	private String projType;

	/** 项目类型（1：投企业，2：投基金，3:子基金项目） */
	private String projTypeName;

	/** 被投对象名称 */
	private String projObjectName;

	/** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatus;

	/** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatusName;

	/** 投资决策时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date decisionDt;

	/** 已投资金额 */
	private String investAmountDisplay;

	/** 项目所属公司ID */
	private Long groupId;

	private String dealMark;

	/** 协议金额 */
	private String signAmountDisplay;

	/** 协议日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date signDt;

	/** 最新占比 */
	private String currentRatio;

	public Date getSignDt() {
		return signDt;
	}

	public void setSignDt(Date signDt) {
		this.signDt = signDt;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public String getSignAmountDisplay() {
		return signAmountDisplay;
	}

	public void setSignAmountDisplay(String signAmountDisplay) {
		this.signAmountDisplay = signAmountDisplay;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public Date getDecisionDt() {
		return decisionDt;
	}

	public void setDecisionDt(Date decisionDt) {
		this.decisionDt = decisionDt;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getInvestAmountDisplay() {
		return investAmountDisplay;
	}

	public void setInvestAmountDisplay(String investAmountDisplay) {
		this.investAmountDisplay = investAmountDisplay;
	}

	public String getProjStatusName() {
		return projStatusName;
	}

	public void setProjStatusName(String projStatusName) {
		this.projStatusName = projStatusName;
	}

	public String getProjTypeName() {
		return projTypeName;
	}

	public void setProjTypeName(String projTypeName) {
		this.projTypeName = projTypeName;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getDealMark() {
		return dealMark;
	}

	public void setDealMark(String dealMark) {
		this.dealMark = dealMark;
	}
}