package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ApplyInfoModel;
import com.ppmg.ei.service.ApplyInfoService;

@Component("applyInfoService")
@Service(interfaceClass = ApplyInfoService.class)
public class ApplyInfoServiceImpl extends BaseServiceImpl<ApplyInfoModel> implements ApplyInfoService {

	public ApplyInfoServiceImpl(){
		this.setNamespace("ApplyInfo");
	}

}