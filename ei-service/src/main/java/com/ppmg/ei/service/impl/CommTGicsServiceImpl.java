package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTGicsModel;
import com.ppmg.ei.service.CommTGicsService;

import java.util.List;
import java.util.Map;

@Component("commTGicsService")
@Service(interfaceClass = CommTGicsService.class)
public class CommTGicsServiceImpl extends BaseServiceImpl<CommTGicsModel> implements CommTGicsService {

	public CommTGicsServiceImpl(){
		this.setNamespace("CommTGics");
	}

	@Override
	public List<Map<String, Object>> getGicsList(Map<String, Object> params) {
		return this.selectList("getGicsList", params);
	}

	@Override
	public Map<String, Object> selectByGisName(String id) {
		return (Map<String, Object>)this.selectOne("selectByGisName",id);
	}
}