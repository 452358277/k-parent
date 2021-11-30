package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntLabelModel;
import com.ppmg.ei.service.EntLabelService;

import java.util.List;

@Component("entLabelService")
@Service(interfaceClass = EntLabelService.class)
public class EntLabelServiceImpl extends BaseServiceImpl<EntLabelModel> implements EntLabelService {

	public EntLabelServiceImpl(){
		this.setNamespace("EntLabel");
	}


}