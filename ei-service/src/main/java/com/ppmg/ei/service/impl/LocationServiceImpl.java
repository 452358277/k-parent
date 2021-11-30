package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.LocationModel;
import com.ppmg.ei.service.LocationService;

import java.util.List;

@Component("locationService")
@Service(interfaceClass = LocationService.class)
public class LocationServiceImpl extends BaseServiceImpl<LocationModel> implements LocationService {

	public LocationServiceImpl(){
		this.setNamespace("Location");
	}

}