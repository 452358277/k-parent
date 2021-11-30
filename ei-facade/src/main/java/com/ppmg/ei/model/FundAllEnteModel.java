package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

@Data
public class FundAllEnteModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业年度财务情况ID */
	private String pkId;

	/** 企业名称 */
	private String enteName;

	/** 年度 */
	private String year;

	/** 职工总人数 */
	private String staffNum;

	/** 科技研发人数 */
	private String rdStaffNum;

	/** 总资产 */
	private String totalAssets;

	/** 总负债 */
	private String totalLiabilities;

	/** 所有者权益 */
	private String ownerEquity;

	/** 总收 */
	private String totalIncome;

	/** 利润总额 */
	private String totalProfit;

	/** 净利润 */
	private String netProfit;

	/** 研发费用 */
	private String rdFee;

	/** 上缴费用 */
	private String payFee;

	/** 其中上缴地方财政税费 */
	private String localPayFee;

	/** 备注 */
	private String remark;

	/** 状态 */
	private String status;

	/** 企业ID */
	private String enteId;

	/** 年报附件 */
	private String reportFile;

	/** 是否投资前上年度财务信息 */
	private String isPreInvest;


	private String inveStr;


}