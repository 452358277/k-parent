package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.InveGroupMemberModel;
import com.ppmg.ei.service.InveGroupMemberService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-09-04 16:21 
 */ 
@Component("inveGroupMemberService")
@Service(interfaceClass = InveGroupMemberService.class)
public class InveGroupMemberServiceImpl extends BaseServiceImpl<InveGroupMemberModel> implements InveGroupMemberService {

	public InveGroupMemberServiceImpl(){
		this.setNamespace("InveGroupMember");
	}

}