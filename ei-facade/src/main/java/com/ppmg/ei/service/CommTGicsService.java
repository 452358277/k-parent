package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.CommTGicsModel;

import java.util.List;
import java.util.Map;

public interface CommTGicsService extends BaseService<CommTGicsModel>  {

    List<Map<String,Object>> getGicsList(Map<String, Object> params);

    Map<String,Object> selectByGisName(String id);

}