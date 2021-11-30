package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjBackMoneySettleModel;

import java.util.Map;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-09-26 13:57 
 */ 
public interface ProjBackMoneySettleService extends BaseService<ProjBackMoneySettleModel>  {

    //根据appid查询数据
    ProjBackMoneySettleModel selectProjBackMoneySettleAppId(String appId);

    Map<String,Object>  sumBackMoney(String projId);

}