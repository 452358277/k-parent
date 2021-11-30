package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundRecordInfoModel;
import com.ppmg.ei.service.FundRecordInfoService;

@Component("fundRecordInfoService")
@Service(interfaceClass = FundRecordInfoService.class)
public class FundRecordInfoServiceImpl extends BaseServiceImpl<FundRecordInfoModel> implements FundRecordInfoService {

	public FundRecordInfoServiceImpl(){
		this.setNamespace("FundRecordInfo");
	}

}