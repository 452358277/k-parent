package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminAssetModel;
import com.ppmg.ei.service.AdminAssetService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Component("adminAssetService")
@Service(interfaceClass = AdminAssetService.class)
public class AdminAssetServiceImpl extends BaseServiceImpl<AdminAssetModel> implements AdminAssetService {

	public AdminAssetServiceImpl(){
		this.setNamespace("AdminAsset");
	}

}