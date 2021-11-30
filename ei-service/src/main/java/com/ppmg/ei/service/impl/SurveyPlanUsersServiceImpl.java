package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.SurveyPlanUsersModel;
import com.ppmg.ei.service.SurveyPlanUsersService;

@Component("surveyPlanUsersService")
@Service(interfaceClass = SurveyPlanUsersService.class)
public class SurveyPlanUsersServiceImpl extends BaseServiceImpl<SurveyPlanUsersModel> implements SurveyPlanUsersService {

	public SurveyPlanUsersServiceImpl(){
		this.setNamespace("SurveyPlanUsers");
	}

	@Override
	public String selectByPlanUserName(String planId) {
		return (String)this.selectOne("selectByPlanUserName",planId);
	}
}