package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-10-22 11:46 
 */ 
@Data 
public class ProjStopModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String stopId;

	/** 关联id */
	private String projId;

	/** 是否需要法务审核（0：需要，1：不需要） */
	private String stopLawWorks;

	/** 是否重大事项（0：是，1：否） */
	private String stopGreat;

	/** 终止日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date stopDate;

	/** 终止原因 */
	private String stopCause;

	/** 附件 */
	private String stopFile;

	/** 状态 */
	private String status;

	/** 项目类型 */
	private String projType;

	/** 终止类型（0：终止，1：注销） */
	private String stopType;
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	//流程实例id
	private String processInstId;

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getStopLawWorks() {
		return stopLawWorks;
	}

	public void setStopLawWorks(String stopLawWorks) {
		this.stopLawWorks = stopLawWorks;
	}

	public String getStopGreat() {
		return stopGreat;
	}

	public void setStopGreat(String stopGreat) {
		this.stopGreat = stopGreat;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getStopCause() {
		return stopCause;
	}

	public void setStopCause(String stopCause) {
		this.stopCause = stopCause;
	}

	public String getStopFile() {
		return stopFile;
	}

	public void setStopFile(String stopFile) {
		this.stopFile = stopFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getStopType() {
		return stopType;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}
}