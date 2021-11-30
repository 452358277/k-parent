package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FounderOaApplyInfoModel;
import com.ppmg.ei.service.FounderOaApplyInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("founderOaApplyInfoService")
@Service(interfaceClass = FounderOaApplyInfoService.class)
public class FounderOaApplyInfoServiceImpl extends BaseServiceImpl<FounderOaApplyInfoModel> implements FounderOaApplyInfoService {

	public FounderOaApplyInfoServiceImpl(){
		this.setNamespace("FounderOaApplyInfo");
	}

	@Override
	public void deleteByIds(List<String> ids) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("ids", ids);
		this.delete("deleteByIds", paramsMap);
	}

}