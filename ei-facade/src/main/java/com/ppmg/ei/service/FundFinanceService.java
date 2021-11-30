package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundFinanceModel;

public interface FundFinanceService extends BaseService<FundFinanceModel>  {

    <E> PageInfo<E> selectListYearPage(SearchCondition var1);

    void updateIsNull(FundFinanceModel model);

}