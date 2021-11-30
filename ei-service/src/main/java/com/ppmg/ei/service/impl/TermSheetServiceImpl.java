package com.ppmg.ei.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.TermSheetModel;
import com.ppmg.ei.service.TermSheetService;

@Component("termSheetService")
@Service(interfaceClass = TermSheetService.class)
public class TermSheetServiceImpl extends BaseServiceImpl<TermSheetModel> implements TermSheetService {

	public TermSheetServiceImpl(){
		this.setNamespace("TermSheet");
	}

}