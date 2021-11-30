package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.model.BdTFitRegulationSelfModel;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.FundInveDescModel;
import com.ppmg.ei.model.ProjInfoModel;

import java.util.List;
import java.util.Map;

public interface BdTFitRegulationSelfService extends BaseService<BdTFitRegulationSelfModel>  {

    void operation(FundInfoModel fundInfoModel, ProjInfoModel projInfoModel, BdTFitRegulationSelfModel  bdTFitRegulationSelfModel, FundInveDescModel fundInveDescModel,String userId) throws Exception;

    Map<String,Object> selectSum(String id);


    List<BdTFitRegulationSelfModel> selectListBybdTFit(Map<String, Object> map);

   /** 省政府基金-合规性审查列表**/
    <E> PageInfo<E> selectRegulationPage(SearchCondition var1);

}