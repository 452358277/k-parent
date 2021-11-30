package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.founder.ssm.core.vo.BaseResponse;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.AppUserModel;

import java.util.List;

/**
 * 用户 [Service]
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */ 
public interface AppUserService extends BaseService<AppUserModel>  {

    /**
     * 根据角色ID获取用户名
     * @param roleId
     * @return
     */
    public String getUserIdByRoleId(String roleId);

    /**
     * 根据userId获取用户信息
     * @param userId
     * @return
     */
    public String getUserNameById(String userId);

    /**
     * 根据userId字符串获取用户信息列表
     * @param userId
     * @return
     */
    public List<AppUserModel> getUserListById(String userId);

    /**
     * 根据角色ID获取用户列表
     * @param roleId
     * @return
     */
    public List<AppUserModel> getUserListByRoleId(String roleId);

    /**
     * 根据登录名查询用户
     * @param loginName
     * @return
     */
    AppUserModel selectByLoginName(String loginName);

    /**
     * 新增外部用户
     * @param model
     * @param roleId
     * @return
     */
    BaseResponse inserOutUser(AppUserModel model, Long roleId);

    /**
     * 逻辑删除用户信息
     * @param model
     * @return
     */
    Integer deleteLogic(AppUserModel model);

    /**
     * 修改密码
     * @param model
     * @param oldPassword
     * @return
     */
    BaseResponse updatePassword(AppUserModel model, String oldPassword);


    <E> PageInfo<E> selectUserListPage(SearchCondition var1);


    <E> PageInfo<E> selectUserAllListPage(SearchCondition var1);

}