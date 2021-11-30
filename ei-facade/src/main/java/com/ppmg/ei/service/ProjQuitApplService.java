package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjQuitApplModel;

public interface ProjQuitApplService extends BaseService<ProjQuitApplModel>  {

    public Double seleSumRevenue(String projId);

}