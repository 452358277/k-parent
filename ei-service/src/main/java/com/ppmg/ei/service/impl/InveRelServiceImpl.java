package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.InveRelModel;
import com.ppmg.ei.service.InveRelService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-09-04 15:42 
 */ 
@Component("inveRelService")
@Service(interfaceClass = InveRelService.class)
public class InveRelServiceImpl extends BaseServiceImpl<InveRelModel> implements InveRelService {

	public InveRelServiceImpl(){
		this.setNamespace("InveRel");
	}

}