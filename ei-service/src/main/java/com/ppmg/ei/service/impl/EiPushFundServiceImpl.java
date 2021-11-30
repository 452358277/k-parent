package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EiPushFundModel;
import com.ppmg.ei.service.EiPushFundService;

@Component("eiPushFundService")
@Service(interfaceClass = EiPushFundService.class)
public class EiPushFundServiceImpl extends BaseServiceImpl<EiPushFundModel> implements EiPushFundService {

	public EiPushFundServiceImpl(){
		this.setNamespace("EiPushFund");
	}

}