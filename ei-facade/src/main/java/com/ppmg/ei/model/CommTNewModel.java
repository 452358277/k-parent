package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class CommTNewModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 行业编码 */
	private String id;

	/** 上级编码 */
	private String parentId;

	/** 行业名称 */
	private String newName;

	/** 行业级别 */
	private String newLevel;

	/** 状态(0：正常，1：禁用) */
	private String status;

	/** 顺序编号 */
	private Long orderNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewLevel() {
		return newLevel;
	}

	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

}