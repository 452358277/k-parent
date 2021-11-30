package com.ppmg.ei.service;

import java.util.List;
import java.util.Map;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.AppFuncPermissionModel;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;

public interface AppFuncPermissionService extends BaseService<AppFuncPermissionModel>  {

	/**
	 * 根据父菜单ID获取子基金详情功能权限菜单列表
	 * @param fatherid
	 * @return
	 */
	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherId(String fatherid);


	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherId1(Map<String, Object> params);

	/**
	 * 根据父菜单ID获取子基金详情功能权限菜单列表--新兴平台
	 * @param fatherid
	 * @return
	 */
	public List<AppFuncPermissionModel> getAppFuncPermissionByFatherIdXx(String fatherid);

	/**
	 * 根据父菜单ID获取平台管理功能权限菜单列表
	 * @param params
	 * @return
	 */
	public List<AppFuncPermissionModel> getPlatFuncPermissionByFatherId(Map<String, Object> params);

	/**
	 * 根据父菜单ID获取平台管理功能权限菜单列表--元禾控股
	 * @param params
	 * @return
	 */
	public List<AppFuncPermissionModel> getPlatFuncPermissionByFatherIdYhkg(Map<String, Object> params);


	/**
	 * 根据父菜单ID获取平台管理功能权限菜单列表--金财
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getPlatFuncPermissionByFatherIdJc(Map<String, Object> params);

	/**
	 * 根据菜单id查询子节点
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getJcFunc(Map<String, Object> params);

	List<Map<String,Object>> selectButtenList(Map<String, Object> params);

	/**
	 * 左侧菜单
	 * @return
	 */
	List<Map<String,Object>> selectMenuTree(Map<String,Object> map);
}