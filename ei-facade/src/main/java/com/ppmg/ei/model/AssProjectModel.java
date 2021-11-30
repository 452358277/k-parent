package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class AssProjectModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 项目ID */
	private String projId;

	/** 项目名称 */
	private String projName;

	/** 开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date projStartTime;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date projEndTime;

	/** 项目类型(码表7503) */
	private String projType;

	/** 项目状态 */
	private String projStatus;

	/** 附件 */
	private String projAtta;

	/** null */
	private String isDelete;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Date getProjStartTime() {
		return projStartTime;
	}

	public void setProjStartTime(Date projStartTime) {
		this.projStartTime = projStartTime;
	}

	public Date getProjEndTime() {
		return projEndTime;
	}

	public void setProjEndTime(Date projEndTime) {
		this.projEndTime = projEndTime;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getProjAtta() {
		return projAtta;
	}

	public void setProjAtta(String projAtta) {
		this.projAtta = projAtta;
	}

}