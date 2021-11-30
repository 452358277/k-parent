package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SpvResulListVO extends BaseVO {
    //基金名称
    private String regName;


    private String projId;
//是否spv
    private String fundSrc;
//基金规模
    private String currentAmount;
//本基金认缴金额
    private String bjjAmount;
//其他认缴金额
    private String  qtAmount;
    //基金实缴规模
    private String raiseAmount;
//拨付金额
    private Double sumActualPayAmount;
    //投资时间
    private String tzTime;

    private String fundName;

    private String js;

    private String creditCode;


    private String isDistribute;


    private Double distributeMoney;

    private Double distributeGovMoney;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date distributeDt;

    private String isFit;

    private String isExcept;

    private String enteId;

    private String fundId;

    private String maxActualPayDate;


}
