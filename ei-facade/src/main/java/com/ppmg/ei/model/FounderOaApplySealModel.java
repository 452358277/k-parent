package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FounderOaApplySealModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 用印申请编号，唯一值，主键 */
	private String applyNo;

	/** 用印 */
	private String sealContent;

	/** 用印时间，格式YYYYMMD */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date sealTime;

	/** 用印份数 */
	private Long sealCopies;

	/** 公章 */
	private String sealNo;

	/** 备注 */
	private String remark;

	/** 经半人 */
	private String responsiblePerson;

	/** 用印用途类型：01业务用印，00非业务用印，默认为非业务用印 */
	private String sealUses;

	/** null */
	private String attachment;

	/** null */
	private String iscopy;

	/** null */
	private Long isnum;

	/** null */
	private String license;

	/** null */
	private Long leaderId;

	/** null */
	private String leaderName;

	/** null */
	private String type;

	/** 只用于公告，公告类型 */
	private String isNotice;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getSealContent() {
		return sealContent;
	}

	public void setSealContent(String sealContent) {
		this.sealContent = sealContent;
	}

	public Date getSealTime() {
		return sealTime;
	}

	public void setSealTime(Date sealTime) {
		this.sealTime = sealTime;
	}

	public Long getSealCopies() {
		return sealCopies;
	}

	public void setSealCopies(Long sealCopies) {
		this.sealCopies = sealCopies;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getSealUses() {
		return sealUses;
	}

	public void setSealUses(String sealUses) {
		this.sealUses = sealUses;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getIscopy() {
		return iscopy;
	}

	public void setIscopy(String iscopy) {
		this.iscopy = iscopy;
	}

	public Long getIsnum() {
		return isnum;
	}

	public void setIsnum(Long isnum) {
		this.isnum = isnum;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

}