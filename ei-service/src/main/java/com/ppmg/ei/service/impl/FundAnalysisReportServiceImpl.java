package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundAnalysisReportModel;
import com.ppmg.ei.service.FundAnalysisReportService;

@Component("fundAnalysisReportService")
@Service(interfaceClass = FundAnalysisReportService.class)
public class FundAnalysisReportServiceImpl extends BaseServiceImpl<FundAnalysisReportModel> implements FundAnalysisReportService {

	public FundAnalysisReportServiceImpl(){
		this.setNamespace("FundAnalysisReport");
	}

}