package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FounderOaApplySealModel;
import com.ppmg.ei.service.FounderOaApplySealService;

@Component("founderOaApplySealService")
@Service(interfaceClass = FounderOaApplySealService.class)
public class FounderOaApplySealServiceImpl extends BaseServiceImpl<FounderOaApplySealModel> implements FounderOaApplySealService {

	public FounderOaApplySealServiceImpl(){
		this.setNamespace("FounderOaApplySeal");
	}

}