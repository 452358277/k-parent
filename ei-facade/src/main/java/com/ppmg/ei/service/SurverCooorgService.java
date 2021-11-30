package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.SurverCooorgModel;

import java.util.List;

public interface SurverCooorgService extends BaseService<SurverCooorgModel>  {

    List<SurverCooorgModel> selectListById(SurverCooorgModel surverCooorgModel);

}