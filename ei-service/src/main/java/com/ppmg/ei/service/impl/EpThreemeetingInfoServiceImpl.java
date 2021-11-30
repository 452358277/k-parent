package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EpThreemeetingInfoModel;
import com.ppmg.ei.service.EpThreemeetingInfoService;

@Component("epThreemeetingInfoService")
@Service(interfaceClass = EpThreemeetingInfoService.class)
public class EpThreemeetingInfoServiceImpl extends BaseServiceImpl<EpThreemeetingInfoModel> implements EpThreemeetingInfoService {

	public EpThreemeetingInfoServiceImpl(){
		this.setNamespace("EpThreemeetingInfo");
	}

}