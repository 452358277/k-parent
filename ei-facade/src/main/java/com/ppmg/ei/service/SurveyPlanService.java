package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.model.SurveyPlanModel;
import com.ppmg.ei.model.SurveyPlanUsersModel;

import java.util.List;

public interface SurveyPlanService extends BaseService<SurveyPlanModel>  {

   void insertUser(SurveyPlanModel model, List<SurveyPlanUsersModel> ids) throws Exception;

   void updateByUser(SurveyPlanModel model, List<SurveyPlanUsersModel> ids) throws Exception ;

    void insertInfo(SurveyPlanModel model, List<SurveyPlanUsersModel> ids,List<SurverCooorgModel> list)throws Exception;

   void updateInfo(SurveyPlanModel model, List<SurveyPlanUsersModel> ids, List<SurverCooorgModel> list)throws Exception;

    void deleteByIds(List<String> ids, String userId);

}