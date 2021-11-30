package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundAccountMapingModel;
import com.ppmg.ei.service.FundAccountMapingService;
import org.springframework.stereotype.Component;

@Component("fundAccountMapingService")
@Service(interfaceClass = FundAccountMapingService.class)
public class FundAccountMapingServiceImpl extends BaseServiceImpl<FundAccountMapingModel> implements FundAccountMapingService {

	public FundAccountMapingServiceImpl(){
		this.setNamespace("FundAccountMaping");
	}

}