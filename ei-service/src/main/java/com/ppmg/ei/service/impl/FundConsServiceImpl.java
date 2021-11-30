package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundConsModel;
import com.ppmg.ei.service.FundConsService;
import org.springframework.stereotype.Component;

@Component("fundConsService")
@Service(interfaceClass = FundConsService.class)
public class FundConsServiceImpl extends BaseServiceImpl<FundConsModel> implements FundConsService {

	public FundConsServiceImpl(){
		this.setNamespace("FundCons");
	}

}