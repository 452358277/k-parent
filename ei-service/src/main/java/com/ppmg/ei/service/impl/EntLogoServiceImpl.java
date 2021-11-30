package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EntLogoModel;
import com.ppmg.ei.service.EntLogoService;

import java.util.Map;

@Component("entLogoService")
@Service(interfaceClass = EntLogoService.class)
public class EntLogoServiceImpl extends BaseServiceImpl<EntLogoModel> implements EntLogoService {

	public EntLogoServiceImpl(){
		this.setNamespace("EntLogo");
	}

	@Override
	public EntLogoModel getLogoByEntId(Map<String, Object> params) {
//		Map<String,Object> map = this..selectSql2Map("getLogoByEntId",params);
		return (EntLogoModel)this.selectOne("getLogoByEntId",params);
	}
}