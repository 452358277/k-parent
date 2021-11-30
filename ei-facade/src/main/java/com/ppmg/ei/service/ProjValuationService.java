package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjRegularAssessModel;
import com.ppmg.ei.model.ProjValuationModel;

public interface ProjValuationService extends BaseService<ProjValuationModel>  {

    void insertByProjValuation(String userId, ProjValuationModel model, ProjRegularAssessModel projRegularAssessModel)throws Exception;

     void deleteByvalue(String id)throws Exception;

    <E> PageInfo<E> selectListEntPage(SearchCondition var1);
}