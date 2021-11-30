package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundAccountDetailModel;
import com.ppmg.ei.service.FundAccountDetailService;
import org.springframework.stereotype.Component;

@Component("fundAccountDetailService")
@Service(interfaceClass = FundAccountDetailService.class)
public class FundAccountDetailServiceImpl extends BaseServiceImpl<FundAccountDetailModel> implements FundAccountDetailService {

	public FundAccountDetailServiceImpl(){
		this.setNamespace("FundAccountDetail");
	}

}