package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ScParamModel;
import com.ppmg.ei.service.ScParamService;

@Component("scParamService")
@Service(interfaceClass = ScParamService.class)
public class ScParamServiceImpl extends BaseServiceImpl<ScParamModel> implements ScParamService {

	public ScParamServiceImpl(){
		this.setNamespace("ScParam");
	}


}