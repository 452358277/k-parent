package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProgressCommentVO extends BaseVO {

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

	protected String createBy;

	protected String createName;

	@DateTimeFormat(pattern="yyyy-MM-dd  HH:mm:ss")
	@JSONField(format="yyyy-MM-dd  HH:mm:ss")
	protected Date createDt;

	/** 头像路径 */
	private String filePath;

	/** 0:可以编辑删除；1：不可以编辑删除 */
	private String isEditDelete;

	/** thatLoginUserId */
	private String thatLoginUserId;

	public String getThatLoginUserId() {
		return thatLoginUserId;
	}

	public void setThatLoginUserId(String thatLoginUserId) {
		this.thatLoginUserId = thatLoginUserId;
	}

	public String getIsEditDelete() {
		return isEditDelete;
	}

	public void setIsEditDelete(String isEditDelete) {
		this.isEditDelete = isEditDelete;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}



	private Integer pageSize = 10;

	private Integer currPage = 1;

	private String sort;

	private String order;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}