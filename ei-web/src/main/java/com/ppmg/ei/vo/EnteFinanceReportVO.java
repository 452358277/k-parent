package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EnteFinanceReportVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String stopId;

    /** 企业编号 */
    private String enterpriseId;

    /** 融资时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date financeDate;

    /** 融资金额(万元) */
    private Double financeAmt1;

    /** 基金本轮投资金额(万元) */
    private Double fundInvestAmt;

    /** 基金最新占比 */
    private Double fundRatio;

    /** 最新投后估值(万元) */
    private Double lastPostValue;

    /** 融资轮数(码值：213) */
    private String finaRounds;

    /** 融资次数(码值：214) */
    private String finaTimes;

    /** 最新注册资本(万元) */
    private Double projType;

    /** 融资方式(码值：1029) */
    private String stopType;

    /** 删除标记 */
    private String isDelete;

    /** 排序 */
    private Long sortOrder;

    /** 版本号 */
    private Long rowVersion;


    private String finaRoundsName;

    private String finaTimesName;

    private String stopTypeName;

}
