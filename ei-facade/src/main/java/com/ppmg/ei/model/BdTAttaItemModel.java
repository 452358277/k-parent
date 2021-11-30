package com.ppmg.ei.model;

import java.util.List;

import com.founder.ssm.core.model.BaseModel;

public class BdTAttaItemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 附件分类ID */
	private String itemId;

	/** 附件分类名称 */
	private String itemName;

	/** 项目或基金ID */
	private String projectOrFundId;

	/** 父节点Id（0:根节点） */
	private String parentId;

	/** 分类类别(1企业，2基金，3子基金) */
	private String itemType;

	/** 描述 */
	private String description;

	/** 是否可编辑(1：不可编辑；0可编辑) */
	private String editable;

	/** 状态（1:删除;0:未删除;） */
	private String status;
	
	private String isLeaf;
	
	private List<BdTAttaItemModel> childrenList;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProjectOrFundId() {
		return projectOrFundId;
	}

	public void setProjectOrFundId(String projectOrFundId) {
		this.projectOrFundId = projectOrFundId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<BdTAttaItemModel> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<BdTAttaItemModel> childrenList) {
		this.childrenList = childrenList;
	}


}