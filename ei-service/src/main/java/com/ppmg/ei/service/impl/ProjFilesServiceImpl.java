package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjFilesModel;
import com.ppmg.ei.service.ProjFilesService;

@Component("projFilesService")
@Service(interfaceClass = ProjFilesService.class)
public class ProjFilesServiceImpl extends BaseServiceImpl<ProjFilesModel> implements ProjFilesService {

	public ProjFilesServiceImpl(){
		this.setNamespace("ProjFiles");
	}

}