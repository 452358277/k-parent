package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ProjContractFileModel extends BaseModel {

	private static final long serialVersionUID = 1L;

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

	/** 占股比 */
	private Double shareRatio;

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

	private String majorTerm;

	private String fileInfoId;

	private String signQuit;

	/** 附件 */
	private List<EiTAttachmentModel> fileLists;

	private String fileTypeName;

	private String statusName;

	private String signAmountCurrName;
	//省政府1
	private String isGov;


	public String getIsGov() {
		return isGov;
	}

	public void setIsGov(String isGov) {
		this.isGov = isGov;
	}

	public String getSignQuit() {
		return signQuit;
	}

	public void setSignQuit(String signQuit) {
		this.signQuit = signQuit;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSignAmountCurrName() {
		return signAmountCurrName;
	}

	public void setSignAmountCurrName(String signAmountCurrName) {
		this.signAmountCurrName = signAmountCurrName;
	}

	public String getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(String fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getMajorTerm() {
		return majorTerm;
	}

	public void setMajorTerm(String majorTerm) {
		this.majorTerm = majorTerm;
	}

	public List<EiTAttachmentModel> getFileLists() {
		return fileLists;
	}

	public void setFileLists(List<EiTAttachmentModel> fileLists) {
		this.fileLists = fileLists;
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

	public Double getShareRatio() {
		return shareRatio;
	}

	public void setShareRatio(Double shareRatio) {
		this.shareRatio = shareRatio;
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

}