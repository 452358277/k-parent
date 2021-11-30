package com.ppmg.ei.service.impl;

import com.ppmg.ei.service.EntBaseInfoService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntBaseInfoModel;

import java.util.List;

@Component("entBaseInfoService")
@Service(interfaceClass = EntBaseInfoService.class)
public class EntBaseInfoServiceImpl extends BaseServiceImpl<EntBaseInfoModel> implements EntBaseInfoService {

	public EntBaseInfoServiceImpl(){
		this.setNamespace("EntBaseInfo");
	}

	@Override
	public EntBaseInfoModel getEntBaseInfoByPlatId(String platId) {
		return (EntBaseInfoModel)this.selectOne("getEntBaseInfoByPlatId", platId);
	}

	@Override
	public List<EntBaseInfoModel> selectByHy(String name) {
		return this.selectList("getEntBaseInfoByName", name);
	}
}