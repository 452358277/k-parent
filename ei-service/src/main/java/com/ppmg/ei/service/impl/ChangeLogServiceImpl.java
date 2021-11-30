package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ChangeLogModel;
import com.ppmg.ei.service.ChangeLogService;

@Component("changeLogService")
@Service(interfaceClass = ChangeLogService.class)
public class ChangeLogServiceImpl extends BaseServiceImpl<ChangeLogModel> implements ChangeLogService {

	public ChangeLogServiceImpl(){
		this.setNamespace("ChangeLog");
	}

}