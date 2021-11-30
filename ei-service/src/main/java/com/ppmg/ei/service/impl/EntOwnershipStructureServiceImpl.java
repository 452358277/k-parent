package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntOwnershipStructureModel;
import com.ppmg.ei.service.EntOwnershipStructureService;

@Component("entOwnershipStructureService")
@Service(interfaceClass = EntOwnershipStructureService.class)
public class EntOwnershipStructureServiceImpl extends BaseServiceImpl<EntOwnershipStructureModel> implements EntOwnershipStructureService {

	public EntOwnershipStructureServiceImpl(){
		this.setNamespace("EntOwnershipStructure");
	}

}