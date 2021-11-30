package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.BdTFundAttaModel;

public interface BdTFundAttaService extends BaseService<BdTFundAttaModel>  {

    /**
     * 文件库右侧列表
     * @param var1
     * @return com.github.pagehelper.PageInfo<com.ppmg.ei.model.BdTFundAttaModel>
     * @author zhaokuiyu
     * @date 2020/2/12 16:07
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    PageInfo<BdTFundAttaModel> selectListPageNT(SearchCondition var1);

    void addBdTFundAttaModel(BdTFundAttaModel bdTFundAttaModel);


}