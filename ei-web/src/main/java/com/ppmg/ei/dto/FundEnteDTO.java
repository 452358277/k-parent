package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class FundEnteDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 企业年度财务情况ID */
    private String pkId;

    /** 企业名称 */
    private String enteName;

    /** 年度 */
    private String year;

    /** 职工总人数 */
    private Double staffNum;

    /** 科技研发人数 */
    private Double rdStaffNum;

    /** 总资产 */
    private Double totalAssets;

    /** 总负债 */
    private Double totalLiabilities;

    /** 所有者权益 */
    private Double ownerEquity;

    /** 总收 */
    private Double totalIncome;

    /** 利润总额 */
    private Double totalProfit;

    /** 净利润 */
    private Double netProfit;

    /** 研发费用 */
    private Double rdFee;

    /** 上缴费用 */
    private Double payFee;

    /** 其中上缴地方财政税费 */
    private Double localPayFee;

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


}
