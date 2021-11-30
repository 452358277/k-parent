package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class EntLogoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String entId;

	/** 图标 */
	private byte[] iconBlob;

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public byte[] getIconBlob() {
		return iconBlob;
	}

	public void setIconBlob(byte[] iconBlob) {
		this.iconBlob = iconBlob;
	}

}