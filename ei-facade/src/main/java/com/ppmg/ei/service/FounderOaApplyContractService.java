package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FounderOaApplyContractModel;

import java.util.List;

public interface FounderOaApplyContractService extends BaseService<FounderOaApplyContractModel>  {

    /**
     * 通过主键列表逻辑删除
     * @param ids
     */
    void deleteByIds(List<String> ids);

    /**
     * 获取母基金合同列表
     * @param searchCondition
     */
    <FounderOaApplyContractModel> PageInfo<FounderOaApplyContractModel> selectApplyContractListByFundId(SearchCondition searchCondition);

}