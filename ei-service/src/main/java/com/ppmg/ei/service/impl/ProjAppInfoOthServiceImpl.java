package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjAppInfoOthModel;
import com.ppmg.ei.service.ProjAppInfoOthService;

@Component("projAppInfoOthService")
@Service(interfaceClass = ProjAppInfoOthService.class)
public class ProjAppInfoOthServiceImpl extends BaseServiceImpl<ProjAppInfoOthModel> implements ProjAppInfoOthService {

	public ProjAppInfoOthServiceImpl(){
		this.setNamespace("ProjAppInfoOth");
	}

}