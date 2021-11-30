package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EpValuRptModel;
import com.ppmg.ei.service.EpValuRptService;

@Component("epValuRptService")
@Service(interfaceClass = EpValuRptService.class)
public class EpValuRptServiceImpl extends BaseServiceImpl<EpValuRptModel> implements EpValuRptService {

	public EpValuRptServiceImpl(){
		this.setNamespace("EpValuRpt");
	}

}