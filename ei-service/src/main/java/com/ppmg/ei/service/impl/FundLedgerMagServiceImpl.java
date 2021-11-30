package com.ppmg.ei.service.impl;

import com.ppmg.ei.model.InveInfoModel;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundLedgerMagModel;
import com.ppmg.ei.service.FundLedgerMagService;

import java.util.List;

/** 
 * 描述 [Service.impl] 
 * @author root
 * @date 2019-09-03 14:21 
 */ 
@Component("fundLedgerMagService")
@Service(interfaceClass = FundLedgerMagService.class)
public class FundLedgerMagServiceImpl extends BaseServiceImpl<FundLedgerMagModel> implements FundLedgerMagService {

	public FundLedgerMagServiceImpl(){
		this.setNamespace("FundLedgerMag");
	}

	@Override
	public InveInfoModel selectOneInveInfo(String investorId) {
		return (InveInfoModel)this.selectOne("selectOneInveInfoMapper",investorId);
	}

	@Override
	public List<FundLedgerMagModel> selectFundLedgerMagList(String pkId) {
		return this.selectList("selectFundLedgerMagListMapper",pkId);
	}
}