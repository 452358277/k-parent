package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class EntLabelModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String entId;

	/** 标签ID */
	private String labelId;

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

}