package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundDescInfoModel;
import com.ppmg.ei.service.FundDescInfoService;

/** 
 * 描述 [Service.impl] 
 * @author root
 * @date 2019-09-02 18:15 
 */ 
@Component("fundDescInfoService")
@Service(interfaceClass = FundDescInfoService.class)
public class FundDescInfoServiceImpl extends BaseServiceImpl<FundDescInfoModel> implements FundDescInfoService {

	public FundDescInfoServiceImpl(){
		this.setNamespace("FundDescInfo");
	}

}