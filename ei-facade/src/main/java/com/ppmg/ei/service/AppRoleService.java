package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.AppRoleModel;

import java.util.List;

public interface AppRoleService extends BaseService<AppRoleModel>  {

    /**
     * 根据平台ID获取角色列表
     * @param groupId
     * @return
     */
    public List<AppRoleModel> getRoleListByGroupId(String groupId);

    /**
     * 查询用户对应的角色
     * @param
     * @return
     */
    public List<AppRoleModel> selectByUserId(String userId);
}