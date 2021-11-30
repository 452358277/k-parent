package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ProjectProgressModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 进展主键 */
	private String progressId;

	/** 出资主体ID */
	private String inveId;

	/** 出资主体NAME */
	private String inveName;

	/** 被投对象ID */
	private String projectObject;

	/** 被投对象NAME */
	private String projectObjectName;

	/** 所属平台 */
	private String groupId;

	/** 进展内容html */
	private String progressHtml;

	/** 进展内容text */
	private String progressText;

	/** 进展发布时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date progressDate;

	/** 进展状态（0：正常9：删除） */
	private String status;

	/** 进展来源（0:员工填写;1:系统推送--只要员工填写的才能评论回复） */
	private String progressResource;

	/** 进展附件 */
	private String progressFile;

	/** PROJECT_ID */
	private String projectId;

	/** 所属平台ID*/
	private String field2;

	/** field_3 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date field3;

	/** field_4 */
	private Long field4;

	/** field_5 */
	private Long field5;

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getProjectObject() {
		return projectObject;
	}

	public void setProjectObject(String projectObject) {
		this.projectObject = projectObject;
	}

	public String getProjectObjectName() {
		return projectObjectName;
	}

	public void setProjectObjectName(String projectObjectName) {
		this.projectObjectName = projectObjectName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getProgressHtml() {
		return progressHtml;
	}

	public void setProgressHtml(String progressHtml) {
		this.progressHtml = progressHtml;
	}

	public String getProgressText() {
		return progressText;
	}

	public void setProgressText(String progressText) {
		this.progressText = progressText;
	}

	public Date getProgressDate() {
		return progressDate;
	}

	public void setProgressDate(Date progressDate) {
		this.progressDate = progressDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgressResource() {
		return progressResource;
	}

	public void setProgressResource(String progressResource) {
		this.progressResource = progressResource;
	}

	public String getProgressFile() {
		return progressFile;
	}

	public void setProgressFile(String progressFile) {
		this.progressFile = progressFile;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Date getField3() {
		return field3;
	}

	public void setField3(Date field3) {
		this.field3 = field3;
	}

	public Long getField4() {
		return field4;
	}

	public void setField4(Long field4) {
		this.field4 = field4;
	}

	public Long getField5() {
		return field5;
	}

	public void setField5(Long field5) {
		this.field5 = field5;
	}

}