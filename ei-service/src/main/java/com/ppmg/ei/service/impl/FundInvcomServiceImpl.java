package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInvcomModel;
import com.ppmg.ei.service.FundInvcomService;

import java.util.List;

@Component("fundInvcomService")
@Service(interfaceClass = FundInvcomService.class)
public class FundInvcomServiceImpl extends BaseServiceImpl<FundInvcomModel> implements FundInvcomService {

	public FundInvcomServiceImpl(){
		this.setNamespace("FundInvcom");
	}

	@Override
	public List<FundInvcomModel> getFundInvcomInfoByFundId(String fundId) {
		return this.selectList("getFundInvcomInfoByFundId", fundId);

	}
}