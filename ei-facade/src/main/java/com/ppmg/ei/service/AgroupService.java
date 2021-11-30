package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.AgroupModel;

public interface AgroupService extends BaseService<AgroupModel>  {

    /**
     * 根据组织机构树
     * @return
     */
    public AgroupModel getTreeList();


}