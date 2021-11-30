package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundQuitApplVO extends BaseVO {
    /** null */
    private String appId;

    /** 投资对象 */
    private String objectId;

    /** 申请人 */
    private String appUser;

    /** 申请日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date appDt;

    /** 退出类型（1：部分退出，2：全部退出） */
    private String quitType;

    /** 退出方式 */
    private String quitWay;

    private String quitWayName;

    /** 退出日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date quitDt;

    /** 退出金额 */
    private Double exitAmount;

    /** 退出金额币种 */
    private String currencyExitamount;

    /** 售股对象 */
    private String saleObject;

    /** 总收入 */
    private Double revenue;

    /** 币种 */
    private String currencyRevenue;

    /** 总成本 */
    private Double cost;

    /** 币种 */
    private String currencyCost;

    /** 总盈利 */
    private Double profit;

    /** 币种 */
    private String currencyProfit;

    /** 原占股比 */
    private Double sharesRate;

    /** 卖出股数 */
    private Double sellShares;

    /** 每股价格 */
    private Double price;

    /** 币种 */
    private String currencyPrice;

    /** 每股盈利 */
    private Double earning;

    /** 币种 */
    private String currencyEarning;

    /** 股份公司 */
    private String company;

    /** 持股主体 */
    private String holderId;

    /** 资料清单 */
    private String docList;

    /** 申请原因 */
    private String appReasons;

    /** 备注 */
    private String remark;

    /** 附件 */
    private String attaFile;

    /** 状态（0：草稿，1：正式，9：删除） */
    private String status;

    /** 流程编号 */
    private String processInstId;

    /** 项目编号 */
    private String projId;


    /** 折算为人民币 */
    private Double rmbExitAmount;


    private String QuitTypeName;

    /*申请名称 */
    private String appName;
    /*退出日期*/
    private String quitDy;
    /*退出方式 */
    //private String quitWay;
    /*基金认缴总规模（万元）*/
    private String fundSubscribed;
    /*退基金实缴规模（万元）*/
    private String fundPaidIn;
    /*股比%*/
    private String shareRatio;
    /*收回金额（万元） **/
    private Double backAmount;
    /*退出认缴出资额（万元*/
   // private String exitAmount;
    /*退出部分所占股比%*/
    private String exitShareRatio;
    /*剩余部分所占股比%*/
    private String restShareRatio;
    /*退出理由 */
    private String exitReason;
    /*决策内容*/
    private String decisionDetail;

    //是否分配
    private String isDistribute;
    //分配金额
    private Double distributeMoney;
    //其中省政府投资基金分配额
    private Double distributeGovMoney;
    //分配金额
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date   distributeDt;

    //认缴出资额
    private Double planAmount;
    //实缴出资额
    private Double paidAmount;

    //会议纪要
    private String meetingDetail;

    //退出本金
    private String quitPrincipal;
    //退出收益
    private String quitProfit;

}
