package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.model.ApplyInfoModel;
import com.ppmg.ei.model.ProjAssignModel;
import com.ppmg.ei.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ApplyIssuedDocModel;

import java.util.Date;

@Component("applyIssuedDocService")
@Service(interfaceClass = ApplyIssuedDocService.class)
public class ApplyIssuedDocServiceImpl extends BaseServiceImpl<ApplyIssuedDocModel> implements ApplyIssuedDocService {

	public ApplyIssuedDocServiceImpl(){
		this.setNamespace("ApplyIssuedDoc");
	}

	@Autowired
	private ApplyInfoService applyInfoService;

	@Autowired
	private ApplyIssuedDocService applyIssuedDocService;

	@Autowired
	private ProjAssignService projAssignService;

	@Override
	public void insertApply(ApplyIssuedDocModel applyIssuedDoc, ApplyInfoModel applyInfoModel,String assId) throws Exception {
		applyIssuedDoc.setCreateDt(new Date());
		applyIssuedDoc.setUpdateBy(applyIssuedDoc.getCreateBy());
		applyIssuedDoc.setUpdateDt(new Date());
		applyInfoModel.setCreateBy(applyIssuedDoc.getCreateBy());
		applyInfoModel.setCreateDt(new Date());
		applyInfoModel.setUpdateBy(applyIssuedDoc.getCreateBy());
		applyInfoModel.setUpdateDt(new Date());
		applyIssuedDocService.insert(applyIssuedDoc);
		applyInfoModel.setProcessStatus("部门经理审核");
		applyInfoService.insert(applyInfoModel);
		ProjAssignModel projAssignModel=new ProjAssignModel();
		projAssignModel.setApplyNo(applyInfoModel.getApplyNo());
		projAssignModel.setAssignId(assId);
		 if(StringUtils.isNotEmpty(assId)){
			 projAssignService.update(projAssignModel);
		 }

	}
}