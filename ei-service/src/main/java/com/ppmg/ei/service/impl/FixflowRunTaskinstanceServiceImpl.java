package com.ppmg.ei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;

@Component("fixflowRunTaskinstanceService")
@Service(interfaceClass = FixflowRunTaskinstanceService.class)
public class FixflowRunTaskinstanceServiceImpl extends BaseServiceImpl<FixflowRunTaskinstanceModel> implements FixflowRunTaskinstanceService {

	public FixflowRunTaskinstanceServiceImpl(){
		this.setNamespace("FixflowRunTaskinstance");
	}

	@Override
	public List<FixflowRunTaskinstanceModel> getProjAppTaskListByProjId(String projId) {
		return this.selectList("getProjAppTaskListByProjId", projId);
	}

	@Override
	public List<FixflowRunTaskinstanceModel> getProjDecisionTaskListByProjId(String projId) {
		return this.selectList("getProjDecisionTaskListByProjId", projId);
	}

	@Override
	public List<FixflowRunTaskinstanceModel> getListByProcessinstanceId(String processinstanceId) {
		return this.selectList("getListByProcessinstanceId", processinstanceId);
	}


	@Override
	public List<Map<String, Object>> getFixFlowByTaskId(String processId) {
		return this.selectList("getFixFlowByTaskId",processId);
	}
}