package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProgressCommentDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 评论ID-主键 */
	private String commentId;

	/** 项目进展ID */
	private String progressId;

	/** 评论时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date commentDt;

	/** 评论内容HTML */
	private String commentHtml;

	/** 状态（0：正常9：删除） */
	private String status;

	/** 评论来源，0:PC;1:手机 */
	private String commentSource;

	/** 出资主体NAME */
	private String inveName;

	/** 被投对象NAME */
	private String projectObjectName;

	/** 所有被@的人ID*/
	private String allUserIds;

	/** 所有被@的人NAME*/
	private String allUserNames;

	private String dealMark;

	private String dist;

	private String projStatus;

	public String getDealMark() {
		return dealMark;
	}

	public void setDealMark(String dealMark) {
		this.dealMark = dealMark;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public void setAllUserNames(String allUserNames) {
		this.allUserNames = allUserNames;
	}

	public String getAllUserIds() {
		return allUserIds;
	}

	public void setAllUserIds(String allUserIds) {
		this.allUserIds = allUserIds;
	}

	public String getAllUserNames() {
		return allUserNames;
	}


	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getProjectObjectName() {
		return projectObjectName;
	}

	public void setProjectObjectName(String projectObjectName) {
		this.projectObjectName = projectObjectName;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public Date getCommentDt() {
		return commentDt;
	}

	public void setCommentDt(Date commentDt) {
		this.commentDt = commentDt;
	}

	public String getCommentHtml() {
		return commentHtml;
	}

	public void setCommentHtml(String commentHtml) {
		this.commentHtml = commentHtml;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommentSource() {
		return commentSource;
	}

	public void setCommentSource(String commentSource) {
		this.commentSource = commentSource;
	}

}