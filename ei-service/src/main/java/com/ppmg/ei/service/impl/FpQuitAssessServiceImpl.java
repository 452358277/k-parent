package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FpQuitAssessModel;
import com.ppmg.ei.service.FpQuitAssessService;

@Component("fpQuitAssessService")
@Service(interfaceClass = FpQuitAssessService.class)
public class FpQuitAssessServiceImpl extends BaseServiceImpl<FpQuitAssessModel> implements FpQuitAssessService {

	public FpQuitAssessServiceImpl(){
		this.setNamespace("FpQuitAssess");
	}

}