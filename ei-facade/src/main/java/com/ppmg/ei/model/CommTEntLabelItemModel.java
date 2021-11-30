package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class CommTEntLabelItemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String entId;

	/** 标签ID */
	private String labelId;

	/** 标签属性ID */
	private String itemId;

	/** 填报内容 */
	private String labelName;

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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

}