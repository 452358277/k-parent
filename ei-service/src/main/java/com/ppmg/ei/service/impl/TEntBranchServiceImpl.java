package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TEntBranchModel;
import com.ppmg.ei.service.TEntBranchService;

@Component("tEntBranchService")
@Service(interfaceClass = TEntBranchService.class)
public class TEntBranchServiceImpl extends BaseServiceImpl<TEntBranchModel> implements TEntBranchService {

	public TEntBranchServiceImpl(){
		this.setNamespace("TEntBranch");
	}

}