package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjMemberModel;

import java.util.List;
import java.util.Map;

public interface ProjMemberService extends BaseService<ProjMemberModel> {
    void deletcProjId(String projId);

    List<ProjMemberModel> selectList(String projId);

    Map<String,Object>  seleByName(String projId);

}