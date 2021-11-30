package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInveDescModel;
import com.ppmg.ei.service.FundInveDescService;

@Component("fundInveDescService")
@Service(interfaceClass = FundInveDescService.class)
public class FundInveDescServiceImpl extends BaseServiceImpl<FundInveDescModel> implements FundInveDescService {

	public FundInveDescServiceImpl(){
		this.setNamespace("FundInveDesc");
	}

}