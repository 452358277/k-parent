package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteMFinaInfoModel;
import com.ppmg.ei.service.EnteMFinaInfoService;

@Component("enteMFinaInfoService")
@Service(interfaceClass = EnteMFinaInfoService.class)
public class EnteMFinaInfoServiceImpl extends BaseServiceImpl<EnteMFinaInfoModel> implements EnteMFinaInfoService {

	public EnteMFinaInfoServiceImpl(){
		this.setNamespace("EnteMFinaInfo");
	}

}