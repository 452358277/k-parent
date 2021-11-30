package com.ppmg.ei.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProjInfoExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "序号",groupName = "基本情况", width = 20,needMerge = true,orderNum = "0")
    private Integer rowNums;

    @Excel(name = "出资主体", groupName = "基本情况", width = 20,needMerge = true ,orderNum = "1")
    private String inveName;

    @Excel(name = "公司名称",groupName = "基本情况", width = 20,needMerge = true ,orderNum = "2")
    private String enteName;

    /** 项目立项时间 */
    @Excel(name = "公司成立时间",exportFormat = "yyyy-MM-dd", groupName = "基本情况", width = 20,needMerge = true ,orderNum = "3")
    private Date setupDt;

    @Excel(name = "公司注册地", groupName = "基本情况", width = 20,needMerge = true ,orderNum = "4")
    private String regAdd;

    @Excel(name = "注册资本", groupName = "基本情况", width = 20,needMerge = true ,orderNum = "5")
    private String registCapi;

    @Excel(name = "实际投资额（万元）",groupName = "基本情况",  width = 20,needMerge = true ,orderNum = "6")
    private Double rmbIntendedAmount;

    @Excel(name = "占股比例(%)", groupName = "基本情况", width = 20,needMerge = true ,orderNum = "7")
    private Double perShare;

    @Excel(name = "占基金总认缴比例(%)", groupName = "基本情况", width = 20,needMerge = true,orderNum = "8")
    private String percent;

    @Excel(name = "投资轮次",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "9")
    private String  finaRounds;;

    @Excel(name = "出资时间",exportFormat = "yyyy-MM-dd", groupName = "基本情况",  width = 20,needMerge = true,orderNum = "10")
    private Date occurDt;

    @Excel(name = "投资方式", groupName = "基本情况", width = 20,needMerge = true,orderNum = "11")
    private String inveTypeName;

    @Excel(name = "所属行业",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "12")
    private String industryName;

    @Excel(name = "是否属于民营企业",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "13")
    private String isPrivate;

    @Excel(name = "是否属于科技型企业",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "14")
    private String isTechnology;

    @Excel(name = "是否属于战略新兴产业", groupName = "基本情况", width = 20,needMerge = true,orderNum = "15")
    private String isStrategy;

    @Excel(name = "是否拥有董事席位", groupName = "基本情况", width = 20,needMerge = true,orderNum = "16")
    private String isDirectorCnt;

    @Excel(name = "是否拥有监事席位", groupName = "基本情况", width = 20,needMerge = true,orderNum = "17")
    private String isSupervisorCnt;

    @Excel(name = " 企业投资时投后估值(万元)", groupName = "基本情况", width = 20,needMerge = true,orderNum = "18")
    private String postMoney;

    @Excel(name = " 企业最新投后估值/市值(万元)", groupName = "基本情况", width = 20,needMerge = true,orderNum = "19")
    private String entePostValue;

    @Excel(name = "投资金额最新投后估值/市值(万元)", groupName = "基本情况", width = 20,needMerge = true,orderNum = "20")
    private String inveNewValue;

    @Excel(name = "最新所占股比(%)", groupName = "基本情况", width = 20,needMerge = true,orderNum = "21")
    private String holdShare;

    @Excel(name = "是否上市",groupName = "基本情况", width = 20,needMerge = true,orderNum = "22")
    private String isPublicComp;

    @Excel(name = "企业阶段",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "23")
    private String   phase;

    @Excel(name = "是否省内企业",groupName = "基本情况",  width = 20,needMerge = true,orderNum = "24")
    private String isProvince;
    //子条目集合（这里是实现一对多的关键。name=""是为了不出现表头，如果不为空表头会多一层合并的单元格）
    @ExcelCollection(name = "企业财务情况",orderNum = "25")
    private List<FundEnteExport> listM;

    @Excel(name = "退出时间", exportFormat = "yyyy-MM-dd", groupName = "退出情况", width = 20,needMerge = true,orderNum = "36")
    private Date quitDt;

    @Excel(name = "退出方式", groupName = "退出情况", width = 20,needMerge = true,orderNum = "37")
    private String exitTypeName;

    @Excel(name = "退出本金（万元）", groupName = "退出情况", width = 20,needMerge = true,orderNum = "38")
    private String quitRmbInveAmount;

    @Excel(name = "退出收益（万元）", groupName = "退出情况", width = 20,needMerge = true,orderNum = "39")
    private String quitSy;

    @Excel(name = "亮点介绍", groupName = "是否亮点项目", width = 20,needMerge = true,orderNum = "40")
    private String hlightsRemark;

    private String finaTimes;

    private String regAmountCurrency;

}
