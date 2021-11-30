package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjStopVO extends BaseVO {
    /** 主键 */
    private String stopId;

    /** 关联id */
    private String projId;

    /** 是否需要法务审核（1：需要，0：不需要） */
    private String stopLawWorks;
    private String stopLawWorksName;

    /** 是否重大事项（1：是，0：否） */
    private String stopGreat;
    private String stopGreatName;

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
    private String statusName;

    /** 项目类型 */
    private String projType;

    /** 终止类型（0：终止，1：注销） */
    private String stopType;
    private String stopTypeName;

    //項目名稱
    private String projName;
    //出資主體
    private String inveName;
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

    public String getStopTypeName() {
        return stopTypeName;
    }

    public void setStopTypeName(String stopTypeName) {
        this.stopTypeName = stopTypeName;
    }

    public String getStopLawWorksName() {
        return stopLawWorksName;
    }

    public void setStopLawWorksName(String stopLawWorksName) {
        this.stopLawWorksName = stopLawWorksName;
    }

    public String getStopGreatName() {
        return stopGreatName;
    }

    public void setStopGreatName(String stopGreatName) {
        this.stopGreatName = stopGreatName;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
