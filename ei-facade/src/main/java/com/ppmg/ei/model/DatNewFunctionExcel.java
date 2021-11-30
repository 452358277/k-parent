package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class DatNewFunctionExcel extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 0 ,value = "出资主体id")
    private String inveId;

    @ExcelProperty(index = 1 ,value = "出资主体")
    private String inveName;

    @ExcelProperty(index = 2 ,value = "公司名称")
    private String projName;

    @ExcelProperty(index = 3 ,value = "公司成立时间")
    private String setupDt;

    @ExcelProperty(index = 4 ,value = "公司注册地")
    private String regAdd;

    @ExcelProperty(index = 5 ,value = "注册资本")
    private String regAmount;

    @ExcelProperty(index = 6 ,value = "实际投资额")
    private String intendedAmount;

    @ExcelProperty(index = 7 ,value = "占股比例(%)")
    private String perShare;

    @ExcelProperty(index = 8 ,value = "属于企业第几轮投资")
    private String finaRounds;

    @ExcelProperty(index = 9 ,value = "出资时间")
    private String occurDt;
/*
    @ExcelProperty(index = 10 ,value = "投资方式")
    private String inveType;*/

    @ExcelProperty(index = 10 ,value = "所属行业")
    private String industryName;

    //@ExcelProperty(index = 11 ,value = "公司名称")
    private String industry;

    @ExcelProperty(index = 11 ,value = "是否属于民营企业（是为1，否为0，外资或中外合资企业请备注）")
    private String label1;

    @ExcelProperty(index = 12 ,value = "是否属于科技型企业（是为1，否为0）")
    private String label2;

    @ExcelProperty(index = 13 ,value = "是否属于战略新兴产业")
    private String label3;

    //是否拥有董事席位
    @ExcelProperty(index = 14 ,value = "是否拥有董事席位")
    private String isDirectorCnt;

    @ExcelProperty(index = 15 ,value = "是否拥有监事席位")
    private String isSupervisorCnt;

    //企业投资时估值
    @ExcelProperty(index = 16 ,value = "公司注册地")
    private String postMoney;
//企业最新估值
  @ExcelProperty(index = 17 ,value = "企业投资时估值")
    private String entePostValue;

    @ExcelProperty(index = 18 ,value = "投资金额最新投后估值/市值")
    private String inveNewValue;

    //最新所占股比
    @ExcelProperty(index = 19 ,value = "最新所占股比")
    private String holdShare;

    @ExcelProperty(index = 20 ,value = "是否上市（是为1，否为0，已申报IPO填2）")
    private String isPublicComp;

    @ExcelProperty(index = 21 ,value = "企业阶段（初创期为1，其余为0）")
    private String phase;

    @ExcelProperty(index = 22 ,value = "是否省内企业（是为1，否为0")
    private String isProvince;

    @ExcelProperty(index = 23 ,value = "年度")
    private String year;

    @ExcelProperty(index = 24 ,value = "职工总人数")
    private Double staffNum;

    @ExcelProperty(index = 25 ,value = "科技研发人数")
    private Double rdStaffNum;

    @ExcelProperty(index = 26 ,value = "总资产")
    private Double totalAssets;

    @ExcelProperty(index = 27 ,value = "总负债")
    private Double totalLiabilities;

    @ExcelProperty(index = 28 ,value = "所有者权益")
    @Excel(name = "所有者权益",  width = 30)
    private Double ownerEquity;

    @ExcelProperty(index = 29 ,value = "总收入")
    private Double totalIncome;

    @ExcelProperty(index = 30 ,value = "利润总额")
    private Double totalProfit;

    //净利润
    @ExcelProperty(index = 31 ,value = "净利润")
    private Double netProfit;

    //研发费用
    @ExcelProperty(index = 32 ,value = "研发费用")
    private Double rdFee;

    @ExcelProperty(index = 33 ,value = "上缴税费")
    @Excel(name = "上缴税费",  width = 30)
    private Double payFee;

    @ExcelProperty(index = 34 ,value = "退出时间")
    private String occurDt1;

    @ExcelProperty(index = 35 ,value = "退出方式")
    private String exitType;

    @ExcelProperty(index = 36 ,value = "退出本金")
    private String occurAmount;

    @ExcelProperty(index = 37 ,value = "退出收益")
    private String occurAmount1;

    @ExcelProperty(index = 38 ,value = "亮点介绍")
    private String hlightsRemark;


}
