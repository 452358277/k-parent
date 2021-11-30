package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.UserModel;
import com.ppmg.ei.service.UserService;

@Component("userService")
@Service(interfaceClass = UserService.class)
public class UserServiceImpl extends BaseServiceImpl<UserModel> implements UserService {

	public UserServiceImpl(){
		this.setNamespace("User");
	}

}