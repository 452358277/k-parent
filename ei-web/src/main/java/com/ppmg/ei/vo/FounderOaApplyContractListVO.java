package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FounderOaApplyContractListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 合同申请编号，唯一值，主键 */
	private String applyNo;

	/** 合同名称 */
	private String contractName;

	/** 合同编号 */
	private String contractNo;

	/** 合同类型 */
	private String contractType;

	/** 合同部门，默认为申请人所属部门 */
	private String contractDepartment;

	/** 合同内容 */
	private String contractContent;

	/** 备注 */
	private String remark;

	/** 盖章编号/ */
	private String sealNo;

	/** 用印 */
	private Long sealCopies;

	/** 文件名称，合同中包含的文件 */
	private String fileName;

	/** 附件 */
	private String attachment;

	/** null */
	private String contractDepartmentId;

	/** 合同折算成人民币金额 */
	private Double amount;

	/** 结算方式，0，一次性，1，分期 */
	private String billOptions;

	/** 原合同号 */
	private String orgContractNo;

	/** 是否是变更合同,1：是，0：不是，3：母基金合同 */
	private String orgContractFlag;

	/** 机构ID */
	private String orgId;

	/** 机构NAME */
	private String orgName;

	/** 合同金额 */
	private Double amountMoney;

	/** 合同币种 */
	private String amountCurr;

	/** 关联采购询比价表单申请号，采购询比价表单主键 */
	private String applyNo2;

	/** 关联基金ID */
	private String relFundId;

	/** 关联基金名称 */
	private String relFundName;

	/** 关联平台ID */
	private String relPlatId;

	/** 关联平台名称 */
	private String relPlatName;

	/** 合同起始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startTime;

	/** 合同终止时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endTime;

	/** 合同起止时间 */
	private String startTime_endTime;

	/** 状态（是否过期） */
	private String effecStatus;

	/** 流程状态 */
	private String processStatus;

	/** 申请日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date applyTime;

	/** null */
	private String processInstId;

	/** 非空，申请人ID,当前登录者ID */
	private long applicantId;

	/** 申请人姓名 */
	private String applicantName;

	/** 申请状态，非空，0草稿状态，1流程:未完成处理（审批中），2：未完成：退回修改,3:已完成-申请失败，4：已完成-申请成功 */
	private String applyStatus;

	/** 申请人所属部门 */
	private String applicantDepartment;

	/** 合同类型汉字 */
	private String contractTypeDesc;


	private FixflowRunTaskinstanceModel instanceModel;

	/** 合同附件List */
	private List fileLists;

	public List getFileLists() {
		return fileLists;
	}

	public void setFileLists(List fileLists) {
		this.fileLists = fileLists;
	}




	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractDepartment() {
		return contractDepartment;
	}

	public void setContractDepartment(String contractDepartment) {
		this.contractDepartment = contractDepartment;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public Long getSealCopies() {
		return sealCopies;
	}

	public void setSealCopies(Long sealCopies) {
		this.sealCopies = sealCopies;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getContractDepartmentId() {
		return contractDepartmentId;
	}

	public void setContractDepartmentId(String contractDepartmentId) {
		this.contractDepartmentId = contractDepartmentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBillOptions() {
		return billOptions;
	}

	public void setBillOptions(String billOptions) {
		this.billOptions = billOptions;
	}

	public String getOrgContractNo() {
		return orgContractNo;
	}

	public void setOrgContractNo(String orgContractNo) {
		this.orgContractNo = orgContractNo;
	}

	public String getOrgContractFlag() {
		return orgContractFlag;
	}

	public void setOrgContractFlag(String orgContractFlag) {
		this.orgContractFlag = orgContractFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getAmountCurr() {
		return amountCurr;
	}

	public void setAmountCurr(String amountCurr) {
		this.amountCurr = amountCurr;
	}

	public String getApplyNo2() {
		return applyNo2;
	}

	public void setApplyNo2(String applyNo2) {
		this.applyNo2 = applyNo2;
	}

	public String getRelFundId() {
		return relFundId;
	}

	public void setRelFundId(String relFundId) {
		this.relFundId = relFundId;
	}

	public String getRelFundName() {
		return relFundName;
	}

	public void setRelFundName(String relFundName) {
		this.relFundName = relFundName;
	}

	public String getRelPlatId() {
		return relPlatId;
	}

	public void setRelPlatId(String relPlatId) {
		this.relPlatId = relPlatId;
	}

	public String getRelPlatName() {
		return relPlatName;
	}

	public void setRelPlatName(String relPlatName) {
		this.relPlatName = relPlatName;
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

	public String getStartTime_endTime() {
		return startTime_endTime;
	}

	public void setStartTime_endTime(String startTime_endTime) {
		this.startTime_endTime = startTime_endTime;
	}

	public String getEffecStatus() {
		return effecStatus;
	}

	public void setEffecStatus(String effecStatus) {
		this.effecStatus = effecStatus;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplicantDepartment() {
		return applicantDepartment;
	}

	public void setApplicantDepartment(String applicantDepartment) {
		this.applicantDepartment = applicantDepartment;
	}

	public String getContractTypeDesc() {
		return contractTypeDesc;
	}

	public void setContractTypeDesc(String contractTypeDesc) {
		this.contractTypeDesc = contractTypeDesc;
	}

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}
}