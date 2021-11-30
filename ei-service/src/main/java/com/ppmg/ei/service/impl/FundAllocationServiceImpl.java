package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundAllocationModel;
import com.ppmg.ei.service.FundAllocationService;
import org.springframework.stereotype.Component;

@Component("fundAllocationService")
@Service(interfaceClass = FundAllocationService.class)
public class FundAllocationServiceImpl extends BaseServiceImpl<FundAllocationModel> implements FundAllocationService {

	public FundAllocationServiceImpl(){
		this.setNamespace("FundAllocation");
	}

}