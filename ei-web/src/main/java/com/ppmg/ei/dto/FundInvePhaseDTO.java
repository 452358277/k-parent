package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class FundInvePhaseDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

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
