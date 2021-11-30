package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.FundUserRelateModel;

import java.util.List;
import java.util.Map;

public interface FundUserRelateService extends BaseService<FundUserRelateModel>  {

    void insertRelate(String relateId,List<FundUserRelateModel> dtoList,String loginUserId )throws Exception;;

    Map<String, Object>  selectFundlist(String userId);
}