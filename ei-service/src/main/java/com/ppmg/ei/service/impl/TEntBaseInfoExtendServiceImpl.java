package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TEntBaseInfoExtendModel;
import com.ppmg.ei.service.TEntBaseInfoExtendService;
import org.springframework.stereotype.Component;

@Component("tEntBaseInfoExtendService")
@Service(interfaceClass = TEntBaseInfoExtendService.class)
public class TEntBaseInfoExtendServiceImpl extends BaseServiceImpl<TEntBaseInfoExtendModel> implements TEntBaseInfoExtendService {

	public TEntBaseInfoExtendServiceImpl(){
		this.setNamespace("TEntBaseInfoExtend");
	}

}