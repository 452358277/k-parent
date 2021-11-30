package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.model.CommTLabelItemModel;

import java.util.List;
import java.util.Map;

public interface CommTLabelItemService extends BaseService<CommTLabelItemModel>  {

    void saveItemInfo(List<LabelSearchBean> labels,String userId);

    List<Map<String,Object>> selectByLabelInfo( Map<String, Object> param);

    void saveEntItem(List<LabelSearchBean> labels,String userId) throws Exception;


    void delEntItem(List<LabelSearchBean> labels,String userId) throws Exception;



}