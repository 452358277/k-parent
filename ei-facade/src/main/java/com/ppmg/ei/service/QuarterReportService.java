package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.QuarterReportModel;

public interface QuarterReportService extends BaseService<QuarterReportModel>  {

    <E> PageInfo<E> selectQuarterReportPage(SearchCondition var1);

    void insertQuarterReport(QuarterReportModel quarterReportModel,String userId);

}