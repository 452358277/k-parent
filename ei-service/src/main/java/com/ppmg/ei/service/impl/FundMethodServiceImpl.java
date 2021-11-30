package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundMethodModel;
import com.ppmg.ei.service.FundMethodService;
import org.springframework.stereotype.Component;

@Component("fundMethodService")
@Service(interfaceClass = FundMethodService.class)
public class FundMethodServiceImpl extends BaseServiceImpl<FundMethodModel> implements FundMethodService {

	public FundMethodServiceImpl(){
		this.setNamespace("FundMethod");
	}

}