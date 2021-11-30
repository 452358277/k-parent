package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ImportViewModel;
import com.ppmg.ei.service.ImportViewService;

@Component("importViewService")
@Service(interfaceClass = ImportViewService.class)
public class ImportViewServiceImpl extends BaseServiceImpl<ImportViewModel> implements ImportViewService {

	public ImportViewServiceImpl(){
		this.setNamespace("ImportView");
	}

}