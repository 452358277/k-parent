package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class EnteMFinaInfoDTO extends BaseDTO {

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

    private Double payFee;

    private Double	payFeeY;

    private Double totalCompPackageY;


    private String financeFile;

   private Boolean isCheck;
}
