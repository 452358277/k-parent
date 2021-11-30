package com.ppmg.ei.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class FundInfoExport  extends BaseRowModel {

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","序号 "},index = 0)
    private Integer rowNums;

    /** 项目名称 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","基金名称"},index = 1)
    private String regName;

    /** 上一年完成数 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","基金管理人/合作方名称"},index = 2)
    private String mcName;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","出资主体"},index = 3)
    private String inveName;


    /** 本年目标 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","基金设立时间"},index = 4)
    private String setupDtStr;

    /** 目标增幅 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","合作期限（年）"},index = 5)
    private String cooperationYear;

    /** 本年累计完成数 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资期起算日"},index = 6)
    private String inveStartDateStr;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资截止日"},index = 7)
    private String inveEndDateStr;

    /** 序时进度 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","基金认缴总额（万元）"},index = 8)
    private Double currentAmount;

    /** 去年同期数 */
    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","基金实缴总额（万元）"},index = 9)
    @NumberFormat("##.0000")
    private Double raiseAmount;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","金财公司认缴总额（万元）"},index = 10)
    @NumberFormat("##.0000")
    private Double jcCurrentAmount;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","金财公司实缴总额（万元"},index = 11)
    @NumberFormat("##.0000")
    private Double jcRaiseAmount;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资项目企业数量（家）"},index = 12)
    private String projGs;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资企业数量（家）"},index = 13)
    private String enteGs;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资项目企业金额（万元）"},index = 14)
    private String projAmount;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资省内企业数量（家）"},index = 15)
    private String provinceGs;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","投资省内企业金额（万元）"},index = 16)
    @NumberFormat("##.0000")
    private Double provinceAmount;



    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","已上市数量（家）"},index = 17)
    private String ssGs;


    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","已申报IPO数量（家）"},index = 18)
    private String ipoGs;


    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","已挂牌新三板数量（家）"},index = 19)
    private String newBoardGs;

    @ExcelProperty(value = {"江苏金财投资有限公司参股子基金基本情况一览表","是否退出"},index = 20)
    private String projStatusName;


}
