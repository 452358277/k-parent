package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.DecisionInfoModel;
import com.ppmg.ei.service.DecisionInfoService;

@Component("decisionInfoService")
@Service(interfaceClass = DecisionInfoService.class)
public class DecisionInfoServiceImpl extends BaseServiceImpl<DecisionInfoModel> implements DecisionInfoService {

	public DecisionInfoServiceImpl(){
		this.setNamespace("DecisionInfo");
	}

}