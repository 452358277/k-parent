package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.InveResumeModel;
import com.ppmg.ei.service.InveResumeService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-09-04 15:50 
 */
@Component("inveResumeService")
@Service(interfaceClass = InveResumeService.class)
public class InveResumeServiceImpl extends BaseServiceImpl<InveResumeModel> implements InveResumeService {

	public InveResumeServiceImpl(){
		this.setNamespace("InveResume");
	}

}