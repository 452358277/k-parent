package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundShareDTO extends BaseDTO {

    /** 主键 */
    private String pkId;

    /** 基金编号 */
    private String fundId;

    /** 投资人ID */
    private String investorId;

    /** 投资角色(1:LP,2:GP,3:GM) */
    private String inveRole;

    /** 认缴规模 */
    private Double inveAmount;

    /** 认缴金额币种 */
    private String inveCurrency;

    /** 累计缴款额 */
    private Double totalFinancial;

    /** 累计出资币种 */
    private String totalFinCurrency;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 企业ID */
    private String enteId;

    /** 出资来源（码值1000：1自有资金、2理财通道、3借贷资金） */
    private String capitalSource;

    /** 出资人性质（码值1001：1政府部门、2国有企业、3混合制企业、4民营企业、5个人） */
    private String inveType;

    /** 实缴规模 */
    private Double raiseAmount;

    /** 主键 */
    private String itemId;


    /** 第几期 */
    private Long closeRound;

    /** 本期应缴额 */
    private Double duesAmount;

    /** 本期实缴额 */
    private Double paymentAmount;

    /** 实到日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date arriveDate;

    private String descFile;

    private Boolean isStartFlow;

    private String remark;

    private String shareFile;

    private  Integer totalCloseRound;

    private String processInstId;

    private String projId;
}
