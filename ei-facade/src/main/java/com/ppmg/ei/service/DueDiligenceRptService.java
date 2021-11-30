package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.DueDiligenceRptModel;
import com.ppmg.ei.model.SurverCooorgModel;

import java.util.List;

public interface DueDiligenceRptService extends BaseService<DueDiligenceRptModel>  {

   void  insertByDue(DueDiligenceRptModel model,List<SurverCooorgModel> list) throws Exception;

    void  updateByDue(DueDiligenceRptModel model,List<SurverCooorgModel> list) throws Exception;

    void deleteByIds(List<String> ids, String userId);
}