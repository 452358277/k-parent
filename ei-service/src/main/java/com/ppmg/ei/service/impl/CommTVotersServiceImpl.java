package com.ppmg.ei.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTVotersModel;
import com.ppmg.ei.service.CommTVotersService;

@Component("commTVotersService")
@Service(interfaceClass = CommTVotersService.class)
public class CommTVotersServiceImpl extends BaseServiceImpl<CommTVotersModel> implements CommTVotersService {

	public CommTVotersServiceImpl(){
		this.setNamespace("CommTVoters");
	}

	@Override
	public List<CommTVotersModel> getProjAppVoteListByProjId(String projId) {
		return this.selectList("getProjAppVoteListByProjId", projId);
	}

	@Override
	public List<CommTVotersModel> getProjDecisionVoteListByProjId(String projId) {
		return this.selectList("getProjDecisionVoteListByProjId", projId);
	}

}