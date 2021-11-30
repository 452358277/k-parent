package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundAccountModel;
import com.ppmg.ei.service.FundAccountService;
import org.springframework.stereotype.Component;

@Component("fundAccountService")
@Service(interfaceClass = FundAccountService.class)
public class FundAccountServiceImpl extends BaseServiceImpl<FundAccountModel> implements FundAccountService {

	public FundAccountServiceImpl(){
		this.setNamespace("FundAccount");
	}

}