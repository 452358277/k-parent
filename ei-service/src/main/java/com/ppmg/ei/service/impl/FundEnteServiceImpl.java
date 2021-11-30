package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundEnteModel;
import com.ppmg.ei.service.FundEnteService;
import org.springframework.stereotype.Component;

@Component("fundEnteService")
@Service(interfaceClass = FundEnteService.class)
public class FundEnteServiceImpl extends BaseServiceImpl<FundEnteModel> implements FundEnteService {

	public FundEnteServiceImpl(){
		this.setNamespace("FundEnte");
	}

}