package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class AgroupModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

	/** null */
	private String name;

	/** null */
	private Long fatherid;

	private String userId;

	private String type;

	private List<AgroupModel> children;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFatherid() {
		return fatherid;
	}

	public void setFatherid(Long fatherid) {
		this.fatherid = fatherid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AgroupModel> getChildren() {
		return children;
	}

	public void setChildren(List<AgroupModel> children) {
		this.children = children;
	}

}