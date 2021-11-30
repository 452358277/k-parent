package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjContractFileDebtModel;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.model.ProjContractFileQuitModel;

import java.util.List;

public interface ProjContractFileService extends BaseService<ProjContractFileModel>  {

    public List<ProjContractFileModel> selectByProjIdList(String projId);

    public Double getSumSignAmount(String projId,String inveId);

    public void insertById(ProjContractFileModel projContractFileModel, ProjContractFileDebtModel projContractFileDebtModel,String  userId) throws Exception;

    public void updateById(ProjContractFileModel projContractFileModel, ProjContractFileDebtModel projContractFileDebtModel,String  userId)throws Exception;

    /**
     * 退出合同--查询
     * @param var1
     * @param <E>
     * @return
     */
    <E> PageInfo<E> selectPageListQuit(SearchCondition var1);
    void insertIntoQuit(String  userId, ProjContractFileQuitModel projContractFileQuitModel);
    void updateIntoQuit(String  userId, ProjContractFileQuitModel projContractFileQuitModel);
    void deleteIntoQuit(String  fileId);
    void deleteIntoQuitAppId(String appId);
    ProjContractFileQuitModel selectDetailes(String fileId);
    ProjContractFileQuitModel selectDetailesAppId(String appId);
    List<ProjContractFileQuitModel> selectDetailesAppIdList(String appId);

    <E> PageInfo<E> selectByListPage(SearchCondition var1);


     String selectGetName(String id);
    /**
     * 投项目新增
     * @param userId
     * @param pq
     * @return void
     * @author zhaokuiyu
     * @date 2019/12/19 11:06
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    void insertProjContractFileProject(String userId,ProjContractFileQuitModel pq);


    void insertProjContractFileProjectBy(String userId,ProjContractFileQuitModel pq);

    /**
     * 投项目更新
     * @return void
     * @author zhaokuiyu
     * @date 2019/12/19 11:06
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    void updateProjContractFileProject(String userId,ProjContractFileQuitModel pq,ProjContractFileQuitModel one);


}