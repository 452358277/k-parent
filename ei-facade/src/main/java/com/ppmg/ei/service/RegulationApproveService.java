package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.RegulationApproveCommModel;
import com.ppmg.ei.model.RegulationApproveModel;

import java.util.List;

public interface RegulationApproveService extends BaseService<RegulationApproveModel>  {

    void saveInfo(RegulationApproveModel model, List<RegulationApproveCommModel> list);


    void  updateInfo(RegulationApproveModel model, List<RegulationApproveCommModel> list);



    void deleteByIds(List<String> ids, String userId);

}