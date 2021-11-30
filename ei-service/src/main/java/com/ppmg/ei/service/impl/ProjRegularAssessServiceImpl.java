package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjRegularAssessModel;
import com.ppmg.ei.service.ProjRegularAssessService;

@Component("projRegularAssessService")
@Service(interfaceClass = ProjRegularAssessService.class)
public class ProjRegularAssessServiceImpl extends BaseServiceImpl<ProjRegularAssessModel> implements ProjRegularAssessService {

	public ProjRegularAssessServiceImpl(){
		this.setNamespace("ProjRegularAssess");
	}

}