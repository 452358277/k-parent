package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AssInfoModel;
import com.ppmg.ei.service.AssInfoService;

@Component("assInfoService")
@Service(interfaceClass = AssInfoService.class)
public class AssInfoServiceImpl extends BaseServiceImpl<AssInfoModel> implements AssInfoService {

	public AssInfoServiceImpl(){
		this.setNamespace("AssInfo");
	}

}