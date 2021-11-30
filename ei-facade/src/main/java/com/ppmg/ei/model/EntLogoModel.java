package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EntLogoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String entId;

	/** 图标token */
	private String icon;

	/** 图标 */
	private byte[] iconBlob;

	/** 备注 */
	private String remark;

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public byte[] getIconBlob() {
		return iconBlob;
	}

	public void setIconBlob(byte[] iconBlob) {
		this.iconBlob = iconBlob;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}