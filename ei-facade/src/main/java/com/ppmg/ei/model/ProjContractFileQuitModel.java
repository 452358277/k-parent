package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjContractFileQuitModel extends BaseModel {
    /** 主键 */
    private String fileId;

    /** 项目ID */
    private String projId;

    /** 文件类别 */
    private String fileType;

    /** 文件名称 */
    private String fileTitle;

    /** 所属模块（1：项目执行-合同列表，2：项目执行-其他审批文件，3：投后-日常监控-合同，4：投后-退出管理-合同） */
    private String firstParty;

    /** 乙方（弃用） */
    private String secondParty;

    /** 起草人 */
    private String draftsman;

    /** 起草日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date draftDt;

    /** 董事席数 */
    private Long directorCount;

    /** 元禾董事席数 */
    private Long yhDireCount;

    /** 监事席数 */
    private Long supervisorCount;

    /** 元禾监事席数 */
    private Long yhSupeCount;

    /** 合同文件 */
    private String contractFile;

    /** 备注 */
    private String remark;

    /** 最终版合同 */
    private String finalFile;

    /** 合同状态（0：草稿，1：审核中，2：已审核，3：已签订，4：无效，9：删除） */
    private String status;

    /** 流程实例 */
    private String processInstId;

    /** null */
    private String projGuid;

    /** null */
    private String processversioninstanceguid;

    /** null */
    private String svgPkGuid;

    /** null */
    private String svgAttaGuid;

    /** 合同签订时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date signDt;

    /** 合同签订金额 */
    private Double signAmount;

    /** 合同签订金额币种 */
    private String signAmountCurr;

    /** 签订金额折算为人民币 */
    private Double signAmountRmb;

    /** 出资主体ID */
    private String afterFunderId;

    /** 最终协议金额 */
    private Double endAgreeAmont;

    /** 最终协议金额币种 */
    private String endAgreeAmontCurr;

    /** 最终协议金额折算为人民币 */
    private Double endAgreeAmontRmb;

    /** 本协议金额 */
    private Double thisAgreeAmont;

    /** 本协议金额币种 */
    private String thisAgreeAmontCurr;

    /** 本协议金额折算为人民币 */
    private Double thisAgreeAmontRmb;

    /** 投前项目ID */
    private String projIdPi;

    /** 测试ID */
    private String csid;

    /** 企业ID */
    private String objectId;

    /** 企业名称 */
    private String projObjectName;

    /** 所属平台 */
    private String groupId;



    /** 当前子基金规模 */
    private Double subFundAmont;

    /** 当前子基金规模币种 */
    private String subFundAmontCurr;

    /** 当前子基金规模人民币 */
    private Double subFundAmontRmb;

    /** 当前认缴规模 */
    private Double currentAmount;

    /** 当前认缴规模币种 */
    private String currentAmountCurr;

    /** 当前认缴规模人民币 */
    private Double currentAmountRmb;

    /** 当前认缴占比 */
    private Long currentRatio;

    /** 是否需要国有股权评估102 */
    private String isGqpg;

    /** 是否需要【出资主体】的基金执行事务合伙人(委派代表)/法定代表人线下签字 */
    private String isInveSign;

    /** 运营主体简称 */
    private String enName;

    /** 运营主体统一社会信用代码 */
    private String creditCode;

    /** 真正的被投对象ID */
    private String objectId2;

    /** 真正的被投对象全称 */
    private String projObjectName2;

    /** 真正的被投对象统一社会信用代码 */
    private String creditCode2;

    /** 运营主体是否境内（0否，1是） */
    private String isAbroad;

    /** 被投对象是否境内（0否，1是） */
    private String isAbroad2;

    /** 退出决策ID */
    private String appId;

    /** 重点关注条款 */
    private String majorTerm;

    /** 是签署合同，还是退出合同（0：退出；1：签署） */
    private String signQuit;


    //申请名称(退出决策名称)
    private String appName;
    //退出日期
    private String quitDt;
    //退出方式
    private String quitWay;
    /*基金认缴总规模（万元）--认缴出资额（万元）*/
    private Double fundSubscribed;
    /*退基金实缴规模（万元）--实缴出资额（万元）*/
    private Double fundPaidIn;
    /** 占股比 */
    private Double shareRatio;
    //收回金额
    private String backAmount;
    //退出金额(退出认缴出资额（万元)
    private String exitAmount;
    //退出部分所占股比
    private String exitShareRatio;
    //剩余部分所占股比
    private String restShareRatio;
    //决策事项(决策内容)
    private String decisionDetail;

    private String tag;

    private List<ProjContractFileQuitUtilModel> projContractFileQuitUtilModel;

    public List<ProjContractFileQuitUtilModel> getProjContractFileQuitUtilModel() {
        return projContractFileQuitUtilModel;
    }

    public void setProjContractFileQuitUtilModel(List<ProjContractFileQuitUtilModel> projContractFileQuitUtilModel) {
        this.projContractFileQuitUtilModel = projContractFileQuitUtilModel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getDraftsman() {
        return draftsman;
    }

    public void setDraftsman(String draftsman) {
        this.draftsman = draftsman;
    }

    public Date getDraftDt() {
        return draftDt;
    }

    public void setDraftDt(Date draftDt) {
        this.draftDt = draftDt;
    }

    public Long getDirectorCount() {
        return directorCount;
    }

    public void setDirectorCount(Long directorCount) {
        this.directorCount = directorCount;
    }

    public Long getYhDireCount() {
        return yhDireCount;
    }

    public void setYhDireCount(Long yhDireCount) {
        this.yhDireCount = yhDireCount;
    }

    public Long getSupervisorCount() {
        return supervisorCount;
    }

    public void setSupervisorCount(Long supervisorCount) {
        this.supervisorCount = supervisorCount;
    }

    public Long getYhSupeCount() {
        return yhSupeCount;
    }

    public void setYhSupeCount(Long yhSupeCount) {
        this.yhSupeCount = yhSupeCount;
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(String finalFile) {
        this.finalFile = finalFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getProjGuid() {
        return projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public String getProcessversioninstanceguid() {
        return processversioninstanceguid;
    }

    public void setProcessversioninstanceguid(String processversioninstanceguid) {
        this.processversioninstanceguid = processversioninstanceguid;
    }

    public String getSvgPkGuid() {
        return svgPkGuid;
    }

    public void setSvgPkGuid(String svgPkGuid) {
        this.svgPkGuid = svgPkGuid;
    }

    public String getSvgAttaGuid() {
        return svgAttaGuid;
    }

    public void setSvgAttaGuid(String svgAttaGuid) {
        this.svgAttaGuid = svgAttaGuid;
    }

    public Date getSignDt() {
        return signDt;
    }

    public void setSignDt(Date signDt) {
        this.signDt = signDt;
    }

    public Double getSignAmount() {
        return signAmount;
    }

    public void setSignAmount(Double signAmount) {
        this.signAmount = signAmount;
    }

    public String getSignAmountCurr() {
        return signAmountCurr;
    }

    public void setSignAmountCurr(String signAmountCurr) {
        this.signAmountCurr = signAmountCurr;
    }

    public Double getSignAmountRmb() {
        return signAmountRmb;
    }

    public void setSignAmountRmb(Double signAmountRmb) {
        this.signAmountRmb = signAmountRmb;
    }

    public String getAfterFunderId() {
        return afterFunderId;
    }

    public void setAfterFunderId(String afterFunderId) {
        this.afterFunderId = afterFunderId;
    }

    public Double getEndAgreeAmont() {
        return endAgreeAmont;
    }

    public void setEndAgreeAmont(Double endAgreeAmont) {
        this.endAgreeAmont = endAgreeAmont;
    }

    public String getEndAgreeAmontCurr() {
        return endAgreeAmontCurr;
    }

    public void setEndAgreeAmontCurr(String endAgreeAmontCurr) {
        this.endAgreeAmontCurr = endAgreeAmontCurr;
    }

    public Double getEndAgreeAmontRmb() {
        return endAgreeAmontRmb;
    }

    public void setEndAgreeAmontRmb(Double endAgreeAmontRmb) {
        this.endAgreeAmontRmb = endAgreeAmontRmb;
    }

    public Double getThisAgreeAmont() {
        return thisAgreeAmont;
    }

    public void setThisAgreeAmont(Double thisAgreeAmont) {
        this.thisAgreeAmont = thisAgreeAmont;
    }

    public String getThisAgreeAmontCurr() {
        return thisAgreeAmontCurr;
    }

    public void setThisAgreeAmontCurr(String thisAgreeAmontCurr) {
        this.thisAgreeAmontCurr = thisAgreeAmontCurr;
    }

    public Double getThisAgreeAmontRmb() {
        return thisAgreeAmontRmb;
    }

    public void setThisAgreeAmontRmb(Double thisAgreeAmontRmb) {
        this.thisAgreeAmontRmb = thisAgreeAmontRmb;
    }

    public String getProjIdPi() {
        return projIdPi;
    }

    public void setProjIdPi(String projIdPi) {
        this.projIdPi = projIdPi;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getProjObjectName() {
        return projObjectName;
    }

    public void setProjObjectName(String projObjectName) {
        this.projObjectName = projObjectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Double getSubFundAmont() {
        return subFundAmont;
    }

    public void setSubFundAmont(Double subFundAmont) {
        this.subFundAmont = subFundAmont;
    }

    public String getSubFundAmontCurr() {
        return subFundAmontCurr;
    }

    public void setSubFundAmontCurr(String subFundAmontCurr) {
        this.subFundAmontCurr = subFundAmontCurr;
    }

    public Double getSubFundAmontRmb() {
        return subFundAmontRmb;
    }

    public void setSubFundAmontRmb(Double subFundAmontRmb) {
        this.subFundAmontRmb = subFundAmontRmb;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getCurrentAmountCurr() {
        return currentAmountCurr;
    }

    public void setCurrentAmountCurr(String currentAmountCurr) {
        this.currentAmountCurr = currentAmountCurr;
    }

    public Double getCurrentAmountRmb() {
        return currentAmountRmb;
    }

    public void setCurrentAmountRmb(Double currentAmountRmb) {
        this.currentAmountRmb = currentAmountRmb;
    }

    public Long getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(Long currentRatio) {
        this.currentRatio = currentRatio;
    }

    public String getIsGqpg() {
        return isGqpg;
    }

    public void setIsGqpg(String isGqpg) {
        this.isGqpg = isGqpg;
    }

    public String getIsInveSign() {
        return isInveSign;
    }

    public void setIsInveSign(String isInveSign) {
        this.isInveSign = isInveSign;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getObjectId2() {
        return objectId2;
    }

    public void setObjectId2(String objectId2) {
        this.objectId2 = objectId2;
    }

    public String getProjObjectName2() {
        return projObjectName2;
    }

    public void setProjObjectName2(String projObjectName2) {
        this.projObjectName2 = projObjectName2;
    }

    public String getCreditCode2() {
        return creditCode2;
    }

    public void setCreditCode2(String creditCode2) {
        this.creditCode2 = creditCode2;
    }

    public String getIsAbroad() {
        return isAbroad;
    }

    public void setIsAbroad(String isAbroad) {
        this.isAbroad = isAbroad;
    }

    public String getIsAbroad2() {
        return isAbroad2;
    }

    public void setIsAbroad2(String isAbroad2) {
        this.isAbroad2 = isAbroad2;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMajorTerm() {
        return majorTerm;
    }

    public void setMajorTerm(String majorTerm) {
        this.majorTerm = majorTerm;
    }

    public String getSignQuit() {
        return signQuit;
    }

    public void setSignQuit(String signQuit) {
        this.signQuit = signQuit;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(String quitDt) {
        this.quitDt = quitDt;
    }

    public String getQuitWay() {
        return quitWay;
    }

    public void setQuitWay(String quitWay) {
        this.quitWay = quitWay;
    }

    public Double getFundSubscribed() {
        return fundSubscribed;
    }

    public void setFundSubscribed(Double fundSubscribed) {
        this.fundSubscribed = fundSubscribed;
    }

    public Double getFundPaidIn() {
        return fundPaidIn;
    }

    public void setFundPaidIn(Double fundPaidIn) {
        this.fundPaidIn = fundPaidIn;
    }

    public Double getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(Double shareRatio) {
        this.shareRatio = shareRatio;
    }

    public String getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(String backAmount) {
        this.backAmount = backAmount;
    }

    public String getExitAmount() {
        return exitAmount;
    }

    public void setExitAmount(String exitAmount) {
        this.exitAmount = exitAmount;
    }

    public String getExitShareRatio() {
        return exitShareRatio;
    }

    public void setExitShareRatio(String exitShareRatio) {
        this.exitShareRatio = exitShareRatio;
    }

    public String getRestShareRatio() {
        return restShareRatio;
    }

    public void setRestShareRatio(String restShareRatio) {
        this.restShareRatio = restShareRatio;
    }

    public String getDecisionDetail() {
        return decisionDetail;
    }

    public void setDecisionDetail(String decisionDetail) {
        this.decisionDetail = decisionDetail;
    }
}
