package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjectProgressDTO extends BaseDTO {
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

    /** 系统发送类型*/
    private String field2;

    /** 所有被@的人ID*/
    private String allUserIds;

    /** 所有被@的人NAME*/
    private String allUserNames;

    /** 通过dealmark判断是否可编辑，update,view*/
    private String dealMark;

    /** dist,是否是我的项目*/
    private String dist;

    /** 项目状态*/
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

    public String getAllUserIds() {
        return allUserIds;
    }

    public void setAllUserIds(String allUserIds) {
        this.allUserIds = allUserIds;
    }

    public String getAllUserNames() {
        return allUserNames;
    }

    public void setAllUserNames(String allUserNames) {
        this.allUserNames = allUserNames;
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
}
