package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PaymentRequestVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 支付申请号 */
	private String pqId;

	/** 投资支付类型（1投资人，2基金，3项目，4平台）,61000 */
	private String investPaymentType;

	/** 出资主体 */
	private String investor;

	/** 被投对象 */
	private String receiver;

	/** 申请人 */
	private String appUser;

	/** 申请时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date appDt;

	/** 资金类型（1出资，2入资）,62000 */
	private String financeType;

	/** 付款时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date payDt;

	/** 本期付款金额 */
	private Double payAmount;

	/** 本期付款金额币种 */
	private String payCurrency;

	/** 当前汇率折算为人民币 */
	private Double rmbInveAmount;

	/** 收款人名称 */
	private String payeeName;

	/** 收款银行 */
	private String dueBank;

	/** 收款银行 */
	private String receivableAccount;

	/** 付款账号 */
	private String paymentAccount;

	/** 附件 */
	private String descFile;

	/** 备注 */
	private String remark;

	/** 表单申请状态,264，0草稿 9删除 4审核成功 5已确认支付 */
	private String status;

	/** 流程编号 */
	private String processInstId;

	/** 项目/基金ID */
	private String projectOrFundId;

	/** 项目/基金NAME */
	private String projectOrFundName;

	/** 出资主体ID */
	private String investorId;

	/** 被投对象ID */
	private String receiverId;

	/** 申请人ID */
	private String appUserId;

	/** 资金对象 */
	private String ledgerObject;

	/** 协议金额，公司投资总额 */
	private Double totalInveAmount;

	/** 协议金额，公司投资总额-币种 */
	private String totalInveCurrency;

	/** 协议金额，公司投资总额人民币金额 */
	private Double totalInveAmountRmb;

	/** 累计付款金额 */
	private Double sumInveAmount;

	/** 累计付款金额币种 */
	private String sumInveCurrency;

	/** 未付款金额 */
	private Double unpaidAmount;

	/** 未付款金额币种 */
	private String unpaidCurrency;

	/** 付款总期数 */
	private Long totalPeriod;

	/** 当前期数 */
	private Long currentPeriod;

	/** 部门ID */
	private String deptId;

	/** 部门名称 */
	private String deptName;

	/** 所属平台 */
	private String groupId;

	/** 所属平台NAME */
	private String groupName;

	/** 资金情况说明 */
	private String moneyDesc;

	/** 资金情况说明附件 */
	private String moneyDescAtt;

	/** 实际付款日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date actualPayDate;

	/** 平台财务相关附件 */
	private String attaFile;

	/** 关联合同ID */
	private String contractFileId;

	private FixflowRunTaskinstanceModel instanceModel;

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}

	public String getPqId() {
		return pqId;
	}

	public void setPqId(String pqId) {
		this.pqId = pqId;
	}

	public String getInvestPaymentType() {
		return investPaymentType;
	}

	public void setInvestPaymentType(String investPaymentType) {
		this.investPaymentType = investPaymentType;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public Date getAppDt() {
		return appDt;
	}

	public void setAppDt(Date appDt) {
		this.appDt = appDt;
	}

	public String getFinanceType() {
		return financeType;
	}

	public void setFinanceType(String financeType) {
		this.financeType = financeType;
	}

	public Date getPayDt() {
		return payDt;
	}

	public void setPayDt(Date payDt) {
		this.payDt = payDt;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayCurrency() {
		return payCurrency;
	}

	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}

	public Double getRmbInveAmount() {
		return rmbInveAmount;
	}

	public void setRmbInveAmount(Double rmbInveAmount) {
		this.rmbInveAmount = rmbInveAmount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getDueBank() {
		return dueBank;
	}

	public void setDueBank(String dueBank) {
		this.dueBank = dueBank;
	}

	public String getReceivableAccount() {
		return receivableAccount;
	}

	public void setReceivableAccount(String receivableAccount) {
		this.receivableAccount = receivableAccount;
	}

	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProjectOrFundId() {
		return projectOrFundId;
	}

	public void setProjectOrFundId(String projectOrFundId) {
		this.projectOrFundId = projectOrFundId;
	}

	public String getProjectOrFundName() {
		return projectOrFundName;
	}

	public void setProjectOrFundName(String projectOrFundName) {
		this.projectOrFundName = projectOrFundName;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getLedgerObject() {
		return ledgerObject;
	}

	public void setLedgerObject(String ledgerObject) {
		this.ledgerObject = ledgerObject;
	}

	public Double getTotalInveAmount() {
		return totalInveAmount;
	}

	public void setTotalInveAmount(Double totalInveAmount) {
		this.totalInveAmount = totalInveAmount;
	}

	public String getTotalInveCurrency() {
		return totalInveCurrency;
	}

	public void setTotalInveCurrency(String totalInveCurrency) {
		this.totalInveCurrency = totalInveCurrency;
	}

	public Double getTotalInveAmountRmb() {
		return totalInveAmountRmb;
	}

	public void setTotalInveAmountRmb(Double totalInveAmountRmb) {
		this.totalInveAmountRmb = totalInveAmountRmb;
	}

	public Double getSumInveAmount() {
		return sumInveAmount;
	}

	public void setSumInveAmount(Double sumInveAmount) {
		this.sumInveAmount = sumInveAmount;
	}

	public String getSumInveCurrency() {
		return sumInveCurrency;
	}

	public void setSumInveCurrency(String sumInveCurrency) {
		this.sumInveCurrency = sumInveCurrency;
	}

	public Double getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(Double unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	public String getUnpaidCurrency() {
		return unpaidCurrency;
	}

	public void setUnpaidCurrency(String unpaidCurrency) {
		this.unpaidCurrency = unpaidCurrency;
	}

	public Long getTotalPeriod() {
		return totalPeriod;
	}

	public void setTotalPeriod(Long totalPeriod) {
		this.totalPeriod = totalPeriod;
	}

	public Long getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Long currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

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

	public String getMoneyDesc() {
		return moneyDesc;
	}

	public void setMoneyDesc(String moneyDesc) {
		this.moneyDesc = moneyDesc;
	}

	public String getMoneyDescAtt() {
		return moneyDescAtt;
	}

	public void setMoneyDescAtt(String moneyDescAtt) {
		this.moneyDescAtt = moneyDescAtt;
	}

	public Date getActualPayDate() {
		return actualPayDate;
	}

	public void setActualPayDate(Date actualPayDate) {
		this.actualPayDate = actualPayDate;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getContractFileId() {
		return contractFileId;
	}

	public void setContractFileId(String contractFileId) {
		this.contractFileId = contractFileId;
	}

}