package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.EntLogoModel;

import java.util.Map;

public interface EntLogoService extends BaseService<EntLogoModel>  {

    public EntLogoModel getLogoByEntId(Map<String, Object> params);
}