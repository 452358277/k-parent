package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.FundInfoModel;

import java.util.List;

/** 
 * 描述 [Service] 
 * @author yuyongjun
 * @date 2019-06-25 09:21 
 */ 
public interface BankInfoService extends BaseService<BankInfoModel>  {

    /**
     * 通过用户编号查询银行信息
     * @param userId
     * @return
     */
    BankInfoModel selectByUserId(String userId);


    Integer insertBankInfo(BankInfoModel model,String password,String userId) throws  Exception;


    List<BankInfoModel> getBankList(SearchCondition var1);


    List<FundInfoModel> getFundMcPhoneList(SearchCondition var1);
}