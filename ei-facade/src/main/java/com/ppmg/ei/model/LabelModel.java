package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import java.util.List;

public class LabelModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String labelId;

	/** 父级ID */
	private String parentId;

	/** 标签名称 */
	private String labelName;

	/** 备注 */
	private String remark;

	/** 状态（0正常，1删除） */
	private String status;

	private List<LabelModel> listStr;

	public List<LabelModel> getListStr() {
		return listStr;
	}

	public void setListStr(List<LabelModel> listStr) {
		this.listStr = listStr;
	}

	private List<CommTLabelItemModel> labelItemList;

	public List<CommTLabelItemModel> getLabelItemList() {
		return labelItemList;
	}

	public void setLabelItemList(List<CommTLabelItemModel> labelItemList) {
		this.labelItemList = labelItemList;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}