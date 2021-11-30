package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.QuotaConfInfoModel;
import com.ppmg.ei.service.QuotaConfInfoService;

@Component("quotaConfInfoService")
@Service(interfaceClass = QuotaConfInfoService.class)
public class QuotaConfInfoServiceImpl extends BaseServiceImpl<QuotaConfInfoModel> implements QuotaConfInfoService {

	public QuotaConfInfoServiceImpl(){
		this.setNamespace("QuotaConfInfo");
	}

}