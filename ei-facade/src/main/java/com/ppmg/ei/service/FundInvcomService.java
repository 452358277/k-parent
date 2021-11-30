package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FundInvcomModel;

import java.util.List;

public interface FundInvcomService extends BaseService<FundInvcomModel>  {

    /**
     * 根据项目ID获取项目基本信息
     * @param fundId
     * @return
     */
    public List<FundInvcomModel> getFundInvcomInfoByFundId(String fundId);


}