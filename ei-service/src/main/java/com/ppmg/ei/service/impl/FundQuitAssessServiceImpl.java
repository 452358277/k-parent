package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundQuitAssessModel;
import com.ppmg.ei.service.FundQuitAssessService;

@Component("fundQuitAssessService")
@Service(interfaceClass = FundQuitAssessService.class)
public class FundQuitAssessServiceImpl extends BaseServiceImpl<FundQuitAssessModel> implements FundQuitAssessService {

	public FundQuitAssessServiceImpl(){
		this.setNamespace("FundQuitAssess");
	}

}