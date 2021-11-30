package com.ppmg.ei.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.bean.BaseSearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value="平台信息列表查询对象searchBean",description="平台信息列表查询对象searchBean")
public class ProjectProgressSearchBean extends BaseSearchBean{

    private static final long serialVersionUID = 1L;

    /** 进展ID */
    @ApiModelProperty(name="progressId", value="进展ID", example="10766", dataType="String", required=false)
    private String progressId;

    /** 关键字 */
    @ApiModelProperty(name="keyword", value="关键字", example="项目名称", dataType="String", required=false)
    private String keyword;

    /** 发布人 */
    @ApiModelProperty(name="createBy", value="发布人", example="1200119390", dataType="String", required=false)
    private String createBy;

    /** 发布时间开始 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(name="startTime", value="成立时间开始", example="2018-10-01", dataType="Date", required=false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 发布时间结束 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(name="endTime", value="成立时间结束", example="2018-12-01", dataType="Date", required=false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 进展来源（0:员工填写;1:系统推送--只要员工填写的才能评论回复） */
    @ApiModelProperty(name="progressResource", value="进展来源", example="1", dataType="String", required=false)
    private String progressResource;

    /** 出资主体ID */
    @ApiModelProperty(name="inveId", value="出资主体ID", example="10648", dataType="String", required=false)
    private String inveId;

    /** 被投对象ID */
    @ApiModelProperty(name="projectObject", value="被投对象ID", example="29684", dataType="String", required=false)
    private String projectObject;

    /** 所属平台 */
    @ApiModelProperty(name="groupId", value="所属平台", example="15", dataType="String", required=false)
    private String groupId;

    public String getProgressId() {
        return progressId;
    }

    public void setProgressId(String progressId) {
        this.progressId = progressId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProgressResource() {
        return progressResource;
    }

    public void setProgressResource(String progressResource) {
        this.progressResource = progressResource;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    public String getProjectObject() {
        return projectObject;
    }

    public void setProjectObject(String projectObject) {
        this.projectObject = projectObject;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
