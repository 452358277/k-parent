package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.EntBaseInfoModel;

import java.util.List;

public interface EntBaseInfoService extends BaseService<EntBaseInfoModel>  {

    /**
     * 根据平台ID获取企业基本信息
     * @param platId
     * @return
     */
    public EntBaseInfoModel getEntBaseInfoByPlatId(String platId);
    /**
     * 根据企业名称查询企业所属行业
     * @param name
     * @return
     */
    public List<EntBaseInfoModel> selectByHy(String name);

}