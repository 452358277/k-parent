package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FounderOaApplySealVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 用印申请编号，唯一值，主键 */
	private String applyNo;

	/** 用印名称 */
	private String applyName;

	/** 用印说明 */
	private String sealContent;

	/** 附件 */
	private String attachment;

	/** 公告申请时间，格式YYYYMMD */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date applyTime;

	/** 公告类型 */
	private String type;

	/** 公告类型name */
	private String typeNmae;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeNmae() {
		return typeNmae;
	}

	public void setTypeNmae(String typeNmae) {
		this.typeNmae = typeNmae;
	}

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

	public String getSealContent() {
		return sealContent;
	}

	public void setSealContent(String sealContent) {
		this.sealContent = sealContent;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}


}