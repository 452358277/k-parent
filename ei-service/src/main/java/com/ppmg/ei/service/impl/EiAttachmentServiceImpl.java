package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.EiTAttachmentModel;
import com.ppmg.ei.service.EiAttachmentService;
import org.springframework.stereotype.Component;

@Component("eiAttachmentService")
@Service(interfaceClass = EiAttachmentService.class)
public class EiAttachmentServiceImpl extends BaseServiceImpl<EiTAttachmentModel> implements EiAttachmentService {

	public EiAttachmentServiceImpl(){
		this.setNamespace("EiTAttachment");
	}

}