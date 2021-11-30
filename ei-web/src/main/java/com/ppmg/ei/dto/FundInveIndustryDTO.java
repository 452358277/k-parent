package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class FundInveIndustryDTO extends BaseDTO {


    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String pkId;

    /** 基金ID */
    private String fundId;

    /** 行业名称 */
    private String industryName;

    /** 投资行业 */
    private String inveIndustry;

    /** 行业比重（%） */
    private Double industryRatio;

}
