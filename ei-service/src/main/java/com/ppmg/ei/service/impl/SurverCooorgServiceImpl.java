package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.service.SurverCooorgService;

import java.util.List;

@Component("surverCooorgService")
@Service(interfaceClass = SurverCooorgService.class)
public class SurverCooorgServiceImpl extends BaseServiceImpl<SurverCooorgModel> implements SurverCooorgService {

	public SurverCooorgServiceImpl(){
		this.setNamespace("SurverCooorg");
	}

	@Override
	public List<SurverCooorgModel> selectListById(SurverCooorgModel surverCooorgModel) {
		return this.selectList("selectListById",surverCooorgModel);
	}
}