package com.ppmg.ei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AppFuncPermissionModel;
import com.ppmg.ei.service.AppFuncPermissionService;

@Component("appFuncPermissionService")
@Service(interfaceClass = AppFuncPermissionService.class)
public class AppFuncPermissionServiceImpl extends BaseServiceImpl<AppFuncPermissionModel> implements AppFuncPermissionService {

	public AppFuncPermissionServiceImpl(){
		this.setNamespace("AppFuncPermission");
	}

	@Override
	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherId1(Map<String, Object> params) {
		return this.selectList("getAppFuncPermissionByFatherId1", params);
	}

	@Override
	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherId(String fatherid) {
		return this.selectList("getAppFuncPermissionByFatherId", fatherid);
	}

	@Override
	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherIdXx(String fatherid) {
		return this.selectList("getAppFuncPermissionByFatherIdXx", fatherid);
	}

	@Override
	public List<AppFuncPermissionModel> getPlatFuncPermissionByFatherId(Map<String, Object> params) {
		return this.selectList("getPlatFuncPermissionByFatherId", params);
	}

	@Override
	public List<AppFuncPermissionModel> getPlatFuncPermissionByFatherIdYhkg(Map<String, Object> params) {
		return this.selectList("getPlatFuncPermissionByFatherIdYhkg", params);
	}
	@Override
	public List<Map<String,Object>> getPlatFuncPermissionByFatherIdJc(Map<String, Object> params) {
		return this.selectList("getPlatFuncPermissionByFatherIdJc", params);
	}
	@Override
	public List<Map<String,Object>> getJcFunc(Map<String, Object> params) {
		return this.selectList("getJcFunc", params);
	}

	@Override
	public List<Map<String, Object>> selectButtenList(Map<String, Object> params) {
		return this.selectList("selectButtenList", params);
	}

	/**
	 * 左侧菜单
	 * @return
	 */
	@Override
	public List<Map<String,Object>> selectMenuTree(Map<String,Object> map) {
		List<Map<String,Object>> modelList = this.selectList("getJcMenuTree", map);
		return modelList;
	}
}