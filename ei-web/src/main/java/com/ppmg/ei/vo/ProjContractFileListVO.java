package com.ppmg.ei.vo;

import java.util.Date;
import java.util.List;

import com.ppmg.ei.model.EiTAttachmentModel;
import com.ppmg.ei.model.ProjContractFileDebtModel;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;

public class ProjContractFileListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fileId;

	/** 合同名称 */
	private String fileTitle;

	/** 合同类别 */
	private String fileType;

	/** 上传人 */
	private String draftsman;

	/** 上传时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date draftDt;

	/** 签订日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date signDt;

	/** 合同状态（0：草稿，1：审核中，2：已审核，3：已签订，4：无效，9：删除） */
	private String status;

	/** 合同类别 */
	private String fileTypeName;

	/** 流程实例ID */
	private String processInstId;

	/** 合同文件 */
	private String contractFile;

	/** 最终版合同 */
	private String finalFile;

	private Double thisAgreeAmont;

	private ProjContractFileDebtModel projContractFileDebtModel;

	public ProjContractFileDebtModel getProjContractFileDebtModel() {
		return projContractFileDebtModel;
	}

	public void setProjContractFileDebtModel(ProjContractFileDebtModel projContractFileDebtModel) {
		this.projContractFileDebtModel = projContractFileDebtModel;
	}

	public Double getThisAgreeAmont() {
		return thisAgreeAmont;
	}

	public void setThisAgreeAmont(Double thisAgreeAmont) {
		this.thisAgreeAmont = thisAgreeAmont;
	}

	/** 流程实例 */
	private FixflowRunTaskinstanceModel instanceModel;

	/** 合同附件List */
	private List<EiTAttachmentModel> fileLists;

	public List<EiTAttachmentModel> getFileLists() {
		return fileLists;
	}

	public void setFileLists(List<EiTAttachmentModel> fileLists) {
		this.fileLists = fileLists;
	}

	public String getFinalFile() {
		return finalFile;
	}

	public void setFinalFile(String finalFile) {
		this.finalFile = finalFile;
	}

	public String getContractFile() {
		return contractFile;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	public Date getSignDt() {
		return signDt;
	}

	public void setSignDt(Date signDt) {
		this.signDt = signDt;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}


}