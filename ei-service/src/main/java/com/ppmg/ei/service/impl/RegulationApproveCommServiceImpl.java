package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.RegulationApproveCommModel;
import com.ppmg.ei.service.RegulationApproveCommService;
import org.springframework.stereotype.Component;

@Component("regulationApproveCommService")
@Service(interfaceClass = RegulationApproveCommService.class)
public class RegulationApproveCommServiceImpl extends BaseServiceImpl<RegulationApproveCommModel> implements RegulationApproveCommService {

	public RegulationApproveCommServiceImpl(){
		this.setNamespace("RegulationApproveComm");
	}

}