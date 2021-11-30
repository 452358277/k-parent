package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.*;

public interface ProjAppInfoSnapshotDService extends BaseService<ProjAppInfoSnapshotDModel>  {

    void updateByAappInfo(ProjAppInfoModel projAppInfo, FundInfoModel fundInfoModel, ProjAppInfoSnapshotDModel model, ProjInfoModel projInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelf) throws Exception;

}