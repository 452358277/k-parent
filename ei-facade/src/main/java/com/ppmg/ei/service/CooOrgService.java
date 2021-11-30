package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.CooOrgSearchBean;
import com.ppmg.ei.model.CooOrgModel;

import java.util.List;

/** 
 * 合作方机构库
 * @author null
 * @date 2019-08-13 10:53 
 */ 
public interface CooOrgService extends BaseService<CooOrgModel>  {

    /**
     * 新增合作方机构库
     * @param model
     */
    void insertModel(CooOrgModel model);

    /**
     * 分页查询
     * @param var1
     * @param searchBean
     * @param <E>
     * @return
     */
    <E> PageInfo<CooOrgModel> selectPage(SearchCondition var1, CooOrgSearchBean searchBean);

    /**
     * 更新合作方机构库
     * @param model
     */
    void updateModel(CooOrgModel model);

    /**
     * 批量删除
     * @param orgIds
     */
    void deleteBatchModel(String[] orgIds);

    /**
     * 查询集合
     * @param sqlModel
     * @return
     */
    List<CooOrgModel> selectListModel(CooOrgModel sqlModel);


    /**
     * 下拉款查询所有的
     * @param
     * @return
     */
    List<CooOrgModel> selectListByCooName (CooOrgModel cooOrgModel );



}