package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.CommTVoteMagModel;

public interface CommTVoteMagService extends BaseService<CommTVoteMagModel>  {

	/**
	 * 根据项目ID获取项目立项决议结果信息
	 * @param projId
	 * @return
	 */
	public CommTVoteMagModel getProjAppVoteMagInfoByProjId(String projId);

	/**
	 * 根据项目ID获取项目决策决议结果信息
	 * @param projId
	 * @return
	 */
	public CommTVoteMagModel getProjDecisionVoteMagInfoByProjId(String projId);

}