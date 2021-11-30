package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.model.UserModel;

import java.util.List;

/**
 * 基金管理人 [Service]
 * @author yuyongjun
 * @date 2019-06-25 09:21
 */ 
public interface FundMcInfoService extends BaseService<FundMcInfoModel>  {

    List<FundMcInfoModel> selectByFundId();

    FundMcInfoModel selectByUserId(String userId);

    <E> PageInfo<E> selectListPage1(SearchCondition var1);

    public void insertById(UserModel user,FundMcInfoModel model)throws Exception;


}