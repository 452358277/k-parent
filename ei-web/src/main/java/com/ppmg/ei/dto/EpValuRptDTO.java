package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EpValuRptDTO extends BaseDTO {
    /** 主键 */
    private String pkId;

    /** 投后主键（投后的HANDLE_ID） */
    private String iaId;

    /** 报告人 */
    private String rptBy;

    /** 报告日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date rptDt;

    /** 报告标题 */
    private String rptTitle;

    /** 报告总结 */
    private String rptDesc;

    /** 分析报告附件 */
    private String rptFile;

    /** 状态（0：草稿，1：正常，9：删除） */
    private String status;

    /** null */
    private String projGuid;

    /** 任务ID */
    private String taskId;

    /** 任务名称 */
    private String taskName;

    /** 出资主体ID */
    private String afterFunderId;

    /** 企业ID */
    private String objectId;

    /** 企业名称 */
    private String projObjectName;

    /** 投后主键 */
    private String handleId;
}
