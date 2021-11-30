package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TzTCooConditionFileModel;
import com.ppmg.ei.service.TzTCooConditionFileService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("tzTCooConditionFileService")
@Service(interfaceClass = TzTCooConditionFileService.class)
public class TzTCooConditionFileServiceImpl extends BaseServiceImpl<TzTCooConditionFileModel> implements TzTCooConditionFileService {

	public TzTCooConditionFileServiceImpl(){
		this.setNamespace("TzTCooConditionFile");
	}

}