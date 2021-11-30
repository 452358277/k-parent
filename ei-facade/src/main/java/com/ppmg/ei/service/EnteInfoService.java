package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.CustMemberModel;
import com.ppmg.ei.model.EnteInfoModel;
import com.ppmg.ei.model.ProjInfoModel;

import java.util.List;

public interface EnteInfoService extends BaseService<EnteInfoModel>  {

    void insertInfo(EnteInfoModel model, CustMemberModel cum,List<String> labels, ProjInfoModel projInfoModel,String typeStr);


    void updateInfo(EnteInfoModel model, CustMemberModel cum, List<String> labels,ProjInfoModel projInfoModel,String typeStr);

    <E> PageInfo<E> selectGetListPage(SearchCondition var1);
}