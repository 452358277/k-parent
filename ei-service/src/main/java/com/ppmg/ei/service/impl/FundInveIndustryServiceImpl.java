package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInveIndustryModel;
import com.ppmg.ei.service.FundInveIndustryService;
import org.springframework.stereotype.Component;

@Component("fundInveIndustryService")
@Service(interfaceClass = FundInveIndustryService.class)
public class FundInveIndustryServiceImpl extends BaseServiceImpl<FundInveIndustryModel> implements FundInveIndustryService {

	public FundInveIndustryServiceImpl(){
		this.setNamespace("FundInveIndustry");
	}

}