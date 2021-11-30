package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TzTCooConditionInfoModel;
import com.ppmg.ei.service.TzTCooConditionInfoService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("tzTCooConditionInfoService")
@Service(interfaceClass = TzTCooConditionInfoService.class)
public class TzTCooConditionInfoServiceImpl extends BaseServiceImpl<TzTCooConditionInfoModel> implements TzTCooConditionInfoService {

	public TzTCooConditionInfoServiceImpl(){
		this.setNamespace("TzTCooConditionInfo");
	}

}