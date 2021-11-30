package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FounderOaApplyInfoModel;

import java.util.List;

public interface FounderOaApplyInfoService extends BaseService<FounderOaApplyInfoModel>  {

    /**
     * 通过主键列表逻辑删除
     * @param ids
     */
    void deleteByIds(List<String> ids);

}