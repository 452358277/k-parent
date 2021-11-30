package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class FundReportDTO extends BaseDTO {

    /** 主键 */
    private String reportId;

    /** 基金id */
    private String fundId;

    /**年份 */
    private String year;

    /** 季度 */
    private String quarter;

    /** 附件说明 */
    private String attaDetail;

    /** 附件 */
    private String attaFile;

    /** 状态 */
    private String status;

    private String type;

}
