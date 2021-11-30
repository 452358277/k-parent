package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.FundInfoModel;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjAppInfoMemberModel;
import com.ppmg.ei.model.ProjAppInfoModel;
import com.ppmg.ei.model.ProjAppInfoSnapshotDModel;

import java.util.Map;

public interface ProjAppInfoService extends BaseService<ProjAppInfoModel>  {
    /**
     * 查询基金立项
     * @param var1
     * @param <E>
     * @return
     */
    <E> PageInfo<E> selectallListPage(SearchCondition var1);

    public ProjAppInfoModel getgetTzProjAppInfo(String projId);
    /**
     * 获取基金立项详情
     * @param projId
     * @return
     */
    public ProjAppInfoMemberModel getOneDetails(String projId);

    public void insertAll(ProjAppInfoMemberModel projAppInfoMemberModel);

    public void updateAll(ProjAppInfoMemberModel projAppInfoMemberModel);

    public void deleteAll(String[] arr);

    public void insertAppInfo(ProjAppInfoModel projAppInfoModel);
    public void updateAppInfo(ProjAppInfoModel projAppInfoModel);

    //删除TZ_T_PROJ_APP_INFO
    public void deleteAppInfo(String projId);

    //删除TZ_T_PROJ_MEMBER
    public void deleteMember(String projId);

    //--项目投管，基金立项
    //查询
    public ProjAppInfoMemberModel getOneProjectDetails(String projId);

    public void insertProjectAll(ProjAppInfoMemberModel projAppInfoMemberModel);

    public void updateProjectAll(ProjAppInfoMemberModel projAppInfoMemberModel);

    public void deleteProjectAll(String[] arr);

    void updateByInfo(ProjAppInfoModel projAppInfo, FundInfoModel fundModel, ProjAppInfoSnapshotDModel model,String projCodeS) throws Exception;

    Map<String, Object>  selectByEiNjId(String projId);
}