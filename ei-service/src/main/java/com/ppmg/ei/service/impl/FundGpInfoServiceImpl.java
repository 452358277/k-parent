package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundGpInfoModel;
import com.ppmg.ei.service.FundGpInfoService;

@Component("fundGpInfoService")
@Service(interfaceClass = FundGpInfoService.class)
public class FundGpInfoServiceImpl extends BaseServiceImpl<FundGpInfoModel> implements FundGpInfoService {

	public FundGpInfoServiceImpl(){
		this.setNamespace("FundGpInfo");
	}

}