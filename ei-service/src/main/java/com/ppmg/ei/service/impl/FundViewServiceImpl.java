package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundViewModel;
import com.ppmg.ei.service.FundViewService;
import org.springframework.stereotype.Component;

@Component("fundViewService")
@Service(interfaceClass = FundViewService.class)
public class FundViewServiceImpl extends BaseServiceImpl<FundViewModel> implements FundViewService {

	public FundViewServiceImpl(){
		this.setNamespace("FundView");
	}

}