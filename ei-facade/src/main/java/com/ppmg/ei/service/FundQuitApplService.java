package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundQuitApplModel;

public interface FundQuitApplService extends BaseService<FundQuitApplModel>  {

   <E> PageInfo<E> fundQuitApplListPage(SearchCondition var1);

   void insertFundQuitAppl (FundQuitApplModel fundQuitApplModel);

   void updateFundQuitAppl(FundQuitApplModel fundQuitApplModel);

   <E> PageInfo<E> selectListInfoPage(SearchCondition var1);

}