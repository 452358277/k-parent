package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class LedgeMagExportModel extends BaseModel {

    private String regName;

    private Double currentAmount;

    private Double rmbCurrentAmount;

    private Double raiseAmount;

    private Double rmbRaiseAmount;

    //金财投资认缴总额
    private String jcRmbInveAmount;

    private String jcRmbInveAmountoOne;


    private Double jcInveAmount;

    private String sumDisJcAmount;

    //基金回收金额
    private String sumBackAmount;


  //   <!--金财投资实缴总额 -->
    private String jcRmbRaiseAmount;

    //基金已投资金额
    private String sumRaiseAmount;

    private String  inveName;
    //基金账户余额
    private String newAccountBalance;
    //剩余未回收实缴出资额
    private String surplusAmount;

    //实缴出资回收率
    private String percent;
 //利润
    private String profitAmount;


    private String oldSubPlatProp;

    private String projId;


    private String sumDisJcAmountStr;

    //基金实缴总额明细
    private List<InveExportInfo> listInveInfo;


    private List<FundAccountDetailModel> listFundAccountDetail;



}
