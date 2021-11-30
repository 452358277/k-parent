package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FitRegulationYelpModel;
import com.ppmg.ei.service.FitRegulationYelpService;
import org.springframework.stereotype.Component;

@Component("fitRegulationYelpService")
@Service(interfaceClass = FitRegulationYelpService.class)
public class FitRegulationYelpServiceImpl extends BaseServiceImpl<FitRegulationYelpModel> implements FitRegulationYelpService {

	public FitRegulationYelpServiceImpl(){
		this.setNamespace("FitRegulationYelp");
	}

}