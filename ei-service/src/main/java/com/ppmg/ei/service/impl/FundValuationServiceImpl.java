package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundValuationModel;
import com.ppmg.ei.service.FundValuationService;

@Component("fundValuationService")
@Service(interfaceClass = FundValuationService.class)
public class FundValuationServiceImpl extends BaseServiceImpl<FundValuationModel> implements FundValuationService {

	public FundValuationServiceImpl(){
		this.setNamespace("FundValuation");
	}

}