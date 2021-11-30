package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundLedgerMagVO extends BaseVO {
    /** 主键 */
    private String ledgerId;
    //投资人名称
    private String investorIdName;

    /** 资金对象（1：LP，2：GP，3：管理公司，4：其他） */
    private String ledgerObject;
    private String ledgerObjectName;//244
    /** 资金类型*/
    private  String ledgerType;
    private  String ledgerTypeName;
    /** 出资金额 */
    private Double occurAmount;
    /** 出资金额币种*/
    private String occurCurr;
    private String occurCurrName;
    /** 发生金额*/
    private Double inveAmount;
    /** 发生金额币种 */
    private String inveCurrency;
    private String inveCurrencyName;
    /** 当前汇率折算为人民币 */
    private Double rmbInveAmount;
    /** 发生时间 （出资时间）*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date occurDt;
    /** 基金 */
    private String fundId;
    /** LP名称 */
    private String objectId;
    /** 联系人 */
    private String contact;

    /** 联系方式 */
    private String contactNo;

    /** 开户行 */
    private String accountBank;

    /** 开户行账号 */
    private String accountNo;

    /** 支付方式 */
    private String payWay;
    private String payWayName;
    /** 备注 */
    private String remark;

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

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getInvestorIdName() {
        return investorIdName;
    }

    public void setInvestorIdName(String investorIdName) {
        this.investorIdName = investorIdName;
    }

    public String getLedgerObject() {
        return ledgerObject;
    }

    public void setLedgerObject(String ledgerObject) {
        this.ledgerObject = ledgerObject;
    }

    public String getLedgerObjectName() {
        return ledgerObjectName;
    }

    public void setLedgerObjectName(String ledgerObjectName) {
        this.ledgerObjectName = ledgerObjectName;
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

    public Date getOccurDt() {
        return occurDt;
    }

    public void setOccurDt(Date occurDt) {
        this.occurDt = occurDt;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
