package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TzTCooConditionModel;
import com.ppmg.ei.service.TzTCooConditionService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("tzTCooConditionService")
@Service(interfaceClass = TzTCooConditionService.class)
public class TzTCooConditionServiceImpl extends BaseServiceImpl<TzTCooConditionModel> implements TzTCooConditionService {

	public TzTCooConditionServiceImpl(){
		this.setNamespace("TzTCooCondition");
	}

}