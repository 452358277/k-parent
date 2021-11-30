package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FmTFileModel;
import com.ppmg.ei.service.FmTFileService;

@Component("fmTFileService")
@Service(interfaceClass = FmTFileService.class)
public class FmTFileServiceImpl extends BaseServiceImpl<FmTFileModel> implements FmTFileService {

	public FmTFileServiceImpl(){
		this.setNamespace("FmTFile");
	}

}