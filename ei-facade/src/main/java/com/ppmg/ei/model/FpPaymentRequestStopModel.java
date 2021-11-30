package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-10-22 14:46 
 */ 
@Data 
public class FpPaymentRequestStopModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 支付申请号 */
	private String pqId;

	/** 申请人 */
	private String appUser;

	/** 申请日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date appDt;

	/** 项目 */
	private String projId;

	/** 关联付款计划 */
	private String relPlanId;

	/** 项目组意见 */
	private String projTeamAgree;

	/** 本期付款金额 */
	private Double curPayAmount;

	/** 本期付款金额币种 */
	private String curPayCurrency;

	/** 公司投资总额 */
	private Double totalInveAmount;

	/** 公司投资总额币种 */
	private String totalInveCurrency;

	/** 累计付款金额 */
	private Double sumInveAmount;

	/** 累计付款金额币种 */
	private String sumInveCurrency;

	/** 付款总共期数 */
	private Long totalPeriod;

	/** 当前期数 */
	private Long currentPeriod;

	/** 未付款金额 */
	private Double unpaidAmount;

	/** 未付款金额币种 */
	private String unpaidCurrency;

	/** 付款目的 */
	private String payReason;

	/** 收款人名称 */
	private String payeeName;

	/** 收款银行 */
	private String dueBank;

	/** 收款账号 */
	private String receivableAccount;

	/** 资金来源 */
	private String fundsSrc;

	/** 付款账号 */
	private String paymentAccount;

	/** 相关文档 */
	private String relFile;

	/** 表单申请状态 */
	private String status;

	/** 流程编号 */
	private String processInstId;

	/** 公司投资总额(折算人民币) */
	private Double totalInveAmountRmb;

	/** 前期资金到位情况说明 */
	private String preMoneyImplDesc;

	/** 前期资金使用情况说明 */
	private String preMoneyUserDesc;

	/** 本期资金用途 */
	private String curMoneyPurpose;

	/** 前期资金到位情况说明附件 */
	private String preMoneyImplDescAtt;

	/** 前期资金使用情况说明附件 */
	private String preMoneyUserDescAtt;

	/** 本期资金用途附件 */
	private String curMoneyPurposeAtt;

	/** 本期付款金额(折算人民币) */
	private Double curPayAmountRmb;

	/** 资金情况说明 */
	private String moneyDesc;

	/** 资金情况说明附件 */
	private String moneyDescAtt;

}