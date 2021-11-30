package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminWorkModel;
import com.ppmg.ei.service.AdminWorkService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminWorkService")
@Service(interfaceClass = AdminWorkService.class)
public class AdminWorkServiceImpl extends BaseServiceImpl<AdminWorkModel> implements AdminWorkService {

	public AdminWorkServiceImpl(){
		this.setNamespace("AdminWork");
	}

}