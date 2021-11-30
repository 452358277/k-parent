package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.PlatformInfoModel;

public interface PlatformInfoService extends BaseService<PlatformInfoModel>  {

    /**
     * 根据平台ID获取平台基本信息
     * @param pkId
     * @return
     */
    public PlatformInfoModel getPlatformInfoByPlatId(String pkId);


    void insertCodeAndOrgAndRole(PlatformInfoModel model,String loginUserId) throws Exception ;

}