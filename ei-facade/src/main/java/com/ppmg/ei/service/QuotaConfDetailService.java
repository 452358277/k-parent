package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.QuotaConfDetailModel;

import java.util.List;

public interface QuotaConfDetailService extends BaseService<QuotaConfDetailModel>  {

    /**
     * 根据ID获取经营计划执行情况详细信息
     * @param confId
     * @return
     */
    public List<QuotaConfDetailModel> getQuotaConfDetailByConfId(String confId);

}