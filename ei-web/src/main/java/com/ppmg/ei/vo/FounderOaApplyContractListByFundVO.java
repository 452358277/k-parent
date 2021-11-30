package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FounderOaApplyContractListByFundVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 合同申请编号，唯一值，主键 */
//	private String applyNo;

	/** 合同名称 */
	private String contractName;


	/** 合同类型 */
	/*private String contractType;*/

	/** 文件名称，合同中包含的文件 */
	/*private String fileName;*/

	/** 附件 */
	private String attachmentName;

	/** 签订日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date signDate;

	/** 非空，申请人ID,当前登录者ID */
	/*private long applicantId;*/

	/** 申请人姓名 */
	private String applicantName;

	/** 申请状态，非空，0草稿状态，1流程:未完成处理（审批中），2：未完成：退回修改,3:已完成-申请失败，4：已完成-申请成功 */
	/*private String applyStatus;

	*//** 申请人所属部门 *//*
	private String applicantDepartment;*/

	/** 合同类型汉字 */
	private String contractTypeDesc;


	/** 附件 */
	private List<EiTAttachmentVO> attachmentList;

//	private FixflowRunTaskinstanceModel instanceModel;

	/*public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}*/

	/*public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}*/

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/*public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}*/

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractTypeDesc() {
		return contractTypeDesc;
	}

	public void setContractTypeDesc(String contractTypeDesc) {
		this.contractTypeDesc = contractTypeDesc;
	}

	public List<EiTAttachmentVO> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<EiTAttachmentVO> attachmentList) {
		this.attachmentList = attachmentList;
	}
/*public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}*/
}