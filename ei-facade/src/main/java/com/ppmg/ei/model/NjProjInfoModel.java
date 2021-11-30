package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NjProjInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String projId;

	/** 项目编号 */
	private String projNo;

	/** 项目名称 */
	private String projName;

	/** 项目类型（1：投企业，2：投基金，3:子基金项目） */
	private String projType;

	/** 项目状态（1：储备项目，2：已入库，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatus;

	/** 被投对象 */
	private String projObject;

	/** 被投对象名称 */
	private String projObjectName;

	/** 所投企业当前所属阶段 */
	private String entePhase;

	/** 项目来源 */
	private String projSrc;

	/** 来源说明 */
	private String srcDesc;

	/** 出资主体编号 */
	private String inveId;

	/** 出资主体名称 */
	private String inveName;

	/** 出资主体类型（1：基金，2：直投部门） */
	private String inveType;

	/** 分管合伙人 */
	private String chargePartner;

	/** 保密等级 */
	private String secrecyLevel;

	/** 附件 */
	private String inveFile;

	/** 项目立项时间 */
	private Date setupDt;

	/** 投资决策时间 */
	private Date decisionDt;

	/** 项目所属公司ID */
	private Long groupId;

	/** 流程实例号 */
	private String processInstId;

	/** null */
	private String projGuid;

	/** 是否为投平台项目（0：否，1：是） */
	private String isPlat;

	/** 立项暂缓（1：表示暂缓:空表示无暂缓） */
	private String pauseApply;

	/** 决策暂缓（1：表示暂缓:空表示无暂缓） */
	private String pauseDecision;

	/** 项目概况 */
	private String projOverview;

	/** 投资经理对项目评价 */
	private String inveEvaluation;

	/** 备注 */
	private String remark;

	/** 附件说明 */
	private String fileDesc;

	/** 决策事项/董监事安排 */
	private String majorMatter;

	/** 项目概况附件 */
	private String projOverviewAtt;

	/** 投资经理对项目评价附件 */
	private String inveEvaluationAtt;

	/** 备注附件 */
	private String remarkAtt;

	/** 决策事项/董监事安排附件 */
	private String majorMatterAtt;

	/** 质量评估评级结果 */
	private Long scoreResult;

	/** 秘书提交审批会议结果(立项) */
	private String applyMeetingFile;

	/** 秘书提交审批会议结果(决策) */
	private String decisionMeetingFile;

	/** 申请母基金额度 */
	private Double fundLimit;

	/** 空：内网录入，guest：外网录入 */
	private String dataSrc;

	private String industry;

	private String controller;

	private String controllerTel;

	private String regAdd;

}