package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CustMemberModel;
import com.ppmg.ei.service.CustMemberService;
import org.springframework.stereotype.Component;

@Component("custMemberService")
@Service(interfaceClass = CustMemberService.class)
public class CustMemberServiceImpl extends BaseServiceImpl<CustMemberModel> implements CustMemberService {

	public CustMemberServiceImpl(){
		this.setNamespace("CustMember");
	}

}