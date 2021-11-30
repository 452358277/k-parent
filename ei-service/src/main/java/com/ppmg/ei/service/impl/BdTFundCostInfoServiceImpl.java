package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BdTFundCostInfoModel;
import com.ppmg.ei.service.BdTFundCostInfoService;

import javax.annotation.Resource;

@Component("bdTFundCostInfoService")
@Service(interfaceClass = BdTFundCostInfoService.class)
public class BdTFundCostInfoServiceImpl extends BaseServiceImpl<BdTFundCostInfoModel> implements BdTFundCostInfoService {

	public BdTFundCostInfoServiceImpl(){
		this.setNamespace("BdTFundCostInfo");
	}

	@Resource
	private BdTFundCostInfoService bdTFundCostInfoService;

}