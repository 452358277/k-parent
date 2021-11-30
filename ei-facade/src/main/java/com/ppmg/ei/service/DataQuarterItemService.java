package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.DataQuarterItemModel;

public interface DataQuarterItemService extends BaseService<DataQuarterItemModel>  {

    <E> PageInfo<E> selectListMcPage(SearchCondition var1);

}