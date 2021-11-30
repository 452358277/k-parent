package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.AdminInvestSearchBean;
import com.ppmg.ei.bean.AdminSearchBean;
import com.ppmg.ei.model.*;

import java.util.List;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public interface AdminService extends BaseService<AdminModel>  {

    /**
     * 新增基金管理人库
     * @param model 管理人信息
     * @param assetModel 资产信息
     * @param partnerModels 股东信息
     */
    void insertModel(AdminModel model, AdminAssetModel assetModel, List<AdminPartnerModel> partnerModels);

    /**
     * 分页查询
     * @param var1
     * @param searchBean
     * @param <E>
     * @return
     */
    <E> PageInfo<AdminListModel> selectPage(SearchCondition var1, AdminSearchBean searchBean);

    /**
     * 更新基金管理人库
     * @param model 管理人信息
     * @param assetModel 资产信息
     * @param partnerModels 股东信息
     */
    void updateModel(AdminModel model, AdminAssetModel assetModel, List<AdminPartnerModel> partnerModels);

    /**
     * 批量删除
     * @param adminIds
     */
    void deleteBatchModel(String[] adminIds);

    /**
     * 查询集合
     * @param sqlModel
     * @return
     */
    List<AdminListModel> selectListModel(AdminModel sqlModel);

    /**
     * 出资人代表库分页查询
     * @param var1
     * @param searchBean
     * @param <E>
     * @return
     */
    <E> PageInfo<AdminInvestModel> selectAdminInvestPage(SearchCondition var1, AdminInvestSearchBean searchBean);

    /**
     * 新增出资人代表库
     * @param model 管理人信息
     */
    void insertAdminInvestModel(AdminModel model,String password);

    /**
     * 更新出资人代表库
     * @param model 管理人信息
     */
    void updateAdminInvestModel(AdminModel model,String password);

    /**
     * 批量删除出资人代表库
     * @param adminIds
     */
    void deleteBatchAdminInvestModel(String[] adminIds);

    void deleteByList(List<AdminModel> list);


     List<AdminModel> getListInfo(AdminModel adminModel);



}