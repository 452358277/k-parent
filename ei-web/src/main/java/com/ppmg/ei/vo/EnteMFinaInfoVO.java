package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class EnteMFinaInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 年月 */
	private String occurMonth;

	/** 企业编号 */
	private String enterpriseId;

	/** 货币类型 */
	private String currencyType;

	/** 薪酬总额 */
	private Double totalCompPackage;

	/** 每月财务报表 */
	private String finaStatements;

	/** 固定资产净值 */
	private Double fixedAssetsNetValue;

	/** 无形资产净值 */
	private Double intaAssetsNetValue;

	/** 货币资金 */
	private Double moneyFunds;

	/** 总资产 */
	private Double totalAssets;

	/** 应收账款 */
	private Double accountsReceivable;

	/** 其它应收账款 */
	private Double otherReceivables;

	/** 存款 */
	private Double deposit;

	/** 流动资产 */
	private Double currentAssets;

	/** 短期借款 */
	private Double shortTermBorrowing;

	/** 长期借款 */
	private Double longTermBorrowing;

	/** 流动负债 */
	private Double currentLiabilities;

	/** 总负债 */
	private Double totalLiabilities;

	/** 股本(实收资本) */
	private Double shareCapital;

	/** 所有者权益 */
	private Double ownerEquity;

	/** 主营业务收入 */
	private Double mainBizIncome;

	/** 主营业务成本 */
	private Double mainBizCost;

	/** 当月总收入 */
	private Double totalRevenue;

	/** 毛利率（%） */
	private Double grossMargin;

	/** 管理费用 */
	private Double magFee;

	/** 研发费用 */
	private Double rdExpense;

	/** 当期利润 */
	private Double currentProfit;

	/** 当期净利润 */
	private Double currentNetProfit;

	/** 审计报告附件 */
	private String auditReport;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 当年累计营业收入 */
	private Double yearIncome;

	/** null */
	private String rowId;

	/** null */
	private String projGuid;

	/** 企业外网信息确认 */
	private String confirm;

	/** 年度值(对应月度值) */
	private Double mainBizIncomeY;

	/** 年度值(对应月度值) */
	private Double mainBizCostY;

	/** 年度值(对应月度值) */
	private Double totalRevenueY;

	/** 年度值(对应月度值) */
	private Double grossMarginY;

	/** 年度值(对应月度值) */
	private Double magFeeY;

	/** 年度值(对应月度值) */
	private Double rdExpenseY;

	/** 年度值(对应月度值) */
	private Double currentProfitY;

	/** 年度值(对应月度值) */
	private Double currentNetProfitY;

	/** 是否提供财务数据code 102 */
	private String finadata;

	/** 备注 */
	private String remark;

	/** 出资主体ID */
	private String investId;

	/** 企业人事推送HANDLE_ID */
	private String handleId;

	/** 填写人 */
	private String createName;

	private String statusName;

	private Double payFee;

	private Double	payFeeY;

	private Double totalCompPackageY;


	private String financeFile;


	private String currencyTypeName;

	public String getFinanceFile() {
		return financeFile;
	}

	public void setFinanceFile(String financeFile) {
		this.financeFile = financeFile;
	}

	public String getCurrencyTypeName() {
		return currencyTypeName;
	}

	public void setCurrencyTypeName(String currencyTypeName) {
		this.currencyTypeName = currencyTypeName;
	}

	public Double getPayFee() {
		return payFee;
	}

	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}

	public Double getPayFeeY() {
		return payFeeY;
	}

	public void setPayFeeY(Double payFeeY) {
		this.payFeeY = payFeeY;
	}

	public Double getTotalCompPackageY() {
		return totalCompPackageY;
	}

	public void setTotalCompPackageY(Double totalCompPackageY) {
		this.totalCompPackageY = totalCompPackageY;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getOccurMonth() {
		return occurMonth;
	}

	public void setOccurMonth(String occurMonth) {
		this.occurMonth = occurMonth;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Double getTotalCompPackage() {
		return totalCompPackage;
	}

	public void setTotalCompPackage(Double totalCompPackage) {
		this.totalCompPackage = totalCompPackage;
	}

	public String getFinaStatements() {
		return finaStatements;
	}

	public void setFinaStatements(String finaStatements) {
		this.finaStatements = finaStatements;
	}

	public Double getFixedAssetsNetValue() {
		return fixedAssetsNetValue;
	}

	public void setFixedAssetsNetValue(Double fixedAssetsNetValue) {
		this.fixedAssetsNetValue = fixedAssetsNetValue;
	}

	public Double getIntaAssetsNetValue() {
		return intaAssetsNetValue;
	}

	public void setIntaAssetsNetValue(Double intaAssetsNetValue) {
		this.intaAssetsNetValue = intaAssetsNetValue;
	}

	public Double getMoneyFunds() {
		return moneyFunds;
	}

	public void setMoneyFunds(Double moneyFunds) {
		this.moneyFunds = moneyFunds;
	}

	public Double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getAccountsReceivable() {
		return accountsReceivable;
	}

	public void setAccountsReceivable(Double accountsReceivable) {
		this.accountsReceivable = accountsReceivable;
	}

	public Double getOtherReceivables() {
		return otherReceivables;
	}

	public void setOtherReceivables(Double otherReceivables) {
		this.otherReceivables = otherReceivables;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getCurrentAssets() {
		return currentAssets;
	}

	public void setCurrentAssets(Double currentAssets) {
		this.currentAssets = currentAssets;
	}

	public Double getShortTermBorrowing() {
		return shortTermBorrowing;
	}

	public void setShortTermBorrowing(Double shortTermBorrowing) {
		this.shortTermBorrowing = shortTermBorrowing;
	}

	public Double getLongTermBorrowing() {
		return longTermBorrowing;
	}

	public void setLongTermBorrowing(Double longTermBorrowing) {
		this.longTermBorrowing = longTermBorrowing;
	}

	public Double getCurrentLiabilities() {
		return currentLiabilities;
	}

	public void setCurrentLiabilities(Double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public Double getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(Double totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public Double getShareCapital() {
		return shareCapital;
	}

	public void setShareCapital(Double shareCapital) {
		this.shareCapital = shareCapital;
	}

	public Double getOwnerEquity() {
		return ownerEquity;
	}

	public void setOwnerEquity(Double ownerEquity) {
		this.ownerEquity = ownerEquity;
	}

	public Double getMainBizIncome() {
		return mainBizIncome;
	}

	public void setMainBizIncome(Double mainBizIncome) {
		this.mainBizIncome = mainBizIncome;
	}

	public Double getMainBizCost() {
		return mainBizCost;
	}

	public void setMainBizCost(Double mainBizCost) {
		this.mainBizCost = mainBizCost;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Double getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	public Double getMagFee() {
		return magFee;
	}

	public void setMagFee(Double magFee) {
		this.magFee = magFee;
	}

	public Double getRdExpense() {
		return rdExpense;
	}

	public void setRdExpense(Double rdExpense) {
		this.rdExpense = rdExpense;
	}

	public Double getCurrentProfit() {
		return currentProfit;
	}

	public void setCurrentProfit(Double currentProfit) {
		this.currentProfit = currentProfit;
	}

	public Double getCurrentNetProfit() {
		return currentNetProfit;
	}

	public void setCurrentNetProfit(Double currentNetProfit) {
		this.currentNetProfit = currentNetProfit;
	}

	public String getAuditReport() {
		return auditReport;
	}

	public void setAuditReport(String auditReport) {
		this.auditReport = auditReport;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(Double yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Double getMainBizIncomeY() {
		return mainBizIncomeY;
	}

	public void setMainBizIncomeY(Double mainBizIncomeY) {
		this.mainBizIncomeY = mainBizIncomeY;
	}

	public Double getMainBizCostY() {
		return mainBizCostY;
	}

	public void setMainBizCostY(Double mainBizCostY) {
		this.mainBizCostY = mainBizCostY;
	}

	public Double getTotalRevenueY() {
		return totalRevenueY;
	}

	public void setTotalRevenueY(Double totalRevenueY) {
		this.totalRevenueY = totalRevenueY;
	}

	public Double getGrossMarginY() {
		return grossMarginY;
	}

	public void setGrossMarginY(Double grossMarginY) {
		this.grossMarginY = grossMarginY;
	}

	public Double getMagFeeY() {
		return magFeeY;
	}

	public void setMagFeeY(Double magFeeY) {
		this.magFeeY = magFeeY;
	}

	public Double getRdExpenseY() {
		return rdExpenseY;
	}

	public void setRdExpenseY(Double rdExpenseY) {
		this.rdExpenseY = rdExpenseY;
	}

	public Double getCurrentProfitY() {
		return currentProfitY;
	}

	public void setCurrentProfitY(Double currentProfitY) {
		this.currentProfitY = currentProfitY;
	}

	public Double getCurrentNetProfitY() {
		return currentNetProfitY;
	}

	public void setCurrentNetProfitY(Double currentNetProfitY) {
		this.currentNetProfitY = currentNetProfitY;
	}

	public String getFinadata() {
		return finadata;
	}

	public void setFinadata(String finadata) {
		this.finadata = finadata;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

}