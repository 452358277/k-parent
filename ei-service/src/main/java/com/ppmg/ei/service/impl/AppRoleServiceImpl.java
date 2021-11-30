package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AppRoleModel;
import com.ppmg.ei.service.AppRoleService;

import java.util.List;

@Component("appRoleService")
@Service(interfaceClass = AppRoleService.class)
public class AppRoleServiceImpl extends BaseServiceImpl<AppRoleModel> implements AppRoleService {

	public AppRoleServiceImpl(){
		this.setNamespace("AppRole");
	}

	@Override
	public List<AppRoleModel> getRoleListByGroupId(String groupId) {
		return this.selectList("getRoleListByGroupId", groupId);
	}

	@Override
	public List<AppRoleModel> selectByUserId(String userId) {
		return this.selectList("selectByUserId", userId);
	}
}