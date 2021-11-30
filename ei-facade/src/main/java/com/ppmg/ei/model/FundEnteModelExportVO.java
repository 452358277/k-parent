package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

@Data
public class FundEnteModelExportVO extends BaseModel {

    @Excel(name = "年度",  width = 30)
    private String year;

    //
    @Excel(name = "职工总人数",  width = 30)
    private Double staffNum;

    //科技研发人数
    @Excel(name = "科技研发人数",  width = 30)
    private Double rdStaffNum;

    //总资产
    @Excel(name = "总资产",  width = 30)
    private Double totalAssets;

    //总负债
    @Excel(name = "总负债",  width = 30)
    private Double totalLiabilities;

    //所有者权益
    @Excel(name = "所有者权益",  width = 30)
    private Double ownerEquity;

    //总收入
    @Excel(name = "总收入",  width = 30)
    private Double totalIncome;

    //利润总额
    @Excel(name = "利润总额",  width = 30)
    private Double totalProfit;

    //净利润
    @Excel(name = "净利润",  width = 30)
    private Double netProfit;

    //研发费用
    @Excel(name = "研发费用",  width = 30)
    private Double rdFee;

    //上缴税费
    @Excel(name = "上缴税费",  width = 30)
    private Double payFee;


}
