package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.InveGroupModel;
import com.ppmg.ei.service.InveGroupService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-09-04 16:20 
 */ 
@Component("inveGroupService")
@Service(interfaceClass = InveGroupService.class)
public class InveGroupServiceImpl extends BaseServiceImpl<InveGroupModel> implements InveGroupService {

	public InveGroupServiceImpl(){
		this.setNamespace("InveGroup");
	}

}