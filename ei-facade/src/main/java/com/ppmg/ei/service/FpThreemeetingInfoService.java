package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FpThreemeetingInfoModel;
import com.ppmg.ei.model.YhStaffListPhModel;

import java.util.List;

public interface FpThreemeetingInfoService extends BaseService<FpThreemeetingInfoModel>  {

    public void addFpThreemeetingInfo(FpThreemeetingInfoModel fpThreemeetingInfoModel);

    public void updateFpThreemeetingInfo(FpThreemeetingInfoModel fpThreemeetingInfoModel);

    <E> PageInfo<E> fundFpThreemeetingInfoListPage(SearchCondition var1);

    List<YhStaffListPhModel> selectNumbers(String projId);
    <E> PageInfo<E> fundFpThreemeetingInfoListPageNumber(SearchCondition var1);

    int count(SearchCondition var1);

}