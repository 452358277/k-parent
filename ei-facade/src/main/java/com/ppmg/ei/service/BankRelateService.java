package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.BankRelateModel;

public interface BankRelateService extends BaseService<BankRelateModel>  {

    void insertById(BankRelateModel bankRelateModel,String userId) throws  Exception;

    void updateOpenOrClose(String bankId,String fundId,String status,String accounts,String userId)throws  Exception;

    void updateByUserRelate(BankRelateModel model);
}