package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTVoteMagModel;
import com.ppmg.ei.service.CommTVoteMagService;

@Component("commTVoteMagService")
@Service(interfaceClass = CommTVoteMagService.class)
public class CommTVoteMagServiceImpl extends BaseServiceImpl<CommTVoteMagModel> implements CommTVoteMagService {

	public CommTVoteMagServiceImpl(){
		this.setNamespace("CommTVoteMag");
	}

	@Override
	public CommTVoteMagModel getProjAppVoteMagInfoByProjId(String projId) {
		return (CommTVoteMagModel)this.selectOne("getProjAppVoteMagInfoByProjId", projId);
	}

	@Override
	public CommTVoteMagModel getProjDecisionVoteMagInfoByProjId(String projId) {
		return (CommTVoteMagModel)this.selectOne("getProjDecisionVoteMagInfoByProjId", projId);
	}

}