package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntInvestmentModel;
import com.ppmg.ei.service.EntInvestmentService;

@Component("entInvestmentService")
@Service(interfaceClass = EntInvestmentService.class)
public class EntInvestmentServiceImpl extends BaseServiceImpl<EntInvestmentModel> implements EntInvestmentService {

	public EntInvestmentServiceImpl(){
		this.setNamespace("EntInvestment");
	}

}