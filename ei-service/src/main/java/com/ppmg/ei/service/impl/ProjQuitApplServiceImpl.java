package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjQuitApplModel;
import com.ppmg.ei.service.ProjQuitApplService;

@Component("projQuitApplService")
@Service(interfaceClass = ProjQuitApplService.class)
public class ProjQuitApplServiceImpl extends BaseServiceImpl<ProjQuitApplModel> implements ProjQuitApplService {

	public ProjQuitApplServiceImpl(){
		this.setNamespace("ProjQuitAppl");
	}

	@Override
	public Double seleSumRevenue(String projId) {
		return (Double)this.selectOne("seleSumRevenue",projId);
	}
}