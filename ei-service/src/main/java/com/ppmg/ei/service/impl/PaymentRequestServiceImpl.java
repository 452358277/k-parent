package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.PaymentRequestModel;
import com.ppmg.ei.service.PaymentRequestService;

@Component("paymentRequestService")
@Service(interfaceClass = PaymentRequestService.class)
public class PaymentRequestServiceImpl extends BaseServiceImpl<PaymentRequestModel> implements PaymentRequestService {

	public PaymentRequestServiceImpl(){
		this.setNamespace("PaymentRequest");
	}

}