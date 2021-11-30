package com.ppmg.ei.service.impl;

import com.founder.ssm.core.common.SearchCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FounderOaApplyContractModel;
import com.ppmg.ei.service.FounderOaApplyContractService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("founderOaApplyContractService")
@Service(interfaceClass = FounderOaApplyContractService.class)
public class FounderOaApplyContractServiceImpl extends BaseServiceImpl<FounderOaApplyContractModel> implements FounderOaApplyContractService {

	public FounderOaApplyContractServiceImpl(){
		this.setNamespace("FounderOaApplyContract");
	}

	@Override
	public void deleteByIds(List<String> ids) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("ids", ids);
		this.delete("deleteByIds", paramsMap);
	}

	@Override
	public <FounderOaApplyContractModel> PageInfo<FounderOaApplyContractModel> selectApplyContractListByFundId(SearchCondition searchCondition) {
		return (PageInfo<FounderOaApplyContractModel>) this.selectListPage("selectApplyContractListByFundId",searchCondition);
	}

}