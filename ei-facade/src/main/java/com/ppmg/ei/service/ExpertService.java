package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.ExpertSearchBean;
import com.ppmg.ei.model.ExpertLabelModel;
import com.ppmg.ei.model.ExpertListModel;
import com.ppmg.ei.model.ExpertModel;

import java.util.List;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 
public interface ExpertService extends BaseService<ExpertModel>  {

    void updateExpert(ExpertModel expertModel,String labels);

    /**
     * 通过主键列表逻辑删除
     * @param ids
     */
    void deleteByIds(String[] ids, String userId)throws Exception;

    /**
     * 插入数据
     */
    void insertModel( ExpertModel expertModel,String labels) throws Exception;


    @Override
    Integer selectCount(Object o);

    <E> PageInfo<ExpertListModel> selectExpertByPage(SearchCondition var1, ExpertSearchBean searchBean);

    /**
     * 导出 的数据
     * @param var1
     * @param searchBean
     * @return
     */
    List<ExpertListModel>  exportList(SearchCondition var1, ExpertSearchBean searchBean);
}