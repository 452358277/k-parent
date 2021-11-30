package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class XjlTPaymentRequestVO extends BaseVO {
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

    /** 投决材料 */
    private String attaFile;
    /** 关联合同ID */
    private String contractFileId;

    private String receiverId2;

    private String receiver2;
    /** 实付款金额 */
    private Double actualPayAmount;
    /** 资金类型(码值242)*/
    private String amountType;

    private String payCurrencyName;

    private Double inveCurrentAmount;

    private Double intendedAmount;

    private Double applyAmount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date endTime;

    private String  openBank;

    private Double rmbActualPayAmount;

    private String inveName;

    private String projName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date  receivablesDate;

    private String  taskId;

    private Double sumRmbActualPayAmount;

}
