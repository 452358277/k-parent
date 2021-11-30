package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundInfoExportVO extends BaseVO {

    private String fundId;

    //参股子基金名称
    private String regName;
    //基金管理人/合作方名称
    private String mcName;
    //基金设立日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    //合作期限
    private String cooperationYear;

     //投资期
    private String invePeriod;
    //起算日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveStartDate;

    //截止日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveEndDate;

    //基金认缴总额（万元）
    private Double currentAmount;
     //基金实缴总额（万元）
    private Double raiseAmount;
   //金财公司认缴总额（万元）
    private Double jcCurrentAmount;
    private Double jcCurrentAmountOne;

    //金财公司实缴总额（万元）
    private Double  jcRaiseAmount;

    private Double  jcRaiseAmountOne;

    //投资项目企业数量（家）
    private String projGs;

    private String enteGs;

    private String  projStatusName;

    private String  projStatus;

    //投资项目企业金额（万元）
    private String projAmount;

    private String fundGs;

    //投资省内企业数量（家）
    private String provinceGs;
    //投资省内企业金额（万元）
    private Double provinceAmount;
    //已上市数量（家）
    private String ssGs;
    //已申报IPO数量（家）
    private String ipoGs;
   //已挂牌新三板数量（家）
    private String newBoardGs;

    private String oldSubPlatProp;

    private String inveName;

    private String projId;

}
