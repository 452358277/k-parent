package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjAssignModel;

public interface ProjAssignService extends BaseService<ProjAssignModel>  {

    void updateByNo(String no);

    Integer  selectOneNo(String no);

}