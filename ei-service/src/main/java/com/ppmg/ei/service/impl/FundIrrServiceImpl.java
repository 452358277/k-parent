package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundIrrModel;
import com.ppmg.ei.service.FundIrrService;

@Component("fundIrrService")
@Service(interfaceClass = FundIrrService.class)
public class FundIrrServiceImpl extends BaseServiceImpl<FundIrrModel> implements FundIrrService {

	public FundIrrServiceImpl(){
		this.setNamespace("FundIrr");
	}

}