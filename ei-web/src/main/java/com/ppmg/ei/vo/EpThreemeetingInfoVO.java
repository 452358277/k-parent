package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class EpThreemeetingInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 会议标题 */
	private String meetingTitle;

	/** 附件 */
	private String attaFile;

	/** 三会事项状态 */
	private String status;

	/** 流程实例 */
	private String processInstId;

	/** 出资主体ID */
	private String afterFunderId;

	/** 企业ID */
	private String objectId;

	/** 所属公司 */
	private String groupId;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date createDt;

	/** 合同附件List */
	private List fileLists;

	public List getFileLists() {
		return fileLists;
	}

	public void setFileLists(List fileLists) {
		this.fileLists = fileLists;
	}


	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}


	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
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

	public String getAfterFunderId() {
		return afterFunderId;
	}

	public void setAfterFunderId(String afterFunderId) {
		this.afterFunderId = afterFunderId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

}