package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ApplyInfoModel;
import com.ppmg.ei.model.ApplyIssuedDocModel;

public interface ApplyIssuedDocService extends BaseService<ApplyIssuedDocModel>  {

    void insertApply(ApplyIssuedDocModel applyIssuedDoc, ApplyInfoModel applyInfoModel,String assId)throws Exception;

}