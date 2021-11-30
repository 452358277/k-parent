package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class AppRoleModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

	/** null */
	private String name;

	/** null */
	private String description;

	/** null */
	private String guid;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date createddate;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date modifieddate;

	/** null */
	private String readonly;

	/** null */
	private String displayname;

	/** null */
	private Long coordinatorid;

	/** null */
	private Long shuxing2;

	/** null */
	private Long sortorder;

	private List<AppUserModel> userList;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public Long getCoordinatorid() {
		return coordinatorid;
	}

	public void setCoordinatorid(Long coordinatorid) {
		this.coordinatorid = coordinatorid;
	}

	public Long getShuxing2() {
		return shuxing2;
	}

	public void setShuxing2(Long shuxing2) {
		this.shuxing2 = shuxing2;
	}

	public Long getSortorder() {
		return sortorder;
	}

	public void setSortorder(Long sortorder) {
		this.sortorder = sortorder;
	}

	public List<AppUserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<AppUserModel> userList) {
		this.userList = userList;
	}
}