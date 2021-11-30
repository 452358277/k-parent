package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EnteAuditInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 企业编号 */
	private String enterpriseId;

	private String enterpriseName;

	/** 日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date auditDate;

	/** 审计报告附件 */
	private String auditReport;

	/** 备注说明 */
	private String remark;

	/** 状态 */
	private String status;

	/** 企业外网信息确认 */
	private String confirm;


	private String chName;



}