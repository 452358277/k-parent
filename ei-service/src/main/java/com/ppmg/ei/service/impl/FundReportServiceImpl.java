package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundReportModel;
import com.ppmg.ei.service.FundReportService;
import org.springframework.stereotype.Component;

@Component("fundReportService")
@Service(interfaceClass = FundReportService.class)
public class FundReportServiceImpl extends BaseServiceImpl<FundReportModel> implements FundReportService {

	public FundReportServiceImpl(){
		this.setNamespace("FundReport");
	}

}