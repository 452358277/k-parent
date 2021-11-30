package com.ppmg.ei.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class HyNewFunctionExcel extends BaseRowModel implements Serializable {

  /*  @ExcelProperty(index = 0 ,value = "出资主体id")
    private String inveId;*/

/*    @ExcelProperty(index = 0 ,value = "公司名称")
    private String projName;*/

   /* @ExcelProperty(index = 2 ,value = "占股比例(%)")
    private String perShare;*/


    @ExcelProperty(index = 0 ,value = "公司名称")
    private String projName;

    @ExcelProperty(index = 1 ,value = "所属行业")
    private String industryName;

    private String industry;

/*    @ExcelProperty(index = 2 ,value = "出资时间")
    private String inveTime;

    @ExcelProperty(index = 3 ,value = "年度")
    private String year;

    @ExcelProperty(index = 4 ,value = "职工总人数")
    private Double staffNum;

    @ExcelProperty(index = 5 ,value = "科技研发人数")
    private Double rdStaffNum;

    @ExcelProperty(index = 6 ,value = "总资产")
    private Double totalAssets;

    @ExcelProperty(index = 7 ,value = "总负债")
    private Double totalLiabilities;

    @ExcelProperty(index = 8 ,value = "所有者权益")
    @Excel(name = "所有者权益",  width = 30)
    private Double ownerEquity;

    @ExcelProperty(index = 9 ,value = "总收入")
    private Double totalIncome;

    @ExcelProperty(index = 10 ,value = "利润总额")
    private Double totalProfit;

    @ExcelProperty(index = 11 ,value = "净利润")
    private Double netProfit;

    @ExcelProperty(index = 12 ,value = "研发费用")
    private Double rdFee;

    @ExcelProperty(index = 13 ,value = "上缴税费")
    @Excel(name = "上缴税费",  width = 30)
    private Double payFee;*/



}
