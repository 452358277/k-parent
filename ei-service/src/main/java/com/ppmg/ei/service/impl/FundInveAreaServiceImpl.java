package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInveAreaModel;
import com.ppmg.ei.service.FundInveAreaService;
import org.springframework.stereotype.Component;

@Component("fundInveAreaService")
@Service(interfaceClass = FundInveAreaService.class)
public class FundInveAreaServiceImpl extends BaseServiceImpl<FundInveAreaModel> implements FundInveAreaService {

	public FundInveAreaServiceImpl(){
		this.setNamespace("FundInveArea");
	}

}