package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.BdTFundLockInfoModel;

public interface BdTFundLockInfoService extends BaseService<BdTFundLockInfoModel>  {

    void saveInfo(BdTFundLockInfoModel model,String userId);

}