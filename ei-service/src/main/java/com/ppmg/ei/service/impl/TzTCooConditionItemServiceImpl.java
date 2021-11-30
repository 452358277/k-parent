package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TzTCooConditionItemModel;
import com.ppmg.ei.service.TzTCooConditionItemService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("tzTCooConditionItemService")
@Service(interfaceClass = TzTCooConditionItemService.class)
public class TzTCooConditionItemServiceImpl extends BaseServiceImpl<TzTCooConditionItemModel> implements TzTCooConditionItemService {

	public TzTCooConditionItemServiceImpl(){
		this.setNamespace("TzTCooConditionItem");
	}

}