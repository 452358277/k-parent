package com.ppmg.ei.service;

import java.util.List;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.CommTVotersModel;

public interface CommTVotersService extends BaseService<CommTVotersModel>  {

	/**
	 * 根据项目ID获取项目立项表决结果列表
	 * @param projId
	 * @return
	 */
	public List<CommTVotersModel> getProjAppVoteListByProjId(String projId);

	/**
	 * 根据项目ID获取项目决策表决结果列表
	 * @param projId
	 * @return
	 */
	public List<CommTVotersModel> getProjDecisionVoteListByProjId(String projId);
}