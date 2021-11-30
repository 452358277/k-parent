package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjMemberModel;
import com.ppmg.ei.service.ProjMemberService;

import java.util.List;

import java.util.Map;

@Component("projMemberService")
@Service(interfaceClass = ProjMemberService.class)
public class ProjMemberServiceImpl extends BaseServiceImpl<ProjMemberModel> implements ProjMemberService {

	public ProjMemberServiceImpl(){
		this.setNamespace("ProjMember");
	}

	@Override
	public void deletcProjId(String projId) {
		this.delete("deletcProjIdMapper",projId);
	}

	@Override
	public List<ProjMemberModel> selectList(String projId) {
		List<ProjMemberModel> list = this.selectList("selectListMapper",projId);
		return list;
	}
	@Override
	public Map<String, Object> seleByName(String projId) {
		return (Map<String,Object>)this.selectOne("seleByName", projId);
	}
}