package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.OtherThreeMeetingModel;
import com.ppmg.ei.service.OtherThreeMeetingService;

@Component("otherThreeMeetingService")
@Service(interfaceClass = OtherThreeMeetingService.class)
public class OtherThreeMeetingServiceImpl extends BaseServiceImpl<OtherThreeMeetingModel> implements OtherThreeMeetingService {

	public OtherThreeMeetingServiceImpl(){
		this.setNamespace("OtherThreeMeeting");
	}

}