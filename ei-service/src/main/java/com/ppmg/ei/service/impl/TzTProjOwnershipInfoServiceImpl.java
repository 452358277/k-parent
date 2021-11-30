package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TzTProjOwnershipInfoModel;
import com.ppmg.ei.service.TzTProjOwnershipInfoService;
import org.springframework.stereotype.Component;

@Component("tzTProjOwnershipInfoService")
@Service(interfaceClass = TzTProjOwnershipInfoService.class)
public class TzTProjOwnershipInfoServiceImpl extends BaseServiceImpl<TzTProjOwnershipInfoModel> implements TzTProjOwnershipInfoService {

	public TzTProjOwnershipInfoServiceImpl(){
		this.setNamespace("TzTProjOwnershipInfo");
	}

}