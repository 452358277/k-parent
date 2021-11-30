package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.RegulationApproveCommModel;
import lombok.Data;

import java.util.List;


public class RegulationApproveVO extends BaseVO {


    /** 主键 */
    private String iraId;

    /** 企业注册状态(码值：249) */
    private String entStatus;

    /** 被投企业ID */
    private String entId;

    /** 被投企业名称 */
    private String entName;

    /** 企业类型(码值：203) */
    private String financeDate;

    /** 出资主体ID */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;

    /** 投资金额 */
    private Double inveAmt;

    /** 所属行业 */
    private String industry;

    /** 所属行业名称 */
    private String industryName;

    /** 说明 */
    private String remark;

    /** 附件 */
    private String attaFile;

    /** 项目ID */
    private String projId;

    /** 项目名称 */
    private String projName;


    /** 排序 */
    private Long sortOrder;

    /** 版本号 */
    private Long rowVersion;

    private String processInstId;

    private String processStatus;

    private String entStatusName;

    private String financeDateName;


    private String processStatusName;


    private  List<RegulationApproveCommModel> list;


    private String  taskId;


    private String  deftId;


    private String  mcName;


    private String  regName;

    private String commFile;


    public String getIraId() {
        return iraId;
    }

    public void setIraId(String iraId) {
        this.iraId = iraId;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(String financeDate) {
        this.financeDate = financeDate;
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

    public Double getInveAmt() {
        return inveAmt;
    }

    public void setInveAmt(Double inveAmt) {
        this.inveAmt = inveAmt;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttaFile() {
        return attaFile;
    }

    public void setAttaFile(String attaFile) {
        this.attaFile = attaFile;
    }

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

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getEntStatusName() {
        return entStatusName;
    }

    public void setEntStatusName(String entStatusName) {
        this.entStatusName = entStatusName;
    }

    public String getFinanceDateName() {
        return financeDateName;
    }

    public void setFinanceDateName(String financeDateName) {
        this.financeDateName = financeDateName;
    }

    public List<RegulationApproveCommModel> getList() {
        return list;
    }

    public void setList(List<RegulationApproveCommModel> list) {
        this.list = list;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMcName() {
        return mcName;
    }

    public void setMcName(String mcName) {
        this.mcName = mcName;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getCommFile() {
        return commFile;
    }

    public void setCommFile(String commFile) {
        this.commFile = commFile;
    }

    public String getDeftId() {
        return deftId;
    }

    public void setDeftId(String deftId) {
        this.deftId = deftId;
    }

    public String getProcessStatusName() {
        return processStatusName;
    }

    public void setProcessStatusName(String processStatusName) {
        this.processStatusName = processStatusName;
    }
}
