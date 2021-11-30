package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProgressCommentModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjectProgressVO extends BaseVO {

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

	/** 进展附件 */
	private List<ProgressCommentVO> ProgressCommentList;

	/** 创建人ID */
	protected String createBy;

	/** 创建人姓名 */
	private String createName;

	/** 头像路径 */
	private String filePath;

	/** PROJECT_ID */
	private String projectId;

	/** PROJECT_ID */
	private Integer commentCount;

	/** thatLoginUserId */
	private String thatLoginUserId;

	/** 项目进展List */
	private List fileLists;

	public List getFileLists() {
		return fileLists;
	}

	public void setFileLists(List fileLists) {
		this.fileLists = fileLists;
	}

	public String getThatLoginUserId() {
		return thatLoginUserId;
	}

	public void setThatLoginUserId(String thatLoginUserId) {
		this.thatLoginUserId = thatLoginUserId;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/** 0:可以编辑删除；1：不可以编辑删除 */
	private String isEditDelete;

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

	public List<ProgressCommentVO> getProgressCommentList() {
		return ProgressCommentList;
	}

	public void setProgressCommentList(List<ProgressCommentVO> progressCommentList) {
		ProgressCommentList = progressCommentList;
	}



}