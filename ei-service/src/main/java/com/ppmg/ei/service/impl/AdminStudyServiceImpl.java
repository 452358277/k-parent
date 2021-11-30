package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminStudyModel;
import com.ppmg.ei.service.AdminStudyService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminStudyService")
@Service(interfaceClass = AdminStudyService.class)
public class AdminStudyServiceImpl extends BaseServiceImpl<AdminStudyModel> implements AdminStudyService {

	public AdminStudyServiceImpl(){
		this.setNamespace("AdminStudy");
	}

}