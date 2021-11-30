package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteAuditInfoModel;
import com.ppmg.ei.service.EnteAuditInfoService;
import org.springframework.stereotype.Component;

@Component("enteAuditInfoService")
@Service(interfaceClass = EnteAuditInfoService.class)
public class EnteAuditInfoServiceImpl extends BaseServiceImpl<EnteAuditInfoModel> implements EnteAuditInfoService {

	public EnteAuditInfoServiceImpl(){
		this.setNamespace("EnteAuditInfo");
	}

}