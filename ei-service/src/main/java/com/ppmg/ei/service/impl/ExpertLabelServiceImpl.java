package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ExpertLabelModel;
import com.ppmg.ei.service.ExpertLabelService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 
@Component("expertLabelService")
@Service(interfaceClass = ExpertLabelService.class)
public class ExpertLabelServiceImpl extends BaseServiceImpl<ExpertLabelModel> implements ExpertLabelService {

	public ExpertLabelServiceImpl(){
		this.setNamespace("ExpertLabel");
	}

}