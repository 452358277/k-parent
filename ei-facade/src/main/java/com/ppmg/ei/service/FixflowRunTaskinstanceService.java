package com.ppmg.ei.service;

import java.util.List;
import java.util.Map;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;

public interface FixflowRunTaskinstanceService extends BaseService<FixflowRunTaskinstanceModel>  {

	/**
	 * 根据项目ID获取项目立项审批信息列表
	 * @param projId
	 * @return
	 */
	public List<FixflowRunTaskinstanceModel> getProjAppTaskListByProjId(String projId);

	/**
	 * 根据项目ID获取项目决策审批信息列表
	 * @param projId
	 * @return
	 */
	public List<FixflowRunTaskinstanceModel> getProjDecisionTaskListByProjId(String projId);

	/**
	 * 根据流程ID获取流程审批信息列表
	 * @param projId
	 * @return
	 */
	public List<FixflowRunTaskinstanceModel> getListByProcessinstanceId(String processinstanceId);


	List<Map<String,Object>> getFixFlowByTaskId(String processId);


}