package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.CommTNewModel;

import java.util.List;

public interface CommTNewService extends BaseService<CommTNewModel>  {

    List<CommTNewModel> selectListById(String id);

}