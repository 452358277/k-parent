package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EiMyHandleModel;
import com.ppmg.ei.service.EiMyHandleService;

@Component("eiMyHandleService")
@Service(interfaceClass = EiMyHandleService.class)
public class EiMyHandleServiceImpl extends BaseServiceImpl<EiMyHandleModel> implements EiMyHandleService {

	public EiMyHandleServiceImpl(){
		this.setNamespace("EiMyHandle");
	}

}