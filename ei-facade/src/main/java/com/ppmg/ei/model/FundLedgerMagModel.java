package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model]台账管理
 * @author root
 * @date 2019-09-03 14:21 
 */ 
@Data 
public class FundLedgerMagModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String ledgerId;

	/** 关联出资信息id */
	private String pkId;

	/** 收付款单号 */
	private String paymentOrder;

	/** 基金 */
	private String fundId;

	/** 关联明细计划 */
	private String relPlanId;

	/** 台帐类型 */
	private String ledgerType;
	private String ledgerTypeName;//242,多了：减资2，分红5

	/** 资金对象类型（1：LP，2：GP，3：管理公司，4：其他） */
	private String ledgerObject;
	private String ledgerObjectName;//244

	/** 资金对象 */
	private String objectId;

	/** 资金对象名称 */
	private String objectName;

	/** 证件号码 */
	private String idNo;

	/** 联系人 */
	private String contact;

	/** 联系方式 */
	private String contactNo;

	/** 开户行 */
	private String accountBank;

	/** 开户行账号 */
	private String accountNo;

	/** 支付方式 */
	private String payWay;//247
	private String payWayName;

	/** 退出方式 */
	private String quitWay;

	/** 发生时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date occurDt;

	/** 发生金额 （出资金额*）*/
	private Double occurAmount;

	/** 发生币种 （出资金额*）*/
	private String occurCurr;
	private String occurCurrName;

	/** 当前汇率折算为人民币 */
	private Double rmbInveAmount;

	/** 占比(%) */
	private Double sharesRate;

	/** 增加股息金额 */
	private Double amountAdd;

	/** 状态（0：正常，1：删除，2：已到帐） */
	private String status;

	/** 往来款后期计划资金类型 */
	private String laterPlan;

	/** 科目 */
	private String ledgerAccount;

	/** 备注 */
	private String remark;

	/** 台帐类型编辑备用 */
	private String ledgerTypeBk;

	/** 当前汇率折算为人民币编辑备用 */
	private Double rmbInveAmountBk;

	/** 是否有效 */
	private String isEffective;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getLedgerTypeName() {
		return ledgerTypeName;
	}

	public void setLedgerTypeName(String ledgerTypeName) {
		this.ledgerTypeName = ledgerTypeName;
	}

	public String getLedgerObjectName() {
		return ledgerObjectName;
	}

	public void setLedgerObjectName(String ledgerObjectName) {
		this.ledgerObjectName = ledgerObjectName;
	}

	public String getPayWayName() {
		return payWayName;
	}

	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}

	public String getOccurCurrName() {
		return occurCurrName;
	}

	public void setOccurCurrName(String occurCurrName) {
		this.occurCurrName = occurCurrName;
	}

	public String getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}

	public String getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(String paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getRelPlanId() {
		return relPlanId;
	}

	public void setRelPlanId(String relPlanId) {
		this.relPlanId = relPlanId;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

	public String getLedgerObject() {
		return ledgerObject;
	}

	public void setLedgerObject(String ledgerObject) {
		this.ledgerObject = ledgerObject;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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

	public String getQuitWay() {
		return quitWay;
	}

	public void setQuitWay(String quitWay) {
		this.quitWay = quitWay;
	}

	public Date getOccurDt() {
		return occurDt;
	}

	public void setOccurDt(Date occurDt) {
		this.occurDt = occurDt;
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

	public Double getRmbInveAmount() {
		return rmbInveAmount;
	}

	public void setRmbInveAmount(Double rmbInveAmount) {
		this.rmbInveAmount = rmbInveAmount;
	}

	public Double getSharesRate() {
		return sharesRate;
	}

	public void setSharesRate(Double sharesRate) {
		this.sharesRate = sharesRate;
	}

	public Double getAmountAdd() {
		return amountAdd;
	}

	public void setAmountAdd(Double amountAdd) {
		this.amountAdd = amountAdd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLaterPlan() {
		return laterPlan;
	}

	public void setLaterPlan(String laterPlan) {
		this.laterPlan = laterPlan;
	}

	public String getLedgerAccount() {
		return ledgerAccount;
	}

	public void setLedgerAccount(String ledgerAccount) {
		this.ledgerAccount = ledgerAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLedgerTypeBk() {
		return ledgerTypeBk;
	}

	public void setLedgerTypeBk(String ledgerTypeBk) {
		this.ledgerTypeBk = ledgerTypeBk;
	}

	public Double getRmbInveAmountBk() {
		return rmbInveAmountBk;
	}

	public void setRmbInveAmountBk(Double rmbInveAmountBk) {
		this.rmbInveAmountBk = rmbInveAmountBk;
	}

	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}
}