package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteBasicInfoModel;
import com.ppmg.ei.service.EnteBasicInfoService;
import org.springframework.stereotype.Component;

@Component("enteBasicInfoService")
@Service(interfaceClass = EnteBasicInfoService.class)
public class EnteBasicInfoServiceImpl extends BaseServiceImpl<EnteBasicInfoModel> implements EnteBasicInfoService {

	public EnteBasicInfoServiceImpl(){
		this.setNamespace("EnteBasicInfo");
	}

}