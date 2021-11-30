package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteLiabilitiesInfoModel;
import com.ppmg.ei.service.EnteLiabilitiesInfoService;
import org.springframework.stereotype.Component;

@Component("enteLiabilitiesInfoService")
@Service(interfaceClass = EnteLiabilitiesInfoService.class)
public class EnteLiabilitiesInfoServiceImpl extends BaseServiceImpl<EnteLiabilitiesInfoModel> implements EnteLiabilitiesInfoService {

	public EnteLiabilitiesInfoServiceImpl(){
		this.setNamespace("EnteLiabilitiesInfo");
	}

}