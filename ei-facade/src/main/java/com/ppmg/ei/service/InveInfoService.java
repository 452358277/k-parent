package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.InveInfoModel;
import com.ppmg.ei.model.InveResumeModel;

import java.util.List;

public interface InveInfoService extends BaseService<InveInfoModel>  {

    List<InveInfoModel> selectListByMcName(InveInfoModel in);

    void insertByInvestorId(InveInfoModel in, String userId, InveResumeModel inveResumeModel) throws  Exception;

    void updateByInvestorId(InveInfoModel in, String userId, InveResumeModel inveResumeModel)throws  Exception;
    /**
     * 查询投资人信息
     * @param investorId
     * @return
     */
    public InveInfoModel selectOneInveInfo(String investorId);

}