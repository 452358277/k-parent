package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProgressCommentModel;

public interface ProgressCommentService extends BaseService<ProgressCommentModel>  {

    void insertCommentAndMessage(ProgressCommentModel model, String allUserIds, String allUserNames , String userId, String name, String dealMark,String dist,String projStatus) throws Exception ;

}