package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInvePhaseModel;
import com.ppmg.ei.service.FundInvePhaseService;
import org.springframework.stereotype.Component;

@Component("fundInvePhaseService")
@Service(interfaceClass = FundInvePhaseService.class)
public class FundInvePhaseServiceImpl extends BaseServiceImpl<FundInvePhaseModel> implements FundInvePhaseService {

	public FundInvePhaseServiceImpl(){
		this.setNamespace("FundInvePhase");
	}

}