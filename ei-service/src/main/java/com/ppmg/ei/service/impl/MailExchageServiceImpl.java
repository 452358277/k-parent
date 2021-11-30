package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.MailExchageModel;
import com.ppmg.ei.service.MailExchageService;

@Component("mailExchageService")
@Service(interfaceClass = MailExchageService.class)
public class MailExchageServiceImpl extends BaseServiceImpl<MailExchageModel> implements MailExchageService {

	public MailExchageServiceImpl(){
		this.setNamespace("MailExchage");
	}

}