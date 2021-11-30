package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjContractFileDebtModel;
import com.ppmg.ei.service.ProjContractFileDebtService;

@Component("projContractFileDebtService")
@Service(interfaceClass = ProjContractFileDebtService.class)
public class ProjContractFileDebtServiceImpl extends BaseServiceImpl<ProjContractFileDebtModel> implements ProjContractFileDebtService {

	public ProjContractFileDebtServiceImpl(){
		this.setNamespace("ProjContractFileDebt");
	}

}