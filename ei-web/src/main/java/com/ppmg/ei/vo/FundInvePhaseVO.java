package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class FundInvePhaseVO extends BaseVO {

    /** 主键ID */
    private String pkId;

    /** 基金ID */
    private String fundId;

    /** 早期（%） */
    private Double phaseA;

    /** 成长期（%） */
    private Double phaseB;

    /** 成熟期（%） */
    private Double phaseC;
}
