package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjReceiptQuitModel;
import com.ppmg.ei.service.ProjReceiptQuitService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-10-15 16:17 
 */ 
@Component("projReceiptQuitService")
@Service(interfaceClass = ProjReceiptQuitService.class)
public class ProjReceiptQuitServiceImpl extends BaseServiceImpl<ProjReceiptQuitModel> implements ProjReceiptQuitService {

	public ProjReceiptQuitServiceImpl(){
		this.setNamespace("ProjReceiptQuit");
	}

	@Override
	public Double getSumAmount(String projId) {
		return (Double)this.selectOne("getSumAmount",projId);
	}
}