package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.QuotaConfDetailModel;
import com.ppmg.ei.service.QuotaConfDetailService;

import java.util.List;

@Component("quotaConfDetailService")
@Service(interfaceClass = QuotaConfDetailService.class)
public class QuotaConfDetailServiceImpl extends BaseServiceImpl<QuotaConfDetailModel> implements QuotaConfDetailService {

	public QuotaConfDetailServiceImpl(){
		this.setNamespace("QuotaConfDetail");
	}

	@Override
	public List<QuotaConfDetailModel> getQuotaConfDetailByConfId(String confId) {
		return this.selectList("getQuotaConfDetailByConfId",confId);
	}
}