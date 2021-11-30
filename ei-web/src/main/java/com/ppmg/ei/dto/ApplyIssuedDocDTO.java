package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ApplyIssuedDocDTO extends BaseDTO {

    /** 申请表单号 */
    private String applyNo;

    /** 文档编号 */
    private String documentNo;

    /** 发文状态:发文拟稿，部门核稿，办公室审核，部门会签，领导签发，停止签发，已签发，退回修改 */
    private String issuedStatus;

    /** 发文标题 */
    private String issuedTitle;

    /** 发文主题词 */
    private String issuedKeywords;

    /** 发文密级 */
    private String issuedLevel;

    /** 发文紧急程度 */
    private String issuedUrgency;

    /** 主要内容 */
    private String content;

    /** 附件 */
    private String attachment;

    /** 油印份数 */
    private Long printCopies;

    /** 备注 */
    private String remark;

    /** 分管领导 */
    private Long leaderId;

    /** 分管领导名称 */
    private String leaderName;

    /** 主送 */
    private String destinationMs;

    /** 抄送 */
    private String destinationCc;

    /** 消息通知人员ID */
    private String destinationMsg;

    /** 消息通知人员名称 */
    private String destinationMsgName;

    /** 是否党委发文(1:是) */
    private String isDangweiSend;

    /** 是否纪委发文(1:是) */
    private String isJiweiSend;

    /** 印发数量 */
    private Long printAmount;

    /** 会办部门 */
    private String meetingDepart;

    /** 发文日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date issueDocumentTime;

    /** 发文文号 */
    private String issueDocumentNo;

    /** 紧急程度（码值:11007） */
    private String urgencyDegree;

    /** 码值:11008 发文类型:请示、报告、批复、通知、函、其它 */
    private String issueDocumentType;

    /** 码值: 11009 发文类别: 内部发文、外部发文 */
    private String issueDocumentClass;

    /** 会办部门ID */
    private String meetingDepartId;

    /** 非空，申请人ID,当前登录者ID */
    private long applicantId;

    /** 申请人姓名 */
    private String applicantName;

    /** 申请人所属公司 */
    private String applicantCompany;

    /** 申请人所属部门 */
    private String applicantDepartment;

    /** 申请日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date applyTime;

    /** 申请状态，非空，0草稿状态，1流程:未完成处理（审批中），2：未完成：退回修改,3:已完成-申请失败，4：已完成-申请成功 */
    private String applyStatus;

    /** 申请类型代码，非空 */
    private String applyType;

    /** 申请描述/备注 */
    private String applyDesc;

    /** 申请原因 */
    private String applyReason;

    /** 所在公司ID */
    private Long applicantCompanyId;

    /** 所在部门ID */
    private Long applicantDepartmentId;

    /** 申请名称/申请标题 */
    private String applyName;

    /** 流程状态 */
    private String processStatus;

    /** 流程ID */
    private String processInstId;


    /** 审批完成时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date processEndTime;

    /** 总金额 */
    private Double totalAmount;

    /**
     *
     * 项目经理
     * **/
    private String projUser;


    private String  assignId;


    private String destinationMsName;

    private String destinationCcName;

}
