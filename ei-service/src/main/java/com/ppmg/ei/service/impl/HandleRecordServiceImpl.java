package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.HandleRecordModel;
import com.ppmg.ei.service.HandleRecordService;

@Component("handleRecordService")
@Service(interfaceClass = HandleRecordService.class)
public class HandleRecordServiceImpl extends BaseServiceImpl<HandleRecordModel> implements HandleRecordService {

	public HandleRecordServiceImpl(){
		this.setNamespace("HandleRecord");
	}

}