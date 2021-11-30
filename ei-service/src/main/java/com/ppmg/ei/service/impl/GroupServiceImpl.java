package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.GroupModel;
import com.ppmg.ei.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Component("groupService")
@Service(interfaceClass = GroupService.class)
public class GroupServiceImpl extends BaseServiceImpl<GroupModel> implements GroupService {

	public GroupServiceImpl(){
		this.setNamespace("Group");
	}

}