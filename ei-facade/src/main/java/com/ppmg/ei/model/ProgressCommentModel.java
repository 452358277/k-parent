package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ProgressCommentModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 评论ID-主键 */
	private String commentId;

	/** 项目进展ID */
	private String progressId;

	/** 评论时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd  HH:mm:ss")
	@JSONField(format="yyyy-MM-dd  HH:mm:ss")
	private Date commentDt;

	/** 评论内容HTML */
	private String commentHtml;

	/** 状态（0：正常9：删除） */
	private String status;

	/** 评论来源，0:PC;1:手机 */
	private String commentSource;

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