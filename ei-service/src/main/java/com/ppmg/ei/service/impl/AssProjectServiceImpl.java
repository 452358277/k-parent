package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AssProjectModel;
import com.ppmg.ei.service.AssProjectService;

@Component("assProjectService")
@Service(interfaceClass = AssProjectService.class)
public class AssProjectServiceImpl extends BaseServiceImpl<AssProjectModel> implements AssProjectService {

	public AssProjectServiceImpl(){
		this.setNamespace("AssProject");
	}

}