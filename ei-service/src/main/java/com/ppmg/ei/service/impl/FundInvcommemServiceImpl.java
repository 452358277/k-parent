package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundInvcommemModel;
import com.ppmg.ei.service.FundInvcommemService;

@Component("fundInvcommemService")
@Service(interfaceClass = FundInvcommemService.class)
public class FundInvcommemServiceImpl extends BaseServiceImpl<FundInvcommemModel> implements FundInvcommemService {

	public FundInvcommemServiceImpl(){
		this.setNamespace("FundInvcommem");
	}

}