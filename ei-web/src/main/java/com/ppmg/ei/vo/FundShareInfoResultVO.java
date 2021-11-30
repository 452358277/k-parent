package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class FundShareInfoResultVO extends BaseVO {

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

    /** 认缴金额币种 */
    private String inveCurrency;

    /** 累计缴款额 */
    private Double totalFinancial;

    /** 累计出资币种 */
    private String totalFinCurrency;

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

    /** 合伙人名称 */
    private String investorName;

    /** 持股比例(%) */
    private String GB;
   /**认缴出资比例**/
    private String invePer;

    /**本次认缴出资比例**/
    private String thisInvePer;

    /**本次前累计实缴出资额**/

    private Double oldInveAmount;
    /** 未缴出资额**/
    private Double noInveAmount;
    /** 本次实缴出资额**/
    private Double newPaymentAmount;

    private String fundName;

    private String sumInveAmount1;

    private Double lastInveAmount;

    public Double getLastInveAmount() {
        return lastInveAmount;
    }

    public void setLastInveAmount(Double lastInveAmount) {
        this.lastInveAmount = lastInveAmount;
    }

    public String getSumInveAmount1() {
        return sumInveAmount1;
    }

    public void setSumInveAmount1(String sumInveAmount1) {
        this.sumInveAmount1 = sumInveAmount1;
    }

    public Double getNewPaymentAmount() {
        return newPaymentAmount;
    }

    public void setNewPaymentAmount(Double newPaymentAmount) {
        this.newPaymentAmount = newPaymentAmount;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Double getNoInveAmount() {
        return noInveAmount;
    }

    public void setNoInveAmount(Double noInveAmount) {
        this.noInveAmount = noInveAmount;
    }

    public String getThisInvePer() {
        return thisInvePer;
    }

    public void setThisInvePer(String thisInvePer) {
        this.thisInvePer = thisInvePer;
    }

    public Double getOldInveAmount() {
        return oldInveAmount;
    }

    public void setOldInveAmount(Double oldInveAmount) {
        this.oldInveAmount = oldInveAmount;
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

    public String getInveRoleName() {
        return inveRoleName;
    }

    public void setInveRoleName(String inveRoleName) {
        this.inveRoleName = inveRoleName;
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

    public String getCapitalSourceName() {
        return capitalSourceName;
    }

    public void setCapitalSourceName(String capitalSourceName) {
        this.capitalSourceName = capitalSourceName;
    }

    public String getInveType() {
        return inveType;
    }

    public void setInveType(String inveType) {
        this.inveType = inveType;
    }

    public String getInveTypeName() {
        return inveTypeName;
    }

    public void setInveTypeName(String inveTypeName) {
        this.inveTypeName = inveTypeName;
    }

    public Double getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(Double raiseAmount) {
        this.raiseAmount = raiseAmount;
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

    public String getInvePer() {
        return invePer;
    }

    public void setInvePer(String invePer) {
        this.invePer = invePer;
    }
}
