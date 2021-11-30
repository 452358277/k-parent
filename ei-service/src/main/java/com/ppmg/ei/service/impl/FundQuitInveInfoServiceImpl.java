package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundQuitInveInfoModel;
import com.ppmg.ei.service.FundQuitInveInfoService;
import org.springframework.stereotype.Component;

@Component("fundQuitInveInfoService")
@Service(interfaceClass = FundQuitInveInfoService.class)
public class FundQuitInveInfoServiceImpl extends BaseServiceImpl<FundQuitInveInfoModel> implements FundQuitInveInfoService {

	public FundQuitInveInfoServiceImpl(){
		this.setNamespace("FundQuitInveInfo");
	}

}