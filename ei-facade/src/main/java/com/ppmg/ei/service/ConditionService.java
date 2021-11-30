package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;

public interface ConditionService extends BaseService<ConditionModel> {

    /*===========征集信息===================*/

    /**
     * 分页查询征集信息列表
     *
     * @param var1
     * @return
     */
    PageInfo<ConditionInfoModel> selectListPageInfo(SearchCondition var1);

    /**
     * 征集信息详情
     *
     * @param id
     * @return
     */
    //public ConditionInfoModel selectConditionInfoDetails(String id);
    public ConditionModel selectConditionInfoDetails(String id);
    /**
     * 征集信息增加
     *
     * @param conditionInfoModel
     * @return
     */
    void addConditionInfos(ConditionInfoModel conditionInfoModel);

    /**
     * 征集信息更新
     *
     * @param conditionInfoModel
     * @return
     */
    void updateConditionInfos(ConditionInfoModel conditionInfoModel);

    /**
     * 征集信息删除
     *
     * @param id
     * @return
     */
    void deleteConditionInfos(String id);

    /**
     * 根据公开征集id查询征集信息
     *
     * @param conditionId
     * @return
     */
    ConditionInfoModel selectOneConditionInfo(String conditionId);

    ConditionInfoModel selectOneConditionInfoT(String infoId);

    //-----------------基金管理人-----------------------

    /**
     * 查询基金管理人列表
     *
     * @param var1
     * @return
     */
    PageInfo<ConditionItemModel> selectListPageItem(SearchCondition var1);

    /**
     * 查询基金管理人详情
     *
     * @param itemId
     * @return
     */
    ConditionItemModel selectConditionItemDetailService(String itemId);

    /**
     * 插入基金管理人：TZ_T_COO_CONDITION_ITEM表
     *
     * @param conditionItemModel
     * @return
     */
    String insertConditionItems(ConditionItemModel conditionItemModel);

    /**
     * 更新基金管理人：TZ_T_COO_CONDITION_ITEM表
     *
     * @param conditionItemModel
     * @return
     */
    Integer updateConditionItems(ConditionItemModel conditionItemModel);

    /**
     * 删除基金管理人
     *
     * @param
     * @return
     */
    Integer deleteConditionItems(String[] arr);

    //**********征集材料***********

    /**
     * 查询征集材料详情
     *
     * @param fileId
     * @return
     */
    ConditionFileModel selectConditionFileDetails(String fileId);

    /**
     * 分页查询母基金
     *
     * @param var1
     * @return
     */
    PageInfo<FundInfoModel> selectListPageFundInfo(SearchCondition var1);

    /**
     * 查询基金管理人
     *
     * @param var1
     * @param <E>
     * @return
     */
    <E> PageInfo<E> selectAdmins(SearchCondition var1);

    Boolean next(String process_key, String bizKey, String task_Number);

}
