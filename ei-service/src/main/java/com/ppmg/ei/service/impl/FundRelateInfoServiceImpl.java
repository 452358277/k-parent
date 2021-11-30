package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundRelateInfoModel;
import com.ppmg.ei.service.FundRelateInfoService;
import org.springframework.stereotype.Component;

@Component("fundRelateInfoService")
@Service(interfaceClass = FundRelateInfoService.class)
public class FundRelateInfoServiceImpl extends BaseServiceImpl<FundRelateInfoModel> implements FundRelateInfoService {

	public FundRelateInfoServiceImpl(){
		this.setNamespace("FundRelateInfo");
	}

}