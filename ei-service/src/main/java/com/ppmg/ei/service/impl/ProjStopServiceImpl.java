package com.ppmg.ei.service.impl;

import com.ppmg.ei.model.FpPaymentRequestStopModel;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjStopModel;
import com.ppmg.ei.service.ProjStopService;

import java.util.List;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-10-22 11:46 
 */ 
@Component("projStopService")
@Service(interfaceClass = ProjStopService.class)
public class ProjStopServiceImpl extends BaseServiceImpl<ProjStopModel> implements ProjStopService {

	public ProjStopServiceImpl(){
		this.setNamespace("ProjStop");
	}

	@Override
	public ProjStopModel selectProjId(String projId) {
		return (ProjStopModel) this.selectOne("selectProjIdMapper",projId);
	}

	@Override
	public List<FpPaymentRequestStopModel> selectList(String projId) {
		List<FpPaymentRequestStopModel> list = this.selectList("selectByIdStop",projId);
		return list;
	}
}