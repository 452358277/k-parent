package com.ppmg.ei.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.ppmg.ei.model.FundAccountDetaiExport;
import com.ppmg.ei.model.LedgeMagExportInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LedgeExportVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "序号", width = 20,needMerge = true,orderNum = "0")
    private Integer rowNums;

    @Excel(name = "基金名称",  width = 20,needMerge = true ,orderNum = "1")
    private String regName;

    @Excel(name = "基金认缴总额（万元）", width = 20,needMerge = true ,orderNum = "2" ,numFormat ="##.00000")
    private Double currentAmount;

    @Excel(name = "基金实缴总额（万元）", width = 20,needMerge = true ,orderNum = "3" ,numFormat ="##.00000" )
    private Double raiseAmount;

    @Excel(name = "金财投资认缴总额（万元）", width = 20,needMerge = true ,orderNum = "4")
    private String jcRmbInveAmount;
    //基金实缴总额明细
   @ExcelCollection(name = "金财投资实缴总额（万元）",orderNum = "5")
    private List<LedgeMagExportInfo> listInfo;

    @Excel(name = "基金已投资金额（万元）", width = 20,needMerge = true ,orderNum = "7")
    private String sumRaiseAmount;


    @Excel(name = "基金已回收金额（万元）", width = 20,needMerge = true ,orderNum = "8")
    private String sumBackAmount;

    @Excel(name = "基金账户余额（万元）", width = 20,needMerge = true ,orderNum = "9")
    private String newAccountBalance;

    @ExcelCollection(name = "",orderNum = "10")
    private List<FundAccountDetaiExport> listFundAccountDetail;

    @Excel(name = "合计", width = 20,needMerge = true ,orderNum = "13")
    private String sumDisJcAmountStr;


    @Excel(name = "剩余未回收实缴出资金额（万元）", width = 20,needMerge = true ,orderNum = "14")
    private String surplusAmount;

    //实缴出资回收率
    @Excel(name = "实缴出资回收率(%)", width = 20,needMerge = true ,orderNum = "15")
    private String percent;
    //利润
    @Excel(name = "利润（万元）", width = 20,needMerge = true ,orderNum = "16")
    private String profitAmount;


}
