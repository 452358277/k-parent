package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.YhStaffForDecisionModel;
import com.ppmg.ei.service.YhStaffForDecisionService;

@Component("yhStaffForDecisionService")
@Service(interfaceClass = YhStaffForDecisionService.class)
public class YhStaffForDecisionServiceImpl extends BaseServiceImpl<YhStaffForDecisionModel> implements YhStaffForDecisionService {

	public YhStaffForDecisionServiceImpl(){
		this.setNamespace("YhStaffForDecision");
	}

}