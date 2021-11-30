package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class FundEnteExport implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 职工总人数 */
    private String year;

    @Excel(name = "年度",width = 30,orderNum = "1" ,needMerge = true)
    private String inveStr;

    @Excel(name = "职工总人数",width = 30,orderNum = "2",needMerge = true)
    private String staffNum;

    /** 科技研发人数 */
    @Excel(name = "科技研发人数", width = 30,orderNum = "3",needMerge = true)
    private String rdStaffNum;

    /** 总资产 */
    @Excel(name = "总资产（万元）", width = 30,orderNum = "4",needMerge = true)
    private String totalAssets;

    /** 总负债 */
    @Excel(name = "总负债（万元）",width = 30,orderNum = "5",needMerge = true)
    private String totalLiabilities;

    /** 所有者权益 */
    @Excel(name = "所有者权益（万元）",width = 30,orderNum = "29",needMerge = true)
    private String ownerEquity;

    /** 总收 */
    @Excel(name = "总收入（万元）", width = 30,orderNum = "30",needMerge = true)
    private String totalIncome;

    /** 利润总额 */
    @Excel(name = "利润总额（万元）", width = 30,orderNum = "31",needMerge = true)
    private String totalProfit;

    /** 净利润 */
    @Excel(name = "净利润（万元）", width = 30,orderNum = "32",needMerge = true)
    private String netProfit;

    /** 研发费用 */
    @Excel(name = "研发费用（万元）", width = 30,orderNum = "33",needMerge = true)
    private String rdFee;

    /** 上缴费用 */
    @Excel(name = "上缴税费（万元）", width = 30,orderNum = "34",needMerge = true,type = 1)
    private String payFee;


}
