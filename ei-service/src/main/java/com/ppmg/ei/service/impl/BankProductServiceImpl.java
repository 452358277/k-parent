package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BankProductModel;
import com.ppmg.ei.service.BankProductService;

@Component("bankProductService")
@Service(interfaceClass = BankProductService.class)
public class BankProductServiceImpl extends BaseServiceImpl<BankProductModel> implements BankProductService {

	public BankProductServiceImpl(){
		this.setNamespace("BankProduct");
	}

}