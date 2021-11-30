package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class FundFinanceVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 财务信息ID */
    private String financeId;

    /** 基金ID */
    private String fundId;

    /** 季度 */
    private String quarter;

    private String quarterName;

    /** 本季度管理费 */
    private Double manageFee;

    /** 本季度资金托管费用 */
    private Double collocationFee;

    /** 本季度其他费用 */
    private Double otherFee;

    /** 本季度利息收入 */
    private Double interestIncome;

    /** 本季度投资收益 */
    private Double investIncome;

    /** 本季度其他收益 */
    private Double otherIncome;

    /** 本季度结余 */
    private Double surplus;

    /** 状态 */
    private String status;

    private String statusName;

    /** 年份 */
    private String year;

    /** 累计管理费 */
    private Double totalManageFee;

    /** 累计资金托管费用 */
    private Double totalCollocationFee;

    /** 累计其他费用 */
    private Double totalOtherFee;

    /** 累计利息收入 */
    private Double totalInterestIncome;

    /** 累计投资收益 */
    private Double totalInvestIncome;

    /** 累计其他收益 */
    private Double totalOtherIncome;

    /** 累计结余 */
    private Double totalSurplus;

    private String financeFile;
}
