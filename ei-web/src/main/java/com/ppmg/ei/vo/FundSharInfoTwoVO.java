package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundLedgerMagModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FundSharInfoTwoVO extends BaseVO {
    /** 主键 */
    private String pkId;
    /** 基金编号 */
    private String fundId;
    /** 投资人ID */
    private String investorId;
    //投资人名称
    private String investorIdName;
    /** 投资人类型*/
    private String inveType;
    private String inveTypeName;
    /** 投资角色(1:LP,2:GP,3:GM) */
    private String inveRole;
    private String inveRoleName;
    /** 投资人性质 */
    private String inveNature;
    private String inveNatureName;

    /** 认缴金额 */
    private Double inveAmount;
    /** 认缴金额币种 */
    private String inveCurrency;
    private String inveCurrencyName;

    /** 认缴金额折算人民币 */
    private Double rmbIntendedAmount;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 股比 */
    private String GB;
    /* 累计出资金额*/
    private Double totalFinancial;
    /** 累计出资币种 */
    private String totalFinCurrency;
    private String totalFinCurrencyName;
    /**出资进度*/
    private String schedule;
    /*台账管理*/
    //private List<FundLedgerMagModel> fundLedgerMagModel;

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

    public String getInvestorIdName() {
        return investorIdName;
    }

    public void setInvestorIdName(String investorIdName) {
        this.investorIdName = investorIdName;
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

    public String getInveNature() {
        return inveNature;
    }

    public void setInveNature(String inveNature) {
        this.inveNature = inveNature;
    }

    public String getInveNatureName() {
        return inveNatureName;
    }

    public void setInveNatureName(String inveNatureName) {
        this.inveNatureName = inveNatureName;
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

    public String getInveCurrencyName() {
        return inveCurrencyName;
    }

    public void setInveCurrencyName(String inveCurrencyName) {
        this.inveCurrencyName = inveCurrencyName;
    }

    public Double getRmbIntendedAmount() {
        return rmbIntendedAmount;
    }

    public void setRmbIntendedAmount(Double rmbIntendedAmount) {
        this.rmbIntendedAmount = rmbIntendedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGB() {
        return GB;
    }

    public void setGB(String GB) {
        this.GB = GB;
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

    public String getTotalFinCurrencyName() {
        return totalFinCurrencyName;
    }

    public void setTotalFinCurrencyName(String totalFinCurrencyName) {
        this.totalFinCurrencyName = totalFinCurrencyName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /*public List<FundLedgerMagModel> getFundLedgerMagModel() {
        return fundLedgerMagModel;
    }

    public void setFundLedgerMagModel(List<FundLedgerMagModel> fundLedgerMagModel) {
        this.fundLedgerMagModel = fundLedgerMagModel;
    }*/
}
