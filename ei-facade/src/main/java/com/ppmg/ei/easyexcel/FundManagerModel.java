package com.ppmg.ei.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class FundManagerModel extends BaseRowModel implements Serializable {

    /**
     * 无参构造函数是导入的必要条件
     */
    public FundManagerModel() {}


    @ExcelProperty(index = 1 ,value = "四级基金名称")
    private String fundName;



    @ExcelProperty(index = 2 ,value = "是否属于SPV基金")
    private String isSpv;

    @ExcelProperty(index = 3 ,value = "SPV基金种类")
    private String spvType;

    @ExcelProperty(index = 4 ,value = "四级基金形式")
    private String fundStruct;


    @ExcelProperty(index =5 ,value = "项目状态")
    private String projStatus;

    @ExcelProperty(index =6 ,value = "四级基金注册状态")
    private String fundSts;

    @ExcelProperty(index =7,value = "四级基金备案情况")
    private String isRecord;

    @ExcelProperty(index =8,value = "四级基金备案号")
    private String recordCode;

    @ExcelProperty(index =9,value = "是否关联交易")
    private String isCorr;

    @ExcelProperty(index =10,value = "三级基金名称")
    private String inveName;

    @ExcelProperty(index =11,value = "三级基金统一社会信用代码")
    private String socialCode;

    @ExcelProperty(index =12,value = "项目是否属于江苏省内企业")
    private String isJs;


    @ExcelProperty(index =13,value = "四级基金目标规模（万元）")
    private String planAmount;

    @ExcelProperty(index =14,value = "四级基金认缴总规模（万元）")
    private String currentAmount;

    @ExcelProperty(index =15,value = "其中：三级基金认缴规模（万元")
    private String inveCurrentAmount;

    @ExcelProperty(index =16,value = "四级基金实缴总规模（万元）")
    private String raiseAmount;

    @ExcelProperty(index =17,value = "其中：三级基金实缴金额（万元）")
    private String inveRaiseAmount;

    @ExcelProperty(index =18,value = "三级基金已拨付金额")
    private String actualPayAmount;

    @ExcelProperty(index =19,value = "投资时间")
    private String actualPayDate;

    @ExcelProperty(index =20,value = "投决是否通过")
    private String isDecisionPass;

    @ExcelProperty(index =21,value = "投决会召开时间")
    private String decisionDate;

    @ExcelProperty(index =22,value = "退出状态")
    private String quitType;

    @ExcelProperty(index =23,value = "已退还三级基金的金额（万元）")
    private String revenue;

    @ExcelProperty(index =24,value = "退出日期")
    private String quitDt;
}
