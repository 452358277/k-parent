package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjBackMoneySettleModel;
import com.ppmg.ei.service.ProjBackMoneySettleService;

import java.util.Map;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-09-26 13:57 
 */ 
@Component("projBackMoneySettleService")
@Service(interfaceClass = ProjBackMoneySettleService.class)
public class ProjBackMoneySettleServiceImpl extends BaseServiceImpl<ProjBackMoneySettleModel> implements ProjBackMoneySettleService {

	public ProjBackMoneySettleServiceImpl(){
		this.setNamespace("ProjBackMoneySettle");
	}

	@Override
	public ProjBackMoneySettleModel selectProjBackMoneySettleAppId(String appId) {
		return (ProjBackMoneySettleModel) this.selectOne("ProjBackMoneySettleModelMapper",appId);
	}

	@Override
	public Map<String, Object> sumBackMoney(String projId) {
		return (Map<String,Object>)this.selectOne("sumBackMoney",projId);
	}
}