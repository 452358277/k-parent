package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class RegulationApproveCommModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String commId;

	/** 合规审查主表主键 */
	private String iraId;

	/** 投决会人员ID */
	private String commUserId;

	/** 投决会人员姓名 */
	private String commUserName;


	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	public String getCommId() {
		return commId;
	}

	public void setCommId(String commId) {
		this.commId = commId;
	}

	public String getIraId() {
		return iraId;
	}

	public void setIraId(String iraId) {
		this.iraId = iraId;
	}

	public String getCommUserId() {
		return commUserId;
	}

	public void setCommUserId(String commUserId) {
		this.commUserId = commUserId;
	}

	public String getCommUserName() {
		return commUserName;
	}

	public void setCommUserName(String commUserName) {
		this.commUserName = commUserName;
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

}