package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class LedgerMagModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String ledgerId;

	/** 投资支付类型（1投资人，2基金，3项目，4平台） */
	private String investPaymentType;

	/** 单据编码 */
	private String fNumber;

	/** 公司名称 */
	private String companyName;

	/** 公司编码 */
	private String companyNumber;

	/** 业务日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date bizDate;

	/** 客户名称 */
	private String customerName;

	/** 客户编码 */
	private String customerNum;

	/** 资金类型（1出资，2入资） */
	private String financeType;

	/** 收款类型（10其他） */
	private String recBillTypeName;

	/** 收款类型编码 */
	private String recBillTypeNum;

	/** 币种（1人民币，2美元，3欧元） */
	private String currencyName;

	/** 币种编码 */
	private String currencyNum;

	/** 金额 */
	private Double amt;

	/** 收款分录行号 */
	private String fseq;

	/** 对方科目名称 */
	private String oppAccountName;

	/** 对方科目编码 */
	private String oppAccountNum;

	/** 核算项目行号 */
	private String assseq;

	/** 收款科目核算辅助中的客户编码 */
	private String assCustomerNum;

	/** 收款科目核算辅助中的客户名称 */
	private String assCustomerName;

	/** 状态（0：正常，1：删除，2：已到帐） */
	private String status;

	/** 附件 */
	private String descFile;

	/** 描述 */
	private String descriptiondata;

	/** 人民币金额 */
	private Double actuallocamt;

	/** 描述 */
	private String remark;

	/** 所属平台 */
	private String groupId;

	/** 所属平台 */
	private String fundName;

	private String customerType;

	private String customerTypeName;

	/** 日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 账户余额 */
	private Double accountBalance;

	/** 起息日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 到期日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 存款期限 */
	private String depositTerm;

	/** 利率*/
	private Double	moneyRate;

	/** 产品id*/
	private String productId;

	/** 产品赎回类型*/
	private String backType;

	/** 利息*/
	private Double gainAmount;

	private String accounts;

	private String isComply;

	private String isPaySame;


	private String  isPerfect;

	private String projId;

	private String ledgerType;

	private Double occurAmount;

	private String occurCurr;

	private String exitType;

	private String exitRound;

	private String payWay;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date occurDt;

	private String inveName;

	private String inveId;

	private String ledgerObject;

	private String fundId;


	private String quitWay;

	private String contact;

	private String contactNo;

	private String accountBank;

	private String accountNo;
   //折算人民币
	private Double rmbInveAmount;
	//选择资金对象，下拉款值导致下面的下拉款变化，其中对应的id
	private String objectId;
	//选择资金对象，下拉款值导致下面的下拉款变化，其中对应的name
	private String objectName;


	private String isComplete;

	private Double fofInveAmount;

	private String fofInveCurr;


	private Double fofInveRmb;



	private String dataType;


	public Double getFofInveAmount() {
		return fofInveAmount;
	}

	public void setFofInveAmount(Double fofInveAmount) {
		this.fofInveAmount = fofInveAmount;
	}

	public String getFofInveCurr() {
		return fofInveCurr;
	}

	public void setFofInveCurr(String fofInveCurr) {
		this.fofInveCurr = fofInveCurr;
	}

	public Double getFofInveRmb() {
		return fofInveRmb;
	}

	public void setFofInveRmb(Double fofInveRmb) {
		this.fofInveRmb = fofInveRmb;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public String getLedgerObject() {
		return ledgerObject;
	}

	public void setLedgerObject(String ledgerObject) {
		this.ledgerObject = ledgerObject;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getQuitWay() {
		return quitWay;
	}

	public void setQuitWay(String quitWay) {
		this.quitWay = quitWay;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getRmbInveAmount() {
		return rmbInveAmount;
	}

	public void setRmbInveAmount(Double rmbInveAmount) {
		this.rmbInveAmount = rmbInveAmount;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Double getOccurAmount() {
		return occurAmount;
	}

	public void setOccurAmount(Double occurAmount) {
		this.occurAmount = occurAmount;
	}

	public String getOccurCurr() {
		return occurCurr;
	}

	public void setOccurCurr(String occurCurr) {
		this.occurCurr = occurCurr;
	}

	public String getExitType() {
		return exitType;
	}

	public void setExitType(String exitType) {
		this.exitType = exitType;
	}

	public String getExitRound() {
		return exitRound;
	}

	public void setExitRound(String exitRound) {
		this.exitRound = exitRound;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public Date getOccurDt() {
		return occurDt;
	}

	public void setOccurDt(Date occurDt) {
		this.occurDt = occurDt;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getIsComply() {
		return isComply;
	}

	public void setIsComply(String isComply) {
		this.isComply = isComply;
	}

	public String getIsPaySame() {
		return isPaySame;
	}

	public void setIsPaySame(String isPaySame) {
		this.isPaySame = isPaySame;
	}

	public String getIsPerfect() {
		return isPerfect;
	}

	public void setIsPerfect(String isPerfect) {
		this.isPerfect = isPerfect;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public Double getGainAmount() {
		return gainAmount;
	}

	public void setGainAmount(Double gainAmount) {
		this.gainAmount = gainAmount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDepositTerm() {
		return depositTerm;
	}

	public void setDepositTerm(String depositTerm) {
		this.depositTerm = depositTerm;
	}

	public Double getMoneyRate() {
		return moneyRate;
	}

	public void setMoneyRate(Double moneyRate) {
		this.moneyRate = moneyRate;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getfNumber() {
		return fNumber;
	}

	public void setfNumber(String fNumber) {
		this.fNumber = fNumber;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}

	public String getInvestPaymentType() {
		return investPaymentType;
	}

	public void setInvestPaymentType(String investPaymentType) {
		this.investPaymentType = investPaymentType;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getFinanceType() {
		return financeType;
	}

	public void setFinanceType(String financeType) {
		this.financeType = financeType;
	}

	public String getRecBillTypeName() {
		return recBillTypeName;
	}

	public void setRecBillTypeName(String recBillTypeName) {
		this.recBillTypeName = recBillTypeName;
	}

	public String getRecBillTypeNum() {
		return recBillTypeNum;
	}

	public void setRecBillTypeNum(String recBillTypeNum) {
		this.recBillTypeNum = recBillTypeNum;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyNum() {
		return currencyNum;
	}

	public void setCurrencyNum(String currencyNum) {
		this.currencyNum = currencyNum;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getFseq() {
		return fseq;
	}

	public void setFseq(String fseq) {
		this.fseq = fseq;
	}

	public String getOppAccountName() {
		return oppAccountName;
	}

	public void setOppAccountName(String oppAccountName) {
		this.oppAccountName = oppAccountName;
	}

	public String getOppAccountNum() {
		return oppAccountNum;
	}

	public void setOppAccountNum(String oppAccountNum) {
		this.oppAccountNum = oppAccountNum;
	}

	public String getAssseq() {
		return assseq;
	}

	public void setAssseq(String assseq) {
		this.assseq = assseq;
	}

	public String getAssCustomerNum() {
		return assCustomerNum;
	}

	public void setAssCustomerNum(String assCustomerNum) {
		this.assCustomerNum = assCustomerNum;
	}

	public String getAssCustomerName() {
		return assCustomerName;
	}

	public void setAssCustomerName(String assCustomerName) {
		this.assCustomerName = assCustomerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public String getDescriptiondata() {
		return descriptiondata;
	}

	public void setDescriptiondata(String descriptiondata) {
		this.descriptiondata = descriptiondata;
	}

	public Double getActuallocamt() {
		return actuallocamt;
	}

	public void setActuallocamt(Double actuallocamt) {
		this.actuallocamt = actuallocamt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}