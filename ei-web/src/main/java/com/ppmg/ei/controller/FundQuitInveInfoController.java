package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.action.BaseAction;
import com.ppmg.ei.service.FundQuitInveInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
@Api(tags={"接口"})
public class FundQuitInveInfoController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundQuitInveInfoService fundQuitInveInfoService;


}

