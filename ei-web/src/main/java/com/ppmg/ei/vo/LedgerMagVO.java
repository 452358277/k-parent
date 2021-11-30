package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.BankProductModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LedgerMagVO extends BaseVO {

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


	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date createDt;

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

	/** 状态（0：正常，1：删除，2：已到帐） */
	private String status;

	/** 描述 */
	private String descriptiondata;

	/** 人民币金额 */
	private Double actuallocamt;

	/** 描述 */
	private String remark;

	/** 所属平台 */
	private String groupId;

	private String customerType;

	private String customerTypeName;

	private String assseq;

	private String assCustomerNum;

	private String assCustomerName;

	protected String createBy;


	protected String financeTypeName;

	/** 附件 */
	private String descFile;

	private Double accountBalance;

	private Double gainAmount;

	/** 日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 收款分录行号 */
	private String fseq;

	/** 对方科目名称 */
	private String oppAccountName;

	/** 对方科目编码 */
	private String oppAccountNum;

	/** 所属平台 */
	private String fundName;


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

	private BankProductModel bankProductModel;

	private String isComply;

	private String isPaySame;


	private String  isPerfect;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date occurDt;

	private String ledgerType;

	private String ledgerTypeName;

	private Double occurAmount;

	private String occurCurr;

	private String occurCurrName;


	private Double rmbInveAmount;

    private String objectId;

    private String objectName;


    private String projName;

	private String projId;

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

    public Date getOccurDt() {
		return occurDt;
	}

	public void setOccurDt(Date occurDt) {
		this.occurDt = occurDt;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

	public String getLedgerTypeName() {
		return ledgerTypeName;
	}

	public void setLedgerTypeName(String ledgerTypeName) {
		this.ledgerTypeName = ledgerTypeName;
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

	public String getOccurCurrName() {
		return occurCurrName;
	}

	public void setOccurCurrName(String occurCurrName) {
		this.occurCurrName = occurCurrName;
	}

	public Double getRmbInveAmount() {
		return rmbInveAmount;
	}

	public void setRmbInveAmount(Double rmbInveAmount) {
		this.rmbInveAmount = rmbInveAmount;
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

	public BankProductModel getBankProductModel() {
		return bankProductModel;
	}

	public void setBankProductModel(BankProductModel bankProductModel) {
		this.bankProductModel = bankProductModel;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Double getGainAmount() {
		return gainAmount;
	}

	public void setGainAmount(Double gainAmount) {
		this.gainAmount = gainAmount;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public String getFinanceTypeName() {
		return financeTypeName;
	}

	public void setFinanceTypeName(String financeTypeName) {
		this.financeTypeName = financeTypeName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
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

	public String getfNumber() {
		return fNumber;
	}

	public void setfNumber(String fNumber) {
		this.fNumber = fNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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

	public String getFNumber() {
		return fNumber;
	}

	public void setFNumber(String fNumber) {
		this.fNumber = fNumber;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
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
}