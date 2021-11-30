package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminPartnerModel;
import com.ppmg.ei.service.AdminPartnerService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminPartnerService")
@Service(interfaceClass = AdminPartnerService.class)
public class AdminPartnerServiceImpl extends BaseServiceImpl<AdminPartnerModel> implements AdminPartnerService {

	public AdminPartnerServiceImpl(){
		this.setNamespace("AdminPartner");
	}

}