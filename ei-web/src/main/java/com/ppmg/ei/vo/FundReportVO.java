package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class FundReportVO  extends BaseVO {

    private static final long serialVersionUID = 1L;

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

    private String quarterName;

    private String type;

    private String typeName;
}
