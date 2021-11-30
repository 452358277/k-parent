package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.model.EntLabelModel;
import com.ppmg.ei.model.LabelModel;

import java.util.List;
import java.util.Map;

public interface LabelService extends BaseService<LabelModel>  {

    List<LabelModel> selectListByLabel(String entId);

    Map<String,Object> selectListByLabels(String entId);

    List<LabelModel> selectListByItem();

  void deleteBy(String labelId);

}