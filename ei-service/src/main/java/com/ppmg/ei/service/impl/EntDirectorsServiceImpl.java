package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntDirectorsModel;
import com.ppmg.ei.service.EntDirectorsService;

@Component("entDirectorsService")
@Service(interfaceClass = EntDirectorsService.class)
public class EntDirectorsServiceImpl extends BaseServiceImpl<EntDirectorsModel> implements EntDirectorsService {

	public EntDirectorsServiceImpl(){
		this.setNamespace("EntDirectors");
	}

}