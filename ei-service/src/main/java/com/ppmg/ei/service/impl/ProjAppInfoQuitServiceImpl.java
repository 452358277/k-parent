package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjAppInfoQuitModel;
import com.ppmg.ei.service.ProjAppInfoQuitService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-10-15 16:17 
 */ 
@Component("projAppInfoQuitService")
@Service(interfaceClass = ProjAppInfoQuitService.class)
public class ProjAppInfoQuitServiceImpl extends BaseServiceImpl<ProjAppInfoQuitModel> implements ProjAppInfoQuitService {

	public ProjAppInfoQuitServiceImpl(){
		this.setNamespace("ProjAppInfoQuit");
	}

}