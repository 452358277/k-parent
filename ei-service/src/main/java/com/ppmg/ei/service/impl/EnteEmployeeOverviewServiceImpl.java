package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteEmployeeOverviewModel;
import com.ppmg.ei.service.EnteEmployeeOverviewService;

@Component("enteEmployeeOverviewService")
@Service(interfaceClass = EnteEmployeeOverviewService.class)
public class EnteEmployeeOverviewServiceImpl extends BaseServiceImpl<EnteEmployeeOverviewModel> implements EnteEmployeeOverviewService {

	public EnteEmployeeOverviewServiceImpl(){
		this.setNamespace("EnteEmployeeOverview");
	}

}