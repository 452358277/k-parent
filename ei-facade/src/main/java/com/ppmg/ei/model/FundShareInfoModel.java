package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

public class FundShareInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 基金编号 */
	private String fundId;

	/** 投资人ID */
	private String investorId;

	/** 投资角色(1:LP,2:GP,3:GM) */
	private String inveRole;

	/** 投资角色(1:LP,2:GP,3:GM) */
	private String inveRoleName;

	/** 认缴规模 */
	private Double inveAmount;
	private String inveAmountString;

	private String invePropertyName;

	public String getInvePropertyName() {
		return invePropertyName;
	}

	public void setInvePropertyName(String invePropertyName) {
		this.invePropertyName = invePropertyName;
	}

	public String getInveAmountString() {
		return inveAmountString;
	}

	public void setInveAmountString(String inveAmountString) {
		this.inveAmountString = inveAmountString;
	}

	/** 认缴金额币种 */
	private String inveCurrency;
	private String inveCurrencyName;

	public String getInveCurrencyName() {
		return inveCurrencyName;
	}

	public void setInveCurrencyName(String inveCurrencyName) {
		this.inveCurrencyName = inveCurrencyName;
	}

	/** 累计缴款额 */
	private Double totalFinancial;


	/** 累计出资币种 */
	private String totalFinCurrency;
	private String totalFinCurrencyName;

	public String getTotalFinCurrencyName() {
		return totalFinCurrencyName;
	}

	public void setTotalFinCurrencyName(String totalFinCurrencyName) {
		this.totalFinCurrencyName = totalFinCurrencyName;
	}

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 第几期 */
	private Long closeRound;

	/** 企业ID */
	private String enteId;

	/** 出资来源（码值1000：1自有资金、2理财通道、3借贷资金） */
	private String capitalSource;

	/** 出资来源（码值1000：1自有资金、2理财通道、3借贷资金） */
	private String capitalSourceName;

	/** 出资人性质（码值1001：1政府部门、2国有企业、3混合制企业、4民营企业、5个人） */
	private String inveType;

	/** 出资人性质（码值1001：1政府部门、2国有企业、3混合制企业、4民营企业、5个人） */
	private String inveTypeName;

	/** 实缴规模 */
	private Double raiseAmount;

	/** 认缴金额显示 */
	private String inveAmountDisplay;

	/** 累计出资金额显示 */
	private String totalFinancialDisplay;

	/** 股东名称 */
	private String investorName;

	/** 持股比例(%) */
	private String GB;

	private InveInfoModel inveInfoModel;

	private FundInfoModel  fundInfoModel;

	/*台账管理*/
	private List<FundLedgerMagModel> fundLedgerMagModel;
	/** 手机号 */
	private String mobile;
	/** 投资人名称(公司) */
	private String investorCompanyName;
	/** 认缴金额折算人民币 */
	private Double rmbInveAmount;
	/** 投资人名称(个人) */
	private String investorPersonName;

	private String fundName;
	/**
	 * 出资进度
	 */
	private String schedule;


	private String investorType;

	private String inveProperty;


	private String investorTypeName;


	public String getInvestorTypeName() {
		return investorTypeName;
	}

	public void setInvestorTypeName(String investorTypeName) {
		this.investorTypeName = investorTypeName;
	}


	public String getInvestorType() {
		return investorType;
	}

	public void setInvestorType(String investorType) {
		this.investorType = investorType;
	}

	public String getInveProperty() {
		return inveProperty;
	}

	public void setInveProperty(String inveProperty) {
		this.inveProperty = inveProperty;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * 投资人属性
	 */
	private String invePropertyF;
	/** 投资人性质 */
	private String inveNature;

	/** 折算为人民币（万元） */
	private String rmbIntendedAmount;

	private String investorOldName;

	private String inveOldCurrency;

	private String inveOldAmount;

	private String investorOldId;


	private String sumTotalFinancial;


	private String financialProcess;

	public String getFinancialProcess() {
		return financialProcess;
	}

	public void setFinancialProcess(String financialProcess) {
		this.financialProcess = financialProcess;
	}

	public String getSumTotalFinancial() {
		return sumTotalFinancial;
	}

	public void setSumTotalFinancial(String sumTotalFinancial) {
		this.sumTotalFinancial = sumTotalFinancial;
	}

	public String getInvestorOldName() {
		return investorOldName;
	}

	public void setInvestorOldName(String investorOldName) {
		this.investorOldName = investorOldName;
	}

	public String getInvestorOldId() {
		return investorOldId;
	}

	public void setInvestorOldId(String investorOldId) {
		this.investorOldId = investorOldId;
	}

	public String getInveOldCurrency() {
		return inveOldCurrency;
	}

	public void setInveOldCurrency(String inveOldCurrency) {
		this.inveOldCurrency = inveOldCurrency;
	}

	public String getInveOldAmount() {
		return inveOldAmount;
	}

	public void setInveOldAmount(String inveOldAmount) {
		this.inveOldAmount = inveOldAmount;
	}

	public String getInveNature() {
		return inveNature;
	}

	public void setInveNature(String inveNature) {
		this.inveNature = inveNature;
	}

    public String getRmbIntendedAmount() {
        return rmbIntendedAmount;
    }

    public void setRmbIntendedAmount(String rmbIntendedAmount) {
        this.rmbIntendedAmount = rmbIntendedAmount;
    }

    public String getInvePropertyF() {
		return invePropertyF;
	}

	public void setInvePropertyF(String invePropertyF) {
		this.invePropertyF = invePropertyF;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public List<FundLedgerMagModel> getFundLedgerMagModel() {
		return fundLedgerMagModel;
	}

	public void setFundLedgerMagModel(List<FundLedgerMagModel> fundLedgerMagModel) {
		this.fundLedgerMagModel = fundLedgerMagModel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInvestorCompanyName() {
		return investorCompanyName;
	}

	public void setInvestorCompanyName(String investorCompanyName) {
		this.investorCompanyName = investorCompanyName;
	}

	public Double getRmbInveAmount() {
		return rmbInveAmount;
	}

	public void setRmbInveAmount(Double rmbInveAmount) {
		this.rmbInveAmount = rmbInveAmount;
	}

	public String getInvestorPersonName() {
		return investorPersonName;
	}

	public void setInvestorPersonName(String investorPersonName) {
		this.investorPersonName = investorPersonName;
	}

	private FundShareItemModel fundShareItem;

	private String  isFinancingPlatform;

	private String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public FundShareItemModel getFundShareItem() {
		return fundShareItem;
	}

	public void setFundShareItem(FundShareItemModel fundShareItem) {
		this.fundShareItem = fundShareItem;
	}

	public FundInfoModel getFundInfoModel() {
		return fundInfoModel;
	}

	public void setFundInfoModel(FundInfoModel fundInfoModel) {
		this.fundInfoModel = fundInfoModel;
	}

	public String getIsFinancingPlatform() {
		return isFinancingPlatform;
	}

	public void setIsFinancingPlatform(String isFinancingPlatform) {
		this.isFinancingPlatform = isFinancingPlatform;
	}

	public InveInfoModel getInveInfoModel() {
		return inveInfoModel;
	}

	public void setInveInfoModel(InveInfoModel inveInfoModel) {
		this.inveInfoModel = inveInfoModel;
	}

	public String getInveRoleName() {
		return inveRoleName;
	}

	public void setInveRoleName(String inveRoleName) {
		this.inveRoleName = inveRoleName;
	}

	public String getCapitalSourceName() {
		return capitalSourceName;
	}

	public void setCapitalSourceName(String capitalSourceName) {
		this.capitalSourceName = capitalSourceName;
	}

	public String getInveTypeName() {
		return inveTypeName;
	}

	public void setInveTypeName(String inveTypeName) {
		this.inveTypeName = inveTypeName;
	}

	public String getInveAmountDisplay() {
		return inveAmountDisplay;
	}

	public void setInveAmountDisplay(String inveAmountDisplay) {
		this.inveAmountDisplay = inveAmountDisplay;
	}

	public String getTotalFinancialDisplay() {
		return totalFinancialDisplay;
	}

	public void setTotalFinancialDisplay(String totalFinancialDisplay) {
		this.totalFinancialDisplay = totalFinancialDisplay;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getGB() {
		return GB;
	}

	public void setGB(String GB) {
		this.GB = GB;
	}

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

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInveRole() {
		return inveRole;
	}

	public void setInveRole(String inveRole) {
		this.inveRole = inveRole;
	}

	public Double getInveAmount() {
		return inveAmount;
	}

	public void setInveAmount(Double inveAmount) {
		this.inveAmount = inveAmount;
	}

	public String getInveCurrency() {
		return inveCurrency;
	}

	public void setInveCurrency(String inveCurrency) {
		this.inveCurrency = inveCurrency;
	}

	public Double getTotalFinancial() {
		return totalFinancial;
	}

	public void setTotalFinancial(Double totalFinancial) {
		this.totalFinancial = totalFinancial;
	}

	public String getTotalFinCurrency() {
		return totalFinCurrency;
	}

	public void setTotalFinCurrency(String totalFinCurrency) {
		this.totalFinCurrency = totalFinCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCloseRound() {
		return closeRound;
	}

	public void setCloseRound(Long closeRound) {
		this.closeRound = closeRound;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

}