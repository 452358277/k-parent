package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class FundInfoUpDTO extends BaseDTO {

    private Date actualPayDate;

    private Date decisionDate;

    private Double inveCurrentAmount;

    private Date decisionDt;

    private String fundId;

    private String fundStruct;

    private String fundSts;

    private String  inveCurrent;

    private String inveId;

    private Double inveRaiseAmount;

    private String isCorr;

    private String isDecisionPass;

    private String  isJs;

    private String isRecord;

    private Double planAmount;

    private String projId;

    private String projStatus;

    private Date quitDt;

    private String  quitType;

    private Double  raiseAmount;

    private String  recordCode;

    private String revenue;

    private String spvType;

    private Double sumInveAmount;

    private Double amount;

    private Double actualPayAmount;

    private String appId;

    private String pqId;

    /** 基金认缴规模 */
    private Double currentAmount;

    private String fundName;

    //三级基金
    private String inveName;

    private String isSpv;

}
