package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class SpvResult extends BaseModel {
    private String regName;

    private String projId;

    private String fundSrc;

    private String currentAmount;

    private String bjjAmount;

    private String qtAmount;

    private String raiseAmount;

    private String fundId;

    private String fundName;

    private String projObject;

    private String js;

    private String creditCode;

    private String isDistribute;


    private Double distributeMoney;

    private Double distributeGovMoney;


    private Double sumActualPayAmount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date distributeDt;

    private String isFit;

    private String isExcept;

    private String enteId;

    private String maxActualPayDate;

}
