package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ppmg.ei.model.SurverCooorgModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class DueDiligenceRptDTO {
    /** null */
    private String rptId;

    /** 载体(基金号、项目号) */
    private String carrier;

    /** 载体类别(1：融资，2：投资) */
    private String carrierType;

    /** 开始时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date beginDt;

    /** 结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date endDt;

    /** 合作方机构 */
    private String partnerOrga;

    /** 合作方类型 */
    private String orgaType;

    /** 关键字 */
    private String keyword;

    /** 附件 */
    private String rptFile;

    /** 状态(‘0’为正常 ‘1’为已删除) */
    private String status;

    /** 报告日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date rptDate;

    /** 报告类型【1:财务、2:法律、3:综合、4:其他】 */
    private String rptType;

    /** 流程实例号 */
    private String processInstId;

    /** 审批状态 */
    private String processStatus;

    /** 合作方机构ID */
    private String partnerOrgaId;

    /** 尽调参与人员 */
    private String staff;

    /** 附件说明 */
    private String rptDetail;

    /** 尽职调查计划 */
    private String surveyPlan;

    /** 尽职调查计划ID */
    private String surveyPlanId;

    private String commentFile;

    private String  isMeeting;

    private Boolean isStartFlow;

    private String commentResult;

    private String projId;

    private List<SurverCooorgModel> list;
}
