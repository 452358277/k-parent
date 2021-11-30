package com.ppmg.ei.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导入业务管理员的model
 * @author yuyongjun
 * @date 2019/6/22 11:18
 */
@Data
public class BusinessManagerModel extends BaseRowModel implements Serializable {

    /**
     * 无参构造函数是导入的必要条件
     */
    public BusinessManagerModel() {}

    /** 登录名 */
    @ExcelProperty(index = 1 ,value = "企业名称")
    private String entName;

    /** 姓名 */
    @ExcelProperty(index = 2 ,value = "企业统一社会信用代码")
    private String creditCode;

    @ExcelProperty(index = 3 ,value = "项目状态")
    private String projStatus;

    @ExcelProperty(index = 4 ,value = "子基金名称")
    private String fundName;

    @ExcelProperty(index = 5 ,value = "子基金统一社会信用代码")
    private String socialCode;

    @ExcelProperty(index = 6 ,value = "是否属于中小企业")
    private String isZx;

    @ExcelProperty(index = 7 ,value = "是否属于高新技术行业")
    private String isGx;


    @ExcelProperty(index = 8 ,value = "是否属于国家一带一路战略")
    private String isYdyl;


    /** 描述 */
    @ExcelProperty(index = 9 ,value = "是否属于战略新兴产业")
    private String isZlxx;

    @ExcelProperty(index = 10 ,value = "企业性质")
    private String projProperty;


    @ExcelProperty(index = 11 ,value = "项目所属行业/领域")
    private String industryName;

    @ExcelProperty(index = 12 ,value = "项目所属行业/领域二级行业")
    private String industryNameTwo;

    @ExcelProperty(index =13 ,value = "项目所属行业/领域/二级行业")
    private String industryNameThree;

    @ExcelProperty(index = 14 ,value = "战略性新兴行业")
    private String newIndustryName;

    @ExcelProperty(index = 15 ,value = "战略性新兴行业/二级行业")
    private String newIndustryNameTwo;

    @ExcelProperty(index = 16 ,value = "战略性新兴行业/三级行业")
    private String newIndustryNameThree;

    @ExcelProperty(index = 17 ,value = "是否属于江苏省内项目")
    private String js;

    @ExcelProperty(index = 18 ,value = "项目属第12条江苏省内企业的何种情形")
    private String isTwelve;

    @ExcelProperty(index = 19,value = "项目属第9条江苏省内企业的何")
    private String isTwelveNine;

    @ExcelProperty(index = 20,value = "被投企业融资轮次")
    private String finaRounds;

    @ExcelProperty(index = 21,value = "被投企业融资轮次")
    private String finaTimes;


    @ExcelProperty(index = 22,value = "被投企业融资金额（万元）")
    private String finaAmount;


    @ExcelProperty(index = 23,value = "投资次数")
    private String inveRounds;

    @ExcelProperty(index = 24,value = "本轮投资金额（万元）")
    private String intendedAmount;


    @ExcelProperty(index = 25,value = "其中：本基金投资金额（万元）")
    private String actualAmount;


    @ExcelProperty(index = 26,value = "本基金投资金额占本基金认缴出资总额的比例（%）")
    private String curTmoneyPer;



    @ExcelProperty(index = 27,value = "本基金对项目累计投资金额（万元）")
    private String allTmoney;


    @ExcelProperty(index = 28,value = "本基金对项目的累计出资比例（%）")
    private String funToPro;


    @ExcelProperty(index = 29,value = "本基金对项目累计投资金额占本基金认缴出资总额的比例（%）")
    private String allTmoneyPer;

    @ExcelProperty(index = 30,value = "本基金已拨付金额（万元）")
    private String actualPayAmount;

    @ExcelProperty(index = 31,value = "投资时间")
    private String actualPayDate;


    @ExcelProperty(index = 32,value = "本轮估值（万元")
    private String preMoney;


    @ExcelProperty(index = 33,value = "最新估值（万元）")
    private String postMoney;

    @ExcelProperty(index = 34,value = "投资类型")
    private String inveType;

    @ExcelProperty(index = 35,value = "投资角色")
    private String inveRole;

    @ExcelProperty(index = 36,value = "退出状态")
    private String quitType;

    @ExcelProperty(index = 37,value = "已退还本基金的金额（万元）")
    private String revenue;

    @ExcelProperty(index = 38,value = "退出日期")
    private String quitDt;

    private String isDistribute;
    //分配金额
    private String distributeMoney;
    //其中省政府投资基金分配额
    private String distributeGovMoney;
    //分配金额
    private String distributeDt;


    List<String> labels = new ArrayList<String>();

    private String industry;

    private String newIndustry;
}
