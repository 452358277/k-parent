package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminCoreModel;
import com.ppmg.ei.service.AdminCoreService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminCoreService")
@Service(interfaceClass = AdminCoreService.class)
public class AdminCoreServiceImpl extends BaseServiceImpl<AdminCoreModel> implements AdminCoreService {

	public AdminCoreServiceImpl(){
		this.setNamespace("AdminCore");
	}

}