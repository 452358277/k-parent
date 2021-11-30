package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundEnteModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProjInfoExportVO extends BaseVO {
    //出资主体
    private String inveName;

    private String inveId;

    private String projName;

    private String projId;

    private String projObject;

    //公司名称
    private String enteName;

   //公司成立时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date setupDt;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    private String addressDetails;

    //注册地
    private String regAdd;

    //注册资本
    private String registCapi;

    //

    private Double intendedAmount;

    //实际投资额
    private Double rmbIntendedAmount;
    // 占股比例
    private Double perShare;

    //占基金总认缴比例
    private String percent;
  //属于企业第几轮
    private String inveRounds;

    //出资时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date occurDt;

    private String inveType;

    //所属行业
    private String industryName;

    private String provinceAmount;

    private String isPrivate;

    private String isTechnology;

    private String isStrategy;

    private String isDirectorCnt;

    private String isSupervisorCnt;

    private String postMoney;

    private String entePostValue;

    private String inveNewValue;

    private String isPublicComp;

    private String isProvince;


    private String   phase;
    //亮点介绍
    private String hlightsRemark;

   //退出本金
    private String quitRmbInveAmount;
    //退出收益
    private String quitSy;
    //退出时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date quitDt;

    private String exitType;

    //投资方式
    private String inveTypeName;

    //是否有董事席位
    private String  isDirectorCnName;

    //是否有监事席位
    private String isSupervisorCntName;

   //是否上市
    private String isPublicCompName;

   //企业阶段
    private String  phaseName;

   //是否省内
    private String   isProvinceName;

    //退出方式
    private String exitTypeName;

    //
    private List<FundEnteModel> listM;


    private Double regAmount;


    private  String regAmountCurrency;

    private String finaRounds;

    private String finaTimes;

    private String holdShare;


    private String lastYear;

}
