package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.ProjShareInfoModel;
import com.ppmg.ei.model.TzTProjOwnershipInfoModel;

import java.util.List;

public interface ProjShareInfoService extends BaseService<ProjShareInfoModel>  {

   List<ProjShareInfoModel> selectListShare(ProjShareInfoModel model);

   void updateInfo(List<ProjShareInfoModel> listVo , TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel )throws Exception;

   void saveInfo(List<ProjShareInfoModel> listVo , TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel )throws Exception;
}