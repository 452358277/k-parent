package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjAppInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 项目 */
	private String projId;

	/** 流程实例号 */
	private String processInstId;

	/** 被投对象融资轮数 */
	private String finaRounds;

	/** 被投对象融资次数 */
	private String finaTimes;

	/** 被投对象融资金额 */
	private Double finaAmount;

	/** FINA_CURRENCY */
	private String finaCurrency;

	/** 投资角色 */
	private String inveRole;

	/** 投资类型 */
	private String inveType;

	/** 投资轮次 */
	private String inveRounds;

	/** 投前估值 */
	private Double preMoney;

	/** PRE_CURRENCY */
	private String preCurrency;

	/** 投后估值 */
	private Double postMoney;

	/** POST_CURRENCY */
	private String postCurrency;

	/** 拟投资金额 */
	private Double intendedAmount;

	/** 拟投资币种 */
	private String intendedCurrency;

	/** 购买股数 */
	private Long inveShare;

	/** 占股比（%） */
	private Double perShare;

	/** null */
	private String projGuid;

	/** 折算为人民币 */
	private Double rmbIntendedAmount;

	/** 尽职调查重点/其它重点关注信息 */
	private String focusInfo;

	/** 投资理由 */
	private String inveReason;

	/** 决策事项/董监事安排 */
	private String majorMatter;

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
	/**附件**/
	private String anotherFile;

	private  String currentCurrency;
	private Double currentAmount;

	private  String inveId;

	private  Double applyAmount;

	private String decisionFile;

	private String isDecisionPass;

	protected Date decisionDate;

	private Double actualAmount;

	private Double inveCurrentAmount;

	private Double  inveRaiseAmount;

	private String isCoo;

	private String descDate;

	private String sumActualPayAmount;

	protected Date startDate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveDt;


	public Date getInveDt() {
		return inveDt;
	}

	public void setInveDt(Date inveDt) {
		this.inveDt = inveDt;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Double getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getCurrentCurrency() {
		return currentCurrency;
	}

	public void setCurrentCurrency(String currentCurrency) {
		this.currentCurrency = currentCurrency;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getAnotherFile() {
		return anotherFile;
	}

	public void setAnotherFile(String anotherFile) {
		this.anotherFile = anotherFile;
	}

	public String getIsCoo() {
		return isCoo;
	}

	public void setIsCoo(String isCoo) {
		this.isCoo = isCoo;
	}


	public String getSumActualPayAmount() {
		return sumActualPayAmount;
	}

	public void setSumActualPayAmount(String sumActualPayAmount) {
		this.sumActualPayAmount = sumActualPayAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDescDate() {
		return descDate;
	}

	public void setDescDate(String descDate) {
		this.descDate = descDate;
	}

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

	public String getDecisionFile() {
		return decisionFile;
	}

	public void setDecisionFile(String decisionFile) {
		this.decisionFile = decisionFile;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
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

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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

	public Double getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(Double intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getIntendedCurrency() {
		return intendedCurrency;
	}

	public void setIntendedCurrency(String intendedCurrency) {
		this.intendedCurrency = intendedCurrency;
	}

	public Long getInveShare() {
		return inveShare;
	}

	public void setInveShare(Long inveShare) {
		this.inveShare = inveShare;
	}

	public Double getPerShare() {
		return perShare;
	}

	public void setPerShare(Double perShare) {
		this.perShare = perShare;
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

	public String getInveReason() {
		return inveReason;
	}

	public void setInveReason(String inveReason) {
		this.inveReason = inveReason;
	}

	public String getMajorMatter() {
		return majorMatter;
	}

	public void setMajorMatter(String majorMatter) {
		this.majorMatter = majorMatter;
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

}