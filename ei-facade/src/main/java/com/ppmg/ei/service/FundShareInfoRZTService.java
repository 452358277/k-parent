package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FundShareInfoModel;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-10-23 15:36 
 */ 
public interface FundShareInfoRZTService extends BaseService<FundShareInfoModel>  {

     void updateByInfo(FundShareInfoModel fundShareInfoModel) throws  Exception;

}