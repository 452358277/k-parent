package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class projValuationDTO  extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String pkId;

    /** 投后主键（投后的HANDLE_ID） */
    private String iaId;

    /** 投资项目投资评估外键 */
    private String praId;

    /** 评估对象，被投对象（企业） */
    private String objectId;

    /** 评估人 */
    private String assessBy;

    /** 评估日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date assessDt;

    /** 当前公司总估值 */
    private Double totalValue;

    /** 总估值币种 */
    private String totalCurr;

    /** 评估对象当前阶段 */
    private String currPhase;

    /** 当前所占股比（%） */
    private Double holdShare;

    /** 估值方式 */
    private String valuType;

    /** 投资估值（持有股份部分估值） */
    private Double inveValuation;

    /** 投资估值币种 */
    private String ivCurrency;

    /** 已实现投资收益 */
    private Double realizedReturn;

    /** 已实现投资收益币种 */
    private String realizedCurr;

    /** 未实现投资收益 */
    private Double unrealizedReturn;

    /** 未实现投资收益币种 */
    private String unrealizedCurr;

    /** 估值依据说明 */
    private String valuDesc;

    /** 估值时间点 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date valuDt;

    /** IRR(%) */
    private Double irr;

    /** 评估报告 */
    private String attaFile;

    /** 关联任务号 */
    private String taskId;

    /** 评估类型（关键字） */
    private String assessType;

    /** 评估总结 */
    private String assessSummary;

    /** 备注 */
    private String remark;

    /** 状态（0：草稿，1：正常，9：删除） */
    private String status;

    /** 投资回报倍数 */
    private Double returnRatio;

    /** null */
    private String rowId;

    /** 整体估值 */
    private Double wholeValue;

    private String afterFunderId;

    /** 投后估值 */
    private Double entePostValue;

    /** 投资金额投后估值*/
    private Double inveNewValue;


}
