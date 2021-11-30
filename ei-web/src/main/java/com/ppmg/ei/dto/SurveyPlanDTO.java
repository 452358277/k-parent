package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.model.SurveyPlanUsersModel;
import lombok.Data;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Data
public class SurveyPlanDTO extends BaseDTO {

    /** 计划ID */
    private String planId;

    /** 载体(基金号、项目号) */
    private String carrier;

    /** 载体名称 */
    private String carrierName;

    /** 载体类别(1：融资，2：投资，3：企业投后) */
    private String carrierType;

    /** 计划名称 */
    private String planName;

    /** 尽调范围 */
    private String surveyContent;

    /** 拟聘机构 */
    private String impOrg;

    /** 尽调费用 */
    private Double surveyFee;

    /** 计划开始时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    /** 计划结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;

    /** 流程ID */
    private String processInstId;

    /** 状态 */
    private String status;

    /** 秘书提交审批会议结果 */
    private String meetingFile;

    /** 附件说明 */
    private String attaDetail;

    /** 附件 */
    private String attaFile;

    /** 尽调机构ID */
    private String partnerOrgaId;

    private String interior;

    private String userIds;

    private Boolean isStartFlow;

    private  List<SurverCooorgModel> list;


    private  List<SurveyPlanUsersModel> ids;

    private String projId;


}
