package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EnteFinanceReportModel;
import com.ppmg.ei.service.EnteFinanceReportService;
import org.springframework.stereotype.Component;

@Component("enteFinanceReportService")
@Service(interfaceClass = EnteFinanceReportService.class)
public class EnteFinanceReportServiceImpl extends BaseServiceImpl<EnteFinanceReportModel> implements EnteFinanceReportService {

	public EnteFinanceReportServiceImpl(){
		this.setNamespace("EnteFinanceReport");
	}

}