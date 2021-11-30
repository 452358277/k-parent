package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundAccountDetaiExport;
import com.ppmg.ei.model.InveExportInfo;
import lombok.Data;

import java.util.List;

@Data
public class LedgeMagExportVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    private String regName;

    private Double currentAmount;

    private Double rmbCurrentAmount;

    private Double raiseAmount;

    private Double rmbRaiseAmount;

    //金财投资认缴总额
    private String jcRmbInveAmount;

    private Double jcInveAmount;

    private String sumDisJcAmount;

    //基金回收金额
    private String sumBackAmount;

    //金财投资实缴总额
    private String jcRmbRaiseAmount;

    //金财已投金额
    private String sumJcRaiseAmount;
    //账户余额
    private String newAccountBalance;
    //剩余回收实缴出资额
    private String surplusAmount;

    private String percent;

    private String profitAmount;

    private String sumRaiseAmount;

    private String oldSubPlatProp;

    private String inveName;

    //基金实缴总额明细
    private List<InveExportInfo> listInfo;

//
    private List<FundAccountDetaiExport> listFundAccountDetail;

    private String projId;

    private String sumDisJcAmountStr;

}
