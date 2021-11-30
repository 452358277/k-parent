package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

import java.util.List;

public class AppRoleVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** null */
	private long id;

	/** 角色名称 */
	private String displayname;

	/** 排序 */
	private Long sortorder;

	/** 角色成员 */
	private List<AppUserVO> userList;

	/** null */
	private Long coordinatorid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Long getSortorder() {
		return sortorder;
	}

	public void setSortorder(Long sortorder) {
		this.sortorder = sortorder;
	}

	public List<AppUserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<AppUserVO> userList) {
		this.userList = userList;
	}
}