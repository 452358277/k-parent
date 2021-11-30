package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.model.ProjContractInfoModel;

import java.util.Date;
import java.util.List;

public interface ProjContractInfoService extends BaseService<ProjContractInfoModel>  {

    void insertByproj(ProjContractInfoModel model,List<ProjContractFileModel> list, String userId,String majorTerm,String contractFile)throws Exception;

    void updateByproj(ProjContractInfoModel model, List<ProjContractFileModel> list, String userId, String majorTerm, String contractFile, String groupId, Date signDt, String  finalFile)throws Exception;

    void updateByInfo(String id,String userId)throws Exception;


    <E> PageInfo<E> selectListByInfoPage(SearchCondition var1);
}