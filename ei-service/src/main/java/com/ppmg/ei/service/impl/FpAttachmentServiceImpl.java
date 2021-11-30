package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FpAttachmentModel;
import com.ppmg.ei.service.FpAttachmentService;

@Component("fpAttachmentService")
@Service(interfaceClass = FpAttachmentService.class)
public class FpAttachmentServiceImpl extends BaseServiceImpl<FpAttachmentModel> implements FpAttachmentService {

	public FpAttachmentServiceImpl(){
		this.setNamespace("FpAttachment");
	}

}