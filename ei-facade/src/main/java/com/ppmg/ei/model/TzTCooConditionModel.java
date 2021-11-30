package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class TzTCooConditionModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 合作条件ID */
	private String conditionId;

	/** 母基金名称 */
	private String fundName;

	/** 母基金规模 */
	private Double fundSize;

	/** 管理部门 */
	private String manDep;

	/** 登记人 */
	private String registrant;

	/** 登记日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date regDate;

	/** 概要说明 */
	private String remark;

	/** 基金管理办法附件 */
	private String methodFile;

	/** 合作条件附件 */
	private String conditionFile;

	/** 载体(基金号、项目号) */
	private String carrier;

	/** 载体名称 */
	private String carrierName;

	/** 载体类别(1：融资，2：投资，3：企业投后) */
	private String carrierType;

	/** 流程ID */
	private String processInstId;

	/** 状态 */
	private String status;

	/** 秘书提交审批会议结果 */
	private String meetingFile;

	/** 母基金ID */
	private String fundId;

	/** 拟征集规模 */
	private Double planSize;

	/** 拟征集数量（家） */
	private Long planNum;

	/** 征集开始日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 征集截止日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 特殊约定 */
	private String specialFile;

}