package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.QuotaInfoModel;
import com.ppmg.ei.service.QuotaInfoService;

@Component("quotaInfoService")
@Service(interfaceClass = QuotaInfoService.class)
public class QuotaInfoServiceImpl extends BaseServiceImpl<QuotaInfoModel> implements QuotaInfoService {

	public QuotaInfoServiceImpl(){
		this.setNamespace("QuotaInfo");
	}

}