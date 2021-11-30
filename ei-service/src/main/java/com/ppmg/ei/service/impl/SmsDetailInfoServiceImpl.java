package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.SmsDetailInfoModel;
import com.ppmg.ei.service.SmsDetailInfoService;
import org.springframework.stereotype.Component;

@Component("smsDetailInfoService")
@Service(interfaceClass = SmsDetailInfoService.class)
public class SmsDetailInfoServiceImpl extends BaseServiceImpl<SmsDetailInfoModel> implements SmsDetailInfoService {

	public SmsDetailInfoServiceImpl(){
		this.setNamespace("SmsDetailInfo");
	}

}