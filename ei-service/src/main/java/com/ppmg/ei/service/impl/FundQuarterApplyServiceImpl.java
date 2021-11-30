package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundQuarterApplyModel;
import com.ppmg.ei.service.FundQuarterApplyService;
import org.springframework.stereotype.Component;

@Component("fundQuarterApplyService")
@Service(interfaceClass = FundQuarterApplyService.class)
public class FundQuarterApplyServiceImpl extends BaseServiceImpl<FundQuarterApplyModel> implements FundQuarterApplyService {

	public FundQuarterApplyServiceImpl(){
		this.setNamespace("FundQuarterApply");
	}

}