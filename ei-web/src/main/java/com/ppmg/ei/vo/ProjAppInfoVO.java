package com.ppmg.ei.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="项目立项信息详细对象",description="项目立项信息详细对象")
public class ProjAppInfoVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 主键 */
	@ApiModelProperty(value="主键",name="projId")
	private String projId;

	/** 出资主体 */
	@ApiModelProperty(value="出资主体",name="inveName")
	private String inveName;

	/** 拟投资比例不超过 */
	@ApiModelProperty(value="拟投资比例不超过",name="perShare")
	private Double perShare;
	
	/** 拟投资金额不超过 */
	@ApiModelProperty(value="拟投资金额不超过",name="intendedAmount")
	private Double intendedAmount;
	
	/** 拟投资币种 */
	private String intendedCurrency;

	/** 决策事项 */
	@ApiModelProperty(value="决策事项",name="majorMatter")
	private String majorMatter;
	
	/** 投资理由 */
	@ApiModelProperty(value="投资理由",name="inveReason")
	private String inveReason;

	/** 商业计划书 */
	private List<EiTAttachmentVO> bizPlanAttList;

	/** 立项报告 */
	private List<EiTAttachmentVO> projReportAttList;

	/** 其他 */
	private List<EiTAttachmentVO> otherAttList;

	/** 项目立项时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date setupDt;


	/** 流程实例号 */
	private String processInstId;

	/** 被投对象融资轮数 */
	private String finaRounds;

	private String finaRoundsName;

	/** 被投对象融资次数 */
	private String finaTimes;

	/** 被投对象融资金额 */
	private Double finaAmount;

	/** FINA_CURRENCY */
	private String finaCurrency;

	/** 投资角色 */
	private String inveRole;

	private String inveRoleName;

	/** 投资类型 */
	private String inveType;

	private String inveTypeName;

	/** 投资轮次 */
	private String inveRounds;


	private String inveRoundsName;

	/** 投前估值 */
	private Double preMoney;

	/** PRE_CURRENCY */
	private String preCurrency;

	/** 投后估值 */
	private Double postMoney;

	/** POST_CURRENCY */
	private String postCurrency;

	/** 购买股数 */
	private Long inveShare;


	/** null */
	private String projGuid;

	/** 折算为人民币 */
	private Double rmbIntendedAmount;

	/** 尽职调查重点/其它重点关注信息 */
	private String focusInfo;


	/** null */
	private String remark;

	/** 尽职调查重点/其它重点关注信息附件 */
	private String focusInfoAtt;

	/** 投资理由附件 */
	private String inveReasonAtt;

	/** 决策事项/董监事安排附件 */
	private String majorMatterAtt;

	/** 备注附件 */
	private String remarkAtt;

	/** 立项问题反馈 */
	private String feedBack;

	private String isDecisionPass;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	protected Date decisionDate;

	private Double actualAmount;

	private String decisionFile;

	private Double allTmoney;


	private Double inveCurrentAmount;

	private Double  inveRaiseAmount;

	public Double getInveCurrentAmount() {
		return inveCurrentAmount;
	}

	public void setInveCurrentAmount(Double inveCurrentAmount) {
		this.inveCurrentAmount = inveCurrentAmount;
	}

	public Double getInveRaiseAmount() {
		return inveRaiseAmount;
	}

	public void setInveRaiseAmount(Double inveRaiseAmount) {
		this.inveRaiseAmount = inveRaiseAmount;
	}

	public Double getAllTmoney() {
		return allTmoney;
	}

	public void setAllTmoney(Double allTmoney) {
		this.allTmoney = allTmoney;
	}

	public String getDecisionFile() {
		return decisionFile;
	}

	public void setDecisionFile(String decisionFile) {
		this.decisionFile = decisionFile;
	}

	public String getFinaRoundsName() {
		return finaRoundsName;
	}

	public void setFinaRoundsName(String finaRoundsName) {
		this.finaRoundsName = finaRoundsName;
	}

	public String getInveTypeName() {
		return inveTypeName;
	}

	public void setInveTypeName(String inveTypeName) {
		this.inveTypeName = inveTypeName;
	}

	public String getInveRoleName() {
		return inveRoleName;
	}

	public void setInveRoleName(String inveRoleName) {
		this.inveRoleName = inveRoleName;
	}

	public String getInveRoundsName() {
		return inveRoundsName;
	}

	public void setInveRoundsName(String inveRoundsName) {
		this.inveRoundsName = inveRoundsName;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getFinaRounds() {
		return finaRounds;
	}

	public void setFinaRounds(String finaRounds) {
		this.finaRounds = finaRounds;
	}

	public String getFinaTimes() {
		return finaTimes;
	}

	public void setFinaTimes(String finaTimes) {
		this.finaTimes = finaTimes;
	}

	public Double getFinaAmount() {
		return finaAmount;
	}

	public void setFinaAmount(Double finaAmount) {
		this.finaAmount = finaAmount;
	}

	public String getFinaCurrency() {
		return finaCurrency;
	}

	public void setFinaCurrency(String finaCurrency) {
		this.finaCurrency = finaCurrency;
	}

	public String getInveRole() {
		return inveRole;
	}

	public void setInveRole(String inveRole) {
		this.inveRole = inveRole;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public String getInveRounds() {
		return inveRounds;
	}

	public void setInveRounds(String inveRounds) {
		this.inveRounds = inveRounds;
	}

	public Double getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(Double preMoney) {
		this.preMoney = preMoney;
	}

	public String getPreCurrency() {
		return preCurrency;
	}

	public void setPreCurrency(String preCurrency) {
		this.preCurrency = preCurrency;
	}

	public Double getPostMoney() {
		return postMoney;
	}

	public void setPostMoney(Double postMoney) {
		this.postMoney = postMoney;
	}

	public String getPostCurrency() {
		return postCurrency;
	}

	public void setPostCurrency(String postCurrency) {
		this.postCurrency = postCurrency;
	}

	public Long getInveShare() {
		return inveShare;
	}

	public void setInveShare(Long inveShare) {
		this.inveShare = inveShare;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public Double getRmbIntendedAmount() {
		return rmbIntendedAmount;
	}

	public void setRmbIntendedAmount(Double rmbIntendedAmount) {
		this.rmbIntendedAmount = rmbIntendedAmount;
	}

	public String getFocusInfo() {
		return focusInfo;
	}

	public void setFocusInfo(String focusInfo) {
		this.focusInfo = focusInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFocusInfoAtt() {
		return focusInfoAtt;
	}

	public void setFocusInfoAtt(String focusInfoAtt) {
		this.focusInfoAtt = focusInfoAtt;
	}

	public String getInveReasonAtt() {
		return inveReasonAtt;
	}

	public void setInveReasonAtt(String inveReasonAtt) {
		this.inveReasonAtt = inveReasonAtt;
	}

	public String getMajorMatterAtt() {
		return majorMatterAtt;
	}

	public void setMajorMatterAtt(String majorMatterAtt) {
		this.majorMatterAtt = majorMatterAtt;
	}

	public String getRemarkAtt() {
		return remarkAtt;
	}

	public void setRemarkAtt(String remarkAtt) {
		this.remarkAtt = remarkAtt;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public String getIsDecisionPass() {
		return isDecisionPass;
	}

	public void setIsDecisionPass(String isDecisionPass) {
		this.isDecisionPass = isDecisionPass;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Date getSetupDt() {
		return setupDt;
	}

	public void setSetupDt(Date setupDt) {
		this.setupDt = setupDt;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public Double getPerShare() {
		return perShare;
	}

	public void setPerShare(Double perShare) {
		this.perShare = perShare;
	}

	public Double getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(Double intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getMajorMatter() {
		return majorMatter;
	}

	public void setMajorMatter(String majorMatter) {
		this.majorMatter = majorMatter;
	}

	public String getInveReason() {
		return inveReason;
	}

	public void setInveReason(String inveReason) {
		this.inveReason = inveReason;
	}

	public List<EiTAttachmentVO> getBizPlanAttList() {
		return bizPlanAttList;
	}

	public void setBizPlanAttList(List<EiTAttachmentVO> bizPlanAttList) {
		this.bizPlanAttList = bizPlanAttList;
	}

	public List<EiTAttachmentVO> getProjReportAttList() {
		return projReportAttList;
	}

	public void setProjReportAttList(List<EiTAttachmentVO> projReportAttList) {
		this.projReportAttList = projReportAttList;
	}

	public List<EiTAttachmentVO> getOtherAttList() {
		return otherAttList;
	}

	public void setOtherAttList(List<EiTAttachmentVO> otherAttList) {
		this.otherAttList = otherAttList;
	}

	public String getIntendedCurrency() {
		return intendedCurrency;
	}

	public void setIntendedCurrency(String intendedCurrency) {
		this.intendedCurrency = intendedCurrency;
	}

	
	
}
