package com.ppmg.ei.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;

public class ScParamListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String paramId;

	/** 客户名称 */
	private String cusName;

	/** 客户类型(1自然人2企业3政府平台) */
	private String cusType;

	/** 客户当前评级 */
	private String cusLevel;

	/** 认定时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date confirmDt;

	/** 1：草稿；2：审批中；3：审批同意；4：已否决；9：删除 */
	private String status;

	/** 1：草稿；2：审批中；3：审批同意；4：已否决；9：删除 */
	private String statusName;

	/** 流程实例号 */
	private String processInstId;

	private FixflowRunTaskinstanceModel instanceModel;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusLevel() {
		return cusLevel;
	}

	public void setCusLevel(String cusLevel) {
		this.cusLevel = cusLevel;
	}

	public Date getConfirmDt() {
		return confirmDt;
	}

	public void setConfirmDt(Date confirmDt) {
		this.confirmDt = confirmDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}


}