package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjAssignModel;
import com.ppmg.ei.service.ProjAssignService;

@Component("projAssignService")
@Service(interfaceClass = ProjAssignService.class)
public class ProjAssignServiceImpl extends BaseServiceImpl<ProjAssignModel> implements ProjAssignService {

	public ProjAssignServiceImpl(){
		this.setNamespace("ProjAssign");
	}

	@Override
	public void updateByNo(String no) {
		this.update("updateByNo",no);

	}

	@Override
	public Integer selectOneNo(String no) {
		return (Integer)this.selectOne("selectOneNo",no);
	}
}