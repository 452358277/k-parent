package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AppUserroleModel;
import com.ppmg.ei.service.AppUserroleService;

@Component("appUserroleService")
@Service(interfaceClass = AppUserroleService.class)
public class AppUserroleServiceImpl extends BaseServiceImpl<AppUserroleModel> implements AppUserroleService {

	public AppUserroleServiceImpl(){
		this.setNamespace("AppUserrole");
	}

	@Override
	public String getUserIdByRoleId(String roleId) {
		return (String) this.selectOne("getUserIdByRoleId",roleId);
	}
}