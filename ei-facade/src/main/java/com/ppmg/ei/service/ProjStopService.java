package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FpPaymentRequestStopModel;
import com.ppmg.ei.model.ProjStopModel;

import java.util.List;

/** 
 * 描述 [Service] 
 * @author null
 * @date 2019-10-22 11:46 
 */ 
public interface ProjStopService extends BaseService<ProjStopModel>  {

    ProjStopModel selectProjId(String projId);

    List<FpPaymentRequestStopModel> selectList(String projId);
}