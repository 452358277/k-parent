package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTNewModel;
import com.ppmg.ei.service.CommTNewService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("commTNewService")
@Service(interfaceClass = CommTNewService.class)
public class CommTNewServiceImpl extends BaseServiceImpl<CommTNewModel> implements CommTNewService {

	public CommTNewServiceImpl(){
		this.setNamespace("CommTNew");
	}

	@Override
	public List<CommTNewModel> selectListById(String id) {
		return this.selectList("selectListById",id);
	}
}