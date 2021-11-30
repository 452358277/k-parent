package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProjQuitApplModelDTO extends BaseDTO {

    /** null */
    private String appId;

    /** 投后项目主键 */
    private String iaId;

    /** 申请人 */
    private String appUser;

    /** 申请日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date appDt;

    /** 退出类型（1：全部退出 ，2：部分退出） */
    private String quitType;

    /** 退出方式 */
    private String quitWay;

    /** 退出日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date quitDt;

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

    /** 状态 */
    private String status;

    /** 备注 */
    private String remark;

    /** 附件 */
    private String attaFile;

    /** 流程实例 */
    private String processInstId;

    /** null */
    private String rowId;

    /** null */
    private String processversioninstanceguid;

    /** 折算为人民币 */
    private Double rmbCost;

    /** 出资主体ID */
    private String afterFunderId;

    /** 企业ID */
    private String objectId;

    /** 企业名称 */
    private String projObjectName;

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


}
