package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjectProgressModel;

public interface ProjectProgressService extends BaseService<ProjectProgressModel>  {

    void insertProessAndMessage(ProjectProgressModel model, String allUserIds, String allUserNames , String userId, String name,String dealMark,String dist,String projStatus) throws Exception ;

    void updateProessAndMessage(ProjectProgressModel model, String allUserIds, String allUserNames , String userId, String name,String dealMark,String dist,String projStatus) throws Exception ;

}