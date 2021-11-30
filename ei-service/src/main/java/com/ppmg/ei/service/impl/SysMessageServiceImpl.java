package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.SysMessageModel;
import com.ppmg.ei.service.SysMessageService;

@Component("sysMessageService")
@Service(interfaceClass = SysMessageService.class)
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessageModel> implements SysMessageService {

	public SysMessageServiceImpl(){
		this.setNamespace("SysMessage");
	}

}