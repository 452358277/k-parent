package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class AppUserIdNameVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 用户id */
	private long id;

	/** 用户name，这里用value,迎合前端组件 */
	private String name;

	/** 登录名loginname，这里用myCustomProperty,迎合前端组件 */
	private String loginname;

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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
}