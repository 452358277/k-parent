package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundMemberModel;
import com.ppmg.ei.service.FundMemberService;

@Component("fundMemberService")
@Service(interfaceClass = FundMemberService.class)
public class FundMemberServiceImpl extends BaseServiceImpl<FundMemberModel> implements FundMemberService {

	public FundMemberServiceImpl(){
		this.setNamespace("FundMember");
	}

}