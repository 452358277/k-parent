package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FounderFileInfoModel;
import com.ppmg.ei.service.FounderFileInfoService;

@Component("founderFileInfoService")
@Service(interfaceClass = FounderFileInfoService.class)
public class FounderFileInfoServiceImpl extends BaseServiceImpl<FounderFileInfoModel> implements FounderFileInfoService {

	public FounderFileInfoServiceImpl(){
		this.setNamespace("FounderFileInfo");
	}

}