package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

@Data
public class BdTFundCostInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String id;

	/** 附件 */
	private String costFile;

	/** 备注 */
	private String remark;

	/** null */
	private String fundId;

	/** 年度 */
	private String year;

	/** 季度 */
	private String quarter;

	/** 资产总额 */
	private Double totalAssets;

	/** 产品成立以来累计管理费 */
	private Double totalManage;

	/** 产品成立以来累计托管费/保管费 * */
	private Double totalDeposit;

	/** 产品成立以来累计投资顾问费  */
	private Double totalConsult;

	/** 累计审计费 */
	private Double totalAudit;

	/** 累计律师费 */
	private Double totalLawyer;

	/** 产品成立以来累计其他费用 */
	private Double totalOther;

	/** null */
	private String fileDesc;

	/** 0 正常 9删除 */
	private String status;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date time;

	/** null */
	private Double totalFood;

	/** 估值基准日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessmentDate;

	/** 期末总资产 * */
	private Double endTotalAssets;

	/** 期末净资产 * */
	private Double endNetAssets;

	/** 产品成立以来累计已付管理费  */
	private Double totalManagePay;

	/** 产品成立以来累计已付投资顾问费 * */
	private Double totalConsultPay;

	/** 产品成立以来累计已付托管费/保管费  */
	private Double totalDepositPay;

	/** 产品成立以来累计运营服务费/外包服务费 */
	private Double totalOutsource;

	/** 产品成立以来累计已付运营服务费/外包服务费 * */
	private Double totalOutsourcePay;

	/** 产品成立以来累计业绩报酬  */
	private Double totalAchievement;

	/** 产品成立以来累计已付业绩报酬  */
	private Double totalAchievementPay;

	/** 产品成立以来累计分红 * */
	private Double totalBonus;

	/** 产品成立以来累计本金、收益分配 */
	private Double totalProfit;

	/** 剩余可用资金-预留管理费、审计费等 */
	private Double totalResidue;

	/** 剩余可用资金-可用于投资资金 */
	private Double totalAvailableFunds;
}