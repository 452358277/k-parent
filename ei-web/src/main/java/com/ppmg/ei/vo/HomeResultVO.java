package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class HomeResultVO extends BaseVO {

        //实缴资本
     private String recCap;

        //已投金额
     private String amount;

     //已投基金数
     private  String countGs;

        //二级基金管理：基金认缴总规模
     private String sumCurrentAmount;

    //二级基金投三级基金总规模
    private String sumTwoCurrentAmount;

    //二级基金投三级基金的数量
     private  String sumFund;

    //二级基金投项目数
     private  String sumProj;

     //母基金规模
     private String sumCurrAmount;

    //投资金额-
     private String sumPay;

    // //母基金管理：已投子基金数合计
     private String  countFund;

     //基金平台立项
     private String  countApp;

    //基金平台过会
    private String   countPolicy;

    //基金平台退出
    private String  countOut;

   //投资金额
    private String sumRmbActualPayAmount;

    //投资项目数
    private String countProj;
    //直投平台 储备
    private String countCb;
    //直投平台 立项
    private String countLx;
    //直投平台 过会 就是决策
    private String countJc;
    //直投平台 退出
    private String countTc;





    //金财合盈-投资管理公司输了合计
    private String hyProj;

    //金财合盈-投资金额
    private String countPayHy;
    //金财合盈-储备
    private String countHyCb;
    //金财合盈-过会
    private String countHyLx;
    //金财合盈-退出
    private String countHyTc;


    private String threeCount;


    private String threeSumpay;

    //省政府二级基金投资金额
    private String sumTwoPay;

    //投资项目总金额
    private String sumCountM;

    //投资项目数
    private String  inveProjCount;

   //带动社会出资总额
    private String  sumQtAm;


}
