package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminFundModel;
import com.ppmg.ei.service.AdminFundService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminFundService")
@Service(interfaceClass = AdminFundService.class)
public class AdminFundServiceImpl extends BaseServiceImpl<AdminFundModel> implements AdminFundService {

	public AdminFundServiceImpl(){
		this.setNamespace("AdminFund");
	}

}