package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class ApplyInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 表单申请编号,唯一值,主键 */
	private String applyNo;

	/** 非空，申请人ID,当前登录者ID */
	private long applicantId;

	/** 申请人姓名 */
	private String applicantName;

	/** 申请人所属公司 */
	private String applicantCompany;

	/** 申请人所属部门 */
	private String applicantDepartment;

	/** 申请日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date applyTime;

	/** 申请状态，非空，0草稿状态，1流程:未完成处理（审批中），2：未完成：退回修改,3:已完成-申请失败，4：已完成-申请成功 */
	private String applyStatus;

	/** 申请类型代码，非空 */
	private String applyType;

	/** 申请描述/备注 */
	private String applyDesc;

	/** 申请原因 */
	private String applyReason;

	/** 所在公司ID */
	private Long applicantCompanyId;

	/** 所在部门ID */
	private Long applicantDepartmentId;

	/** 申请名称/申请标题 */
	private String applyName;

	/** 流程状态 */
	private String processStatus;

	/** 流程ID */
	private String processInstId;

	/** 附件 */
	private String attachment;

	/** 审批完成时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date processEndTime;

	/** 总金额 */
	private Double totalAmount;

	/** 备注 */
	private String remark;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
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

	public String getApplicantCompany() {
		return applicantCompany;
	}

	public void setApplicantCompany(String applicantCompany) {
		this.applicantCompany = applicantCompany;
	}

	public String getApplicantDepartment() {
		return applicantDepartment;
	}

	public void setApplicantDepartment(String applicantDepartment) {
		this.applicantDepartment = applicantDepartment;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Long getApplicantCompanyId() {
		return applicantCompanyId;
	}

	public void setApplicantCompanyId(Long applicantCompanyId) {
		this.applicantCompanyId = applicantCompanyId;
	}

	public Long getApplicantDepartmentId() {
		return applicantDepartmentId;
	}

	public void setApplicantDepartmentId(Long applicantDepartmentId) {
		this.applicantDepartmentId = applicantDepartmentId;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getProcessEndTime() {
		return processEndTime;
	}

	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}