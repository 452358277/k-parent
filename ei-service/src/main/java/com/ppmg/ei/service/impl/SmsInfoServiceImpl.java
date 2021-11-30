package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.AdminModel;
import com.ppmg.ei.model.SmsInfoModel;
import com.ppmg.ei.model.SmsModel;
import com.ppmg.ei.service.SmsInfoService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("smsInfoService")
@Service(interfaceClass = SmsInfoService.class)
public class SmsInfoServiceImpl extends BaseServiceImpl<SmsInfoModel> implements SmsInfoService {

	public SmsInfoServiceImpl(){
		this.setNamespace("SmsInfo");
	}

	@Override
	public List<AdminModel> getEntInfos() {
		return this.selectList("getEntInfos", "");
	}

	@Override
	public void deleteDetails(String smsId) {
		this.update("deleteDetails", smsId);
	}

	@Override
	public void deleteSmsInfo(String smsId) {
		this.update("deleteSmsInfo", smsId);
	}

	@Override
	public List<SmsModel> getSmsModel() {
		return this.selectList("getSmsModel", "");
	}


	@Override
	public List<SmsModel> getGovSmsModel(SearchCondition var1) {
		return this.selectList("getGovSmsModel", var1);
	}
}