package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FundShareItemModel;

import java.util.List;

public interface FundShareItemService extends BaseService<FundShareItemModel>  {

    List<FundShareItemModel> selectItemList(String pkId);

    List<FundShareItemModel>  selectByFundIdList(String fundId);

    Double selectSumDuesAmount(String fundId);

    Double selectByCloseRound(FundShareItemModel fundShareItemModel);

    Double selectByFundCloseRound(FundShareItemModel fundShareItemModel);

    Double selectBySum(String pkId);

    Double selectByCR(FundShareItemModel fundShareItemModel);

    Double selectByLastCR(FundShareItemModel fundShareItemModel);

    FundShareItemModel selectByC(FundShareItemModel base);

    Double selectSumGovDuesAmount(String fundId);

   void  updateByIds(List<FundShareItemModel> dtoList,String userId) throws Exception;

    Double selectBySumPay(String fundId);
}