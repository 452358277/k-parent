package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjReceiptQuitModel;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-10-15 16:17 
 */ 
public interface ProjReceiptQuitService extends BaseService<ProjReceiptQuitModel>  {

    Double getSumAmount(String projId);

}