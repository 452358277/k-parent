package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class AppUserroleModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private long appid;

	/** null */
	private long userid;

	/** null */
	private long roleid;

	public long getAppid() {
		return appid;
	}

	public void setAppid(long appid) {
		this.appid = appid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

}